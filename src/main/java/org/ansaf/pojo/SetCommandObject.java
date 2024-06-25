package org.ansaf.pojo;

public class SetCommandObject {
  private String type;
  private String data;
  private String key;

  public SetCommandObject(String type, String key, String data) {
    this.type = type;
    this.data = data;
    this.key = key;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }
}
