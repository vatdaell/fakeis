package org.ansaf.command;

import java.io.PrintWriter;
import org.ansaf.data.Store;
import org.ansaf.data.structures.IDataStructure;
import org.ansaf.data.structures.IntStore;
import org.ansaf.data.structures.StringStore;
import org.ansaf.pojo.GetCommandObject;
import org.ansaf.pojo.SetCommandObject;
import org.tinylog.Logger;

public class ExecuteCommand {
  public IDataStructure createDataStructure(SetCommandObject setCommandObject){
    String type = setCommandObject.getType();
    String value = setCommandObject.getData();
    switch (type){
      case "STRING" -> {
        return new StringStore(value);
      }
      case "INT" -> {
        return new IntStore(Integer.valueOf(value));
      }
      default -> {
        return null;
      }
    }
  }

  public IDataStructure getDataStructure(GetCommandObject getCommandObject, Store store, PrintWriter out){
    if(store.getFromStore(getCommandObject.getKey()) == null){
      Logger.warn("Key {} not found", getCommandObject.getKey());
      out.println("Key not found");
    }
    return store.getFromStore(getCommandObject.getKey());
  }

}
