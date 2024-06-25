package org.ansaf.validator;

import java.util.HashSet;
import java.util.List;
import org.ansaf.pojo.GetCommandObject;
import org.ansaf.pojo.SetCommandObject;

public class InputValidator {
  private final HashSet<String> validCommands = new HashSet<>(List.of("STRING", "INT"));
  public boolean validateInput(String input){
    // null/empty check
    if(input == null || input.trim().isEmpty()){
      return false;
    }
    String[] commandString = input.split("\\s+");

    if(commandString.length < 2){
      return false;
    }

    String command = commandString[0];
    String action = commandString[1];

    if(command.equals("SET") && action.equals("INT") && !isInteger(commandString[3])){
      return false;
    }


    return command.equals("SET") && validCommands.contains(action) && commandString.length == 4
        || command.equals("GET") && commandString.length == 2;
  }

  public SetCommandObject getSetCommand(String input){
    String[] commandString = input.split("\\s+");
    String action = commandString[1];
    String key = commandString[2];
    String value = commandString[3];
    return new SetCommandObject(action, key, value);

  }

  public GetCommandObject getGetCommand(String input){
    String[] commandString = input.split("\\s+");
    String key = commandString[1];
    return new GetCommandObject(key);
  }

  private boolean isInteger(String str) {
    if (str == null || str.isEmpty()) {
      return false;
    }
    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      if (i == 0 && (c == '-' || c == '+')) {
        if (str.length() == 1) {
          return false; // single '-' or '+' is not valid
        }
        continue; // skip the sign character
      }
      if (!Character.isDigit(c)) {
        return false; // if character is not a digit
      }
    }
    return true;
  }

}
