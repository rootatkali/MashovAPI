package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ComparisonChain;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class Justification implements Comparable<Justification> {
  private String name;
  private boolean hidden;
  private int justificationId;
  private int displayOrder;
  
  public String getName() {
    return name;
  }
  
  public boolean isHidden() {
    return hidden;
  }
  
  public int getJustificationId() {
    return justificationId;
  }
  
  public int getDisplayOrder() {
    return displayOrder;
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("name", name)
        .add("hidden", hidden)
        .add("justificationId", justificationId)
        .add("displayOrder", displayOrder)
        .toString();
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Justification that = (Justification) o;
    return hidden == that.hidden &&
        justificationId == that.justificationId &&
        displayOrder == that.displayOrder &&
        Objects.equals(name, that.name);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(name, hidden, justificationId, displayOrder);
  }
  
  @Override
  public int compareTo(@NotNull Justification j) {
    return ComparisonChain.start()
        .compare(displayOrder, j.displayOrder)
        .compare(name, j.name)
        .compare(justificationId, j.justificationId)
        .result();
  }
}
