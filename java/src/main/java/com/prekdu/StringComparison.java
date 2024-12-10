/**
 * This package contains classes.
 *
 * <p>Classes:
 *
 * <ul>
 *   <li>{@link com.prekdu.StringComparison} - Demonstrates collections usage.
 * </ul>
 */
package com.prekdu;

import java.util.Scanner;

public final class StringComparison {
  private StringComparison() {
    throw new UnsupportedOperationException("Utility class");
  }

  /**
   * The main method where the program execution begins.
   *
   * @param args command-line arguments (not used in this program)
   */
  public static void main(final String[] args) {

    Scanner sc = new Scanner(System.in);
    String s1 = sc.nextLine();
    String s2 = sc.nextLine();

    System.out.println("length of the first string : " + s1.length());
    System.out.println("length of the second string : " + s2.length());
    if (s1.length() == s2.length()) {
      System.out.println("length of both the strings are same");
    } else {
      System.out.println("length of both the strings are not same");
    }

    if (s1.equals(s2)) {
      System.out.println("Both the strings are same");
    } else {
      System.out.println("Both the strings are not same");
    }

    sc.close();
  }
}
