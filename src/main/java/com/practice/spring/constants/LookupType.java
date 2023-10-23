package com.practice.spring.constants;

import java.util.Arrays;

public enum LookupType {
  BIRYANI("biryani"),
  CURRIES("curries"),
  FRIED_RICE_NOODLES("friedRiceAndNoodles"),
  RICE("rice"),
  ROTIS("rotis"),
  STARTERS("starters"),
  DRINKS("drinks");

  private final String type;

  LookupType(String type) {
    this.type = type;
  }

  public String getType() {
    return this.type;
  }

  public static LookupType get(String type) {
    return Arrays.stream(LookupType.values())
        .filter(val -> val.getType().equalsIgnoreCase(type))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Lookup type is invalid"));
  }

  public static Boolean isLookup(String type) {
    return Arrays.stream(LookupType.values())
            .filter(val -> val.getType().equalsIgnoreCase(type))
            .count()
        > 0;
  }
}
