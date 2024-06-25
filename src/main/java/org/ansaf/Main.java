package org.ansaf;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.ansaf.command.ExecuteCommand;
import org.ansaf.data.Store;
import org.ansaf.validator.InputValidator;
import org.tinylog.Logger;

public class Main {
  private static final int PORT = 12345;
  private static final int THREAD_POOL_SIZE = 10;

  public static void main(String[] args) {
    ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
    Store store = new Store();

    try (ServerSocket serverSocket = new ServerSocket(PORT)) {
      Logger.info("Server started, listening on port {}",PORT);
      while (true) {
        Socket clientSocket = serverSocket.accept();
        Logger.info("New connection from {}", clientSocket.getInetAddress().getHostAddress());
        executor.execute(new ClientHandler(clientSocket, new InputValidator(), new ExecuteCommand(), store));
      }
    } catch (IOException e) {
      Logger.info("Error while running server: {}", e.getMessage());
      System.exit(1);
    }

  }
}