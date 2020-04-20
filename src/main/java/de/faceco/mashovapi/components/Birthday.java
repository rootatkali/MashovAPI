package de.faceco.mashovapi.components;

public class Birthday {
  private String birthDate;
  
  Birthday() {
  
  }
  
  public String getBirthDate() {
    return birthDate;
  }
  
  public int getYear() {
    return Integer.parseInt(birthDate.substring(0, 4));
  }
  
  public int getMonth() {
    return Integer.parseInt(birthDate.substring(5, 7));
  }
  
  public int getDay() {
    return Integer.parseInt(birthDate.substring(8, 10));
  }
}
