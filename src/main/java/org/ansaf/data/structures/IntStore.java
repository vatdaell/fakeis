package org.ansaf.data.structures;

public class IntStore implements IDataStructure<Integer>{

  private Integer data;

  public IntStore(Integer data) {
    this.data = data;
  }

  @Override
  public Integer getData() {
    return data;
  }

  @Override
  public void setData(Integer data) {
    this.data = data;
  }
}
