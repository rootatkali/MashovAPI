package de.faceco.mashovapi.components;

import java.util.Arrays;
import java.util.Objects;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ComparisonChain;
import com.google.gson.annotations.Expose;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a school registered in the Mashov system.
 */
public final class School implements Comparable<School> {
  private int semel;
  private String name;
  private int[] years;
  
  /**
   * Zero-argument constructor, for internal Gson use.
   */
  School() {
  
  }
  
  /**
   * Returns the school symbol as designated by the Ministry of Education.
   * Example: HaKfar HaYarok = 580019
   */
  public int getId() {
    return semel;
  }
  
  public String getName() {
    return name;
  }
  
  public int[] getYears() {
    return years;
  }
  
  public int getCurrentYear() {
    return years[years.length - 1];
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", semel)
        .add("name", name)
        .add("years", years)
        .toString();
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    
    School school = (School) o;
  
    return semel == school.semel
        && Objects.equals(name, school.name)
        && Arrays.equals(years, school.years);
  }
  
  @Override
  public int hashCode() {
    int result = semel;
    result = (31 * result) + ((name != null) ? name.hashCode() : 0);
    result = (31 * result) + Arrays.hashCode(years);
    return result;
  }
  
  @Override
  public int compareTo(@NotNull School s) {
    return ComparisonChain.start().compare(semel, s.semel).result();
  }
}
