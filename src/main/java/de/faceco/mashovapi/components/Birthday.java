package de.faceco.mashovapi.components;

import de.faceco.mashovapi.API;

/**
 * A class showing the user's birth date. Mashov returns all dates formatted as "yyyy-MM-ddThh:mm:ss".
 * Convenience methods convert the string to integers.
 *
 * @see API#getBirthday()
 */
public final class Birthday {
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
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    
    Birthday b = (Birthday) o;
  
    return getYear() == b.getYear() && getMonth() == b.getMonth() && getDay() == b.getDay();
  }
  
  @Override
  public int hashCode() {
    int result = getYear();
    result = 31 * result + getMonth();
    result = 31 * result + getDay();
    return result;
  }
}
