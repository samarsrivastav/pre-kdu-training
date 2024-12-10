package com.prekdu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public final class CollectionsExample {

  /** Default size for the array. */
  private static final int DEFAULT_ARRAY_SIZE = 10;

  private CollectionsExample() {
    throw new UnsupportedOperationException("Utility class");
  }

  /**
   * The main method where the program execution begins.
   *
   * @param args command-line arguments (not used in this program)
   */
  public static void main(final String[] args) {

    Scanner sc = new Scanner(System.in);
    HashMap<String, Integer> mp = new HashMap<>();
    HashSet<String> st = new HashSet<>();
    ArrayList<String> arr = new ArrayList<>();
    for (int i = 0; i < DEFAULT_ARRAY_SIZE; i++) {
      String temp = sc.nextLine();
      arr.add(temp);
      st.add(temp);
      mp.put(temp, mp.getOrDefault(temp, 0) + 1);
    }
    // array output
    System.out.println("Elements inside arrayList: " + arr);
    // Map output
    for (Map.Entry<String, Integer> x : mp.entrySet()) {
      System.out.println(x.getKey() + " : " + x.getValue());
    }
    // HashSet output
    System.out.println("Elements inside HashSet: " + st);

    sc.close();
  }
}
