package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ComparisonChain;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class Achva implements Comparable<Achva> {
  private int code;
  private String name;
  private boolean hidden;
  private boolean inClass;
  private int commonBehaveId;
  private boolean onDuplicateLesson;
  private int aval;
  private int displayOrder;
  private long teacherid;
  private boolean justifiable;
  
  Achva() {
  
  }
  
  public int getCode() {
    return code;
  }
  
  public String getName() {
    return name;
  }
  
  public boolean isHidden() {
    return hidden;
  }
  
  public boolean isInClass() {
    return inClass;
  }
  
  public int getCommonBehaveId() {
    return commonBehaveId;
  }
  
  public boolean isOnDuplicateLesson() {
    return onDuplicateLesson;
  }
  
  public int getAval() {
    return aval;
  }
  
  public int getDisplayOrder() {
    return displayOrder;
  }
  
  public long getTeacherId() {
    return teacherid;
  }
  
  public boolean isJustifiable() {
    return justifiable;
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("code", code)
        .add("name", name)
        .add("hidden", hidden)
        .add("inClass", inClass)
        .add("commonBehaveId", commonBehaveId)
        .add("onDuplicateLesson", onDuplicateLesson)
        .add("aval", aval)
        .add("displayOrder", displayOrder)
        .add("teacherid", teacherid)
        .add("justifiable", justifiable)
        .toString();
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Achva achva = (Achva) o;
    return code == achva.code &&
        hidden == achva.hidden &&
        inClass == achva.inClass &&
        commonBehaveId == achva.commonBehaveId &&
        onDuplicateLesson == achva.onDuplicateLesson &&
        aval == achva.aval &&
        displayOrder == achva.displayOrder &&
        teacherid == achva.teacherid &&
        justifiable == achva.justifiable &&
        Objects.equals(name, achva.name);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(code, name, hidden, inClass, commonBehaveId, onDuplicateLesson, aval, displayOrder, teacherid
        , justifiable);
  }
  
  @Override
  public int compareTo(@NotNull Achva a) {
    return ComparisonChain.start()
        .compare(displayOrder, a.displayOrder)
        .compare(name, a.name)
        .compare(commonBehaveId, a.commonBehaveId)
        .result();
  }
}
