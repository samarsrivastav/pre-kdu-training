/*
  Define a type alias User with the following properties:
  id (number)
  name (string)
  email (string)
  role (string | undefined)

  Create an interface Admin that extends User and adds an additional property:
  permissions (array of strings)

  Write a generic function getProperty<T, K extends keyof T>(obj: T, key: K): T[K] that retrieves a property value of a given object by its key. 
  Use the keyof operator to ensure type safety.

  Make sure to export the User type alias, Admin interface, and getProperty function by adding the export keyword in front of them.
  So that the code can be tested in the test file.
*/
