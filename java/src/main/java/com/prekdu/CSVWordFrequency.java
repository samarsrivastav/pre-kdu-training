package com.prekdu;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public final class CSVWordFrequency {
  /** the length required in question. */
  private static final int LENGTH = 3;

  private CSVWordFrequency() {
    throw new UnsupportedOperationException("Utility class");
  }

  /**
   * The main method where the program execution begins.
   *
   * @param args command-line arguments (not used in this program)
   */
  public static void main(final String[] args) {
    try {
      String file = "java/src/main/resources/input.csv";
      HashMap<String, Integer> mp = new HashMap<>();
      Scanner sc = new Scanner(new File(file));
      while (sc.hasNextLine()) {
        String[] temp = sc.nextLine().split(",");
        for (String x : temp) {
          mp.put(x, mp.getOrDefault(x, 0) + 1);
        }
      }
      List<Map.Entry<String, Integer>> arr = new ArrayList<>(mp.entrySet());
      arr.sort((a, b) -> b.getValue().compareTo(a.getValue()));

      for (int i = 0; i < Math.min(LENGTH, arr.size()); i++) {
        System.out.println(arr.get(i));
      }
      sc.close();
    } catch (Exception e) {
      System.err.println("error fetching file");
    }
  }
}
