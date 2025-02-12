import { User } from "./q1";
export function findById(
  userArray: User[],
  id: number,
  handleUndefined?: boolean
): User | undefined {
  const user = userArray.find((user) => user.id === id);
  if (!user && handleUndefined) {
    throw new Error(`User with ID ${id} not found`);
  } else if (handleUndefined) {
    return undefined;
  } else if (user) {
    return user;
  }
}
/*
  Create a utility function findById that takes an array of User objects and a number as arguments and returns a User | undefined (if the user with the given ID exists).
  Extend the function to support a new parameter, which specifies if the returned user should include undefined or throw an error. Use TypeScript's never type to represent the error-throwing case.
  Test the function using a sample array of User objects and handle both scenarios (with and without throwing an error).

  Export the function findById so that it can be used in the test file.
*/
