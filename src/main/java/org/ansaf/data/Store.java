package org.ansaf.data;

import java.util.HashMap;
import org.ansaf.data.structures.IDataStructure;

public class Store {
  private HashMap<String, IDataStructure> store;

  public Store(){
    this.store = new HashMap<>();
  }
  public HashMap<String, IDataStructure> getStore() {
    return store;
  }
  public void setStore(HashMap<String, IDataStructure> store) {
    this.store = store;
  }

  public void addToStore(String key, IDataStructure dataStructure){
    store.put(key, dataStructure);
  }

  public IDataStructure getFromStore(String key){
    return store.get(key);
  }

}
