package org.ansaf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import org.ansaf.command.ExecuteCommand;
import org.ansaf.data.Store;
import org.ansaf.data.structures.IDataStructure;
import org.ansaf.pojo.GetCommandObject;
import org.ansaf.pojo.SetCommandObject;
import org.ansaf.validator.InputValidator;
import org.tinylog.Logger;

public class ClientHandler implements Runnable{
  private final Socket clientSocket;
  private final InputValidator inputValidator;
  private final ExecuteCommand executeCommand;
  private final Store store;

  public ClientHandler(Socket socket, InputValidator inputValidator, ExecuteCommand executeCommand, Store store) {
    this.clientSocket = socket;
    this.inputValidator = inputValidator;
    this.executeCommand = executeCommand;
    this.store = store;
  }

  @Override
  public void run() {
    try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
      String inputLine;
      while ((inputLine = in.readLine()) != null) {
        Logger.info("Received: {}", inputLine);
        boolean inputValidation = inputValidator.validateInput(inputLine);
        if(inputValidation){
          if(inputLine.startsWith("SET")){
            SetCommandObject setCommandObject = inputValidator.getSetCommand(inputLine);
            IDataStructure dataStructure = executeCommand.createDataStructure(setCommandObject);
            store.addToStore(setCommandObject.getKey(), dataStructure);
            out.println("Key added" + inputLine);
          }
          else{
            GetCommandObject getCommandObject = inputValidator.getGetCommand(inputLine);
            IDataStructure val = executeCommand.getDataStructure(getCommandObject, store, out);
            if(val != null){
              out.println(store.getFromStore(getCommandObject.getKey()).getData());
            }
          }
        } else {
          Logger.error("Invalid input received: {}", inputLine);
          out.println("Invalid input received: " + inputLine);
        }
      }
    } catch (IOException e) {
      Logger.error("IO error: ",e.getMessage());
    } finally {
      try {
        clientSocket.close();
      } catch (IOException e) {
        Logger.error("Error closing socket: ",e.getMessage());      }
      Logger.info("Server shutting down");
    }
  }
}
