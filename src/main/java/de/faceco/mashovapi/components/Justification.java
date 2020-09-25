package de.faceco.mashovapi.components;

import com.google.common.collect.ComparisonChain;
import org.jetbrains.annotations.NotNull;

public final class Justification implements Comparable<Justification> {
  private String name;
  private boolean hidden;
  private int justificationId;
  private int displayOrder;
  
  @Override
  public int compareTo(@NotNull Justification j) {
    return ComparisonChain.start()
        .compare(displayOrder, j.displayOrder)
        .compare(name, j.name)
        .compare(justificationId, j.justificationId)
        .result();
  }
}
