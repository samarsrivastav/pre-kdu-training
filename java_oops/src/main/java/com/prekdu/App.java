/** This package contains the main application logic. */
package com.prekdu;

import java.util.ArrayList;
import java.util.List;

/** Enum representing membership types for library members. */
enum MembershipType {
  /** Standard membership type. */
  STANDARD,
  /** Premium membership type. */
  PREMIUM
}

/** Enum representing the status of a library resource. */
enum ResourceStatus {
  /** Resource is available for borrowing. */
  AVAILABLE,
  /** Resource is currently borrowed. */
  BORROWED,
  /** Resource is reserved by a member. */
  RESERVED
}

/** Enum representing the content format for digital content. */
enum ContentFormat {
  /** PDF format. */
  PDF,
  /** EPUB format. */
  EPUB
}

/** Exception thrown when a resource is not available for borrowing. */
class ResourceNotAvailableException extends Exception {
  ResourceNotAvailableException(final String message) {
    super(message);
  }
}

/** Exception thrown when a library member exceeds the maximum loan limit. */
class MaximumLoanExceededException extends Exception {
  MaximumLoanExceededException(final String message) {
    super(message);
  }
}

/** Exception thrown when a library member has an invalid membership type. */
class InvalidMembershipException extends Exception {
  InvalidMembershipException(final String message) {
    super(message);
  }
}

/** Interface for resources that can be renewed. */
interface Renewable {
  /**
   * Renews the loan for a library resource.
   *
   * @param member The library member attempting to renew the loan.
   * @return True if the loan is successfully renewed; otherwise, false.
   */
  boolean renewLoan(LibraryMember member);
}

/** Interface for resources that can be reserved. */
interface Reservable {
  /**
   * Reserves the resource for a library member.
   *
   * @param member The library member reserving the resource.
   */
  void reserve(LibraryMember member);

  /**
   * Cancels the reservation for a library member.
   *
   * @param member The library member canceling the reservation.
   */
  void cancelReservation(LibraryMember member);
}

/** Abstract base class representing a library resource. */
abstract class LibraryResource {

  /** resource id. */
  private String resourceId;

  /** title. */
  private String title;

  /** status. */
  private ResourceStatus status;

  /** currentBorrower. */
  private LibraryMember currentBorrower;

  /** reservedBy. */
  private LibraryMember reservedBy;

  /**
   * getting details of Library Resource id.
   *
   * @return resourceId
   */
  public String getResourceId() {
    return resourceId;
  }

  /**
   * getting details of Library Resource title. *
   *
   * @return getTitle
   */
  public String getTitle() {
    return title;
  }

  /**
   * getting details of Library Resource Current Borrower.
   *
   * @return currentBorrower
   */
  public LibraryMember getCurrentBorrower() {
    return currentBorrower;
  }

  /**
   * getting details of Library Resource Reserved By.
   *
   * @return reservedBy
   */
  public LibraryMember getReservedBy() {
    return reservedBy;
  }

  /**
   * setter for reserved by.
   *
   * @param r is reserved by
   */
  public void setReservedBy(final LibraryMember r) {
    this.reservedBy = r;
  }

  /**
   * Constructor for LibraryResource.
   *
   * @param id the unique ID of the resource
   * @param t the title of the resource
   */
  LibraryResource(final String id, final String t) {
    this.resourceId = id;
    this.title = t;
    this.status = ResourceStatus.AVAILABLE;
  }

  /**
   * Gets the status of the resource.
   *
   * @return The current status of the resource.
   */
  public ResourceStatus getStatus() {
    return status;
  }

  /**
   * setter for status.
   *
   * @param s is status
   */
  public void setStatus(final ResourceStatus s) {
    this.status = s;
  }

  /**
   * Calculates the late fee for the resource.
   *
   * @param daysLate The number of days the resource is late.
   * @return The calculated late fee.
   */
  public abstract double calculateLateFee(int daysLate);

  /**
   * Gets the maximum loan period for the resource.
   *
   * @return The maximum loan period in days.
   */
  public abstract int getMaxLoanPeriod();
}

/** Represents a book resource in the library. */
class Book extends LibraryResource implements Renewable, Reservable {
  /** book late fee. */
  private static final double BOOK_LATE_FEE_PER_DAY = 0.5;

  /** Loan Period. */
  private static final int LOAN_PERIOD = 28;

  /** author. */
  private String author;

  /** isbn. */
  private String isbn;

  /**
   * Book constructor.
   *
   * @param r is the resource id of the book
   * @param t is the title of the book
   * @param a is the author of the book
   * @param i is the isbn of the book
   */
  Book(final String r, final String t, final String a, final String i) {
    super(r, t);
    this.author = a;
    this.isbn = i;
  }

  /** Prints the book details. */
  public void printBookDetails() {
    System.out.println("Author: " + author);
    System.out.println("ISBN: " + isbn);
  }

