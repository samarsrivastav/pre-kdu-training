package com.prekdu;

/*
 * Design a Library Management System that handles different types of library resources
(books, digital content, periodicals). The system should demonstrate(Use enums, classes, functions etc):

1. Create an abstract class LibraryResource that contains:
   - Protected fields for resourceId, title, and availabilityStatus
   - Abstract method calculateLateFee(int daysLate)
   - Abstract method getMaxLoanPeriod()

2. Implement different types of resources:
   - Book: Has additional fields for author and ISBN
   - DigitalContent: Has fields for fileSize and format (PDF, EPUB)
   - Periodical: Has fields for issueNumber and frequency (WEEKLY, MONTHLY)

3. Create an interface Renewable with method:
   - boolean renewLoan(LibraryMember member)

4. Create an interface Reservable with methods:
   - void reserve(LibraryMember member)
   - void cancelReservation(LibraryMember member)

5. Implement a LibraryMember class that:
   - Maintains a list of borrowed resources
   - Has a membership type (STANDARD, PREMIUM)
   - Has methods to borrow and return resources

6. Implement proper exception handling for:
   - ResourceNotAvailableException
   - MaximumLoanExceededException
 - InvalidMembershipException

Requirements:
- Books and Periodicals should be both Renewable and Reservable
- DigitalContent should only be Renewable
- Different resource types should have different late fees and loan periods
- Premium members should have higher loan limits and lower late fees and loan periods
- Implement proper validation and business logic

 */
