import { describe, it, expect } from '@jest/globals';
import { User } from './q1';
import { findById } from './q4';

describe("findById", () => {
    const userArray: User[] = [
      { id: 1, name: "Alice", email: "alice@example.com" },
      { id: 2, name: "Bob", email: "bob@example.com" },
    ];
  
    it("should return a user if found", () => {
      const result = findById(userArray, 1);
      expect(result).toEqual({ id: 1, name: "Alice", email: "alice@example.com" });
    });
  
    it("should return undefined if user is not found", () => {
      const result = findById(userArray, 3);
      expect(result).toBeUndefined();
    });
  
    it("should throw an error if user is not found and throwOnError is true", () => {
      expect(() => findById(userArray, 3, true)).toThrow("User with ID 3 not found");
    });
  });
  