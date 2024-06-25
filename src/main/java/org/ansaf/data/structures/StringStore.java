package org.ansaf.data.structures;

public class StringStore implements IDataStructure<String>{
  private String data;

  public StringStore(String data) {
    this.data = data;
  }

  @Override
  public String getData() {
    return data;
  }
  @Override
  public void setData(String data) {
    this.data = data;
  }
}
