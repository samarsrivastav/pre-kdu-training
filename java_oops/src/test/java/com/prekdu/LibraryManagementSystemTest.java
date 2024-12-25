package com.prekdu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Unit tests for the Library Management System. */
class LibraryManagementSystemTest {

  private LibraryMember standardMember;
  private LibraryMember premiumMember;
  private Book book;
  private DigitalContent digitalContent;

  /** Sets up the test environment by initializing test data. */
  @BeforeEach
  void setUp() {
    standardMember = new LibraryMember("STD001", MembershipType.STANDARD);
    premiumMember = new LibraryMember("PRE001", MembershipType.PREMIUM);
    book = new Book("B001", "Clean Code", "Robert Martin", "978-0132350884");
    digitalContent = new DigitalContent("D001", "Digital Design", 15.5, ContentFormat.PDF);
  }

  /** Tests late fee calculation for books. */
  @Test
  void testBookLateFeeCalculation() {
    assertEquals(5.0, book.calculateLateFee(10), 0.01);
  }

  /** Tests late fee calculation for digital content. */
  @Test
  void testDigitalContentLateFeeCalculation() {
    assertEquals(2.5, digitalContent.calculateLateFee(10), 0.01);
  }

  /** Tests that a standard member cannot borrow more than their maximum allowed resources. */
  @Test
  void testStandardMemberBorrowLimit() {
    assertThrows(
        MaximumLoanExceededException.class,
        () -> {
          for (int i = 0; i < 6; i++) {
            Book newBook = new Book("B00" + i, "Test Book " + i, "Author", "ISBN-" + i);
            standardMember.borrowResource(newBook);
          }
        });
  }

  /**
   * Tests that a premium member can borrow up to their maximum allowed resources.
   *
   * @throws MaximumLoanExceededException if the borrow limit is exceeded
   * @throws ResourceNotAvailableException if the resource is not available
   */
  @Test
  void testPremiumMemberBorrowLimit()
      throws MaximumLoanExceededException, ResourceNotAvailableException {
    for (int i = 0; i < 10; i++) {
      Book newBook = new Book("B00" + i, "Test Book " + i, "Author", "ISBN-" + i);
      premiumMember.borrowResource(newBook);
    }
    assertEquals(10, premiumMember.getBorrowedResources().size());
  }

  /**
   * Tests borrowing and returning of resources.
   *
   * @throws MaximumLoanExceededException if the borrow limit is exceeded
   * @throws ResourceNotAvailableException if the resource is not available
   */
  @Test
  void testBorrowAndReturn() throws MaximumLoanExceededException, ResourceNotAvailableException {
    standardMember.borrowResource(book);
    assertEquals(ResourceStatus.BORROWED, book.getStatus());
    assertEquals(1, standardMember.getBorrowedResources().size());

    standardMember.returnResource(book);
    assertEquals(ResourceStatus.AVAILABLE, book.getStatus());
    assertEquals(0, standardMember.getBorrowedResources().size());
  }

  /**
   * Tests reservation of a book by another member while it is borrowed.
   *
   * @throws MaximumLoanExceededException if the borrow limit is exceeded
   * @throws ResourceNotAvailableException if the resource is not available
   */
  @Test
  void testBookReservation() throws MaximumLoanExceededException, ResourceNotAvailableException {
    standardMember.borrowResource(book);
    LibraryMember anotherMember = new LibraryMember("STD002", MembershipType.STANDARD);

    book.reserve(anotherMember);
    assertFalse(book.renewLoan(standardMember));
  }

  /**
   * Tests that a resource cannot be borrowed by another member if it is already borrowed.
   *
   * @throws MaximumLoanExceededException if the borrow limit is exceeded
   * @throws ResourceNotAvailableException if the resource is not available
   */
  @Test
  void testResourceAvailability()
      throws MaximumLoanExceededException, ResourceNotAvailableException {

    standardMember.borrowResource(book);

    LibraryMember anotherMember = new LibraryMember("STD002", MembershipType.STANDARD);
    assertThrows(
        ResourceNotAvailableException.class,
        () -> {
          anotherMember.borrowResource(book);
        });
  }

  /**
   * Tests that digital content can always be renewed.
   *
   * @throws MaximumLoanExceededException if the borrow limit is exceeded
   * @throws ResourceNotAvailableException if the resource is not available
   */
  @Test
  void testDigitalContentRenewal()
      throws MaximumLoanExceededException, ResourceNotAvailableException {
    standardMember.borrowResource(digitalContent);
    assertTrue(digitalContent.renewLoan(standardMember));
  }

  /** Tests that reserving a book that is not borrowed throws an exception. */
  @Test
  void testInvalidReservation() {
    assertThrows(
        IllegalStateException.class,
        () -> {
          book.reserve(standardMember);
        });
  }
}
