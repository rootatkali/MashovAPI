package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class BagrutGrade {
  private String studentGuid;
  private int moed;
  private int semel;
  private String name;
  private int semelRashi;
  private Integer shnati;
  @SerializedName("final")
  private Integer finalGrade;
  private boolean going;
  private String examRoomNumber;
  
  BagrutGrade() {
  
  }
  
  public String getStudentGuid() {
    return studentGuid;
  }
  
  public int getMoed() {
    return moed;
  }
  
  public int getSemel() {
    return semel;
  }
  
  public String getName() {
    return name;
  }
  
  public int getSemelRashi() {
    return semelRashi;
  }
  
  public Integer getShnati() {
    return shnati;
  }
  
  public Integer getFinal() {
    return finalGrade;
  }
  
  public boolean isGoing() {
    return going;
  }
  
  public String getExamRoomNumber() {
    return examRoomNumber;
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("studentGuid", studentGuid)
        .add("moed", moed)
        .add("semel", semel)
        .add("name", name)
        .add("semelRashi", semelRashi)
        .add("shnati", shnati)
        .add("finalGrade", finalGrade)
        .add("going", going)
        .add("examRoomNumber", examRoomNumber)
        .toString();
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BagrutGrade that = (BagrutGrade) o;
    return moed == that.moed &&
        semel == that.semel &&
        semelRashi == that.semelRashi &&
        going == that.going &&
        Objects.equals(studentGuid, that.studentGuid) &&
        Objects.equals(name, that.name) &&
        Objects.equals(shnati, that.shnati) &&
        Objects.equals(finalGrade, that.finalGrade) &&
        Objects.equals(examRoomNumber, that.examRoomNumber);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(studentGuid, moed, semel, name, semelRashi, shnati, finalGrade, going, examRoomNumber);
  }
}