  @Override
  public double calculateLateFee(final int daysLate) {
    return daysLate * BOOK_LATE_FEE_PER_DAY; // $0.50 per day late
  }

  @Override
  public int getMaxLoanPeriod() {
    return LOAN_PERIOD; // 3 weeks
  }

  @Override
  public boolean renewLoan(final LibraryMember member) {
    return getStatus() == ResourceStatus.BORROWED
        && getCurrentBorrower().equals(member)
        && (getReservedBy() == null || getReservedBy().equals(member));
  }

  @Override
  public void reserve(final LibraryMember member) {
    if (getStatus() != ResourceStatus.BORROWED) {
      throw new IllegalStateException("Resource must be borrowed");
    }
    if (getReservedBy() != null) {
      throw new IllegalStateException("Resource is already reserved");
    }
    setReservedBy(member);
    setStatus(ResourceStatus.RESERVED);
  }

  @Override
  public void cancelReservation(final LibraryMember member) {
    if (getReservedBy() != null && getReservedBy().equals(member)) {
      setReservedBy(null);
      if (getStatus() == ResourceStatus.RESERVED) {
        setStatus(ResourceStatus.BORROWED);
      }
    }
  }

  public String getAuthor() {
    return author;
  }

  public String getIsbn() {
    return isbn;
  }
}

/** Represents digital content in the library. */
class DigitalContent extends LibraryResource implements Renewable {
  /** DIGITAL LATE FEE. */
  private static final double DIGITAL_LATE_FEE = 0.25;

  /** DIGITAL LOAN PERIOD. */
  private static final int DIGITAL_LOAN_PERIOD = 14;

  /** filesize. */
  private double fileSize;

  /** file format. */
  private ContentFormat format;

  /**
   * Digital content Constructor.
   *
   * @param i is the resource id
   * @param t is the title of the resource
   * @param s is the size of the resource
   * @param f is the format of the resource
   */
  DigitalContent(
      final String i, // CHECKSTYLE:OFF
      final String t, // CHECKSTYLE:OFF
      final double s, // CHECKSTYLE:OFF
      final ContentFormat f // CHECKSTYLE:OFF
      ) {
    super(i, t);
    this.fileSize = s;
    this.format = f;
  }

  /** Prints the digital content details. */
  public void printDigitalContent() {
    System.out.println("File Size: " + fileSize);
    System.out.println("Format: " + format);
  }

  @Override
  public double calculateLateFee(final int daysLate) {
    return daysLate * DIGITAL_LATE_FEE; // $0.25 per day late
  }

  @Override
  public int getMaxLoanPeriod() {
    return DIGITAL_LOAN_PERIOD; // 2 weeks
  }

  @Override
  public boolean renewLoan(final LibraryMember member) {
    return true; // Digital content can always be renewed
  }
}

/** Represents a library member. */
class LibraryMember {
  /** standard borrow limit. */
  private static final int S_L = 5;

  /** premium borrow limit. */
  private static final int P_L = 10;

  /** memberId. */
  private String memberId;

  /** memberType. */
  private MembershipType membershipType;

  /** resources that are borrowed. */
  private List<LibraryResource> borrowedResources;

  /**
   * Library Member Constructor.
   *
   * @param id is the memberid
   * @param mt is the membership type
   */
  LibraryMember(final String id, final MembershipType mt) {
    this.memberId = id;
    this.membershipType = mt;
    this.borrowedResources = new ArrayList<>();
  }

  /** Gets the member ID. */
  public void printMemberDetail() {
    System.out.println("ID: " + memberId);
  }

  /**
   * Borrows a resource for the member.
   *
   * @param resource The resource to borrow.
   * @throws MaximumLoanExceededException If the borrow limit is exceeded.
   * @throws ResourceNotAvailableException If the resource is not available.
   */
  public void borrowResource(final LibraryResource resource)
      throws MaximumLoanExceededException, ResourceNotAvailableException {
    int maxBorrowLimit = (membershipType == MembershipType.PREMIUM) ? P_L : S_L;

    if (borrowedResources.size() >= maxBorrowLimit) {
      throw new MaximumLoanExceededException("Maximum loan limit exceeded");
    }

    if (resource.getStatus() != ResourceStatus.AVAILABLE) {
      throw new ResourceNotAvailableException("Resource is not available");
    }

    resource.setStatus(ResourceStatus.BORROWED);
    borrowedResources.add(resource);
  }

  /**
   * Returns a resource.
   *
   * @param resource The resource to return.
   */
  public void returnResource(final LibraryResource resource) {
    borrowedResources.remove(resource);
    resource.setStatus(ResourceStatus.AVAILABLE);
    if (resource.getReservedBy() != null) {
      resource.setStatus(ResourceStatus.RESERVED);
    }
  }

  /**
   * Gets the list of borrowed resources.
   *
   * @return A list of borrowed resources.
   */
  public List<LibraryResource> getBorrowedResources() {
    return new ArrayList<>(borrowedResources);
  }
}
