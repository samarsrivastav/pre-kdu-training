import { describe, it, expect } from '@jest/globals';
import { filterArray } from './q3';
import { User } from './q1';

describe("filterArray", () => {
    it("should filter numbers correctly", () => {
      const numbers = [1, 2, 3, 4, 5];
      const result = filterArray(numbers, (num) => num % 2 === 0);
      expect(result).toEqual([2, 4]);
    });
  
    it("should filter users with company emails correctly", () => {
      const users: User[] = [
        { id: 1, name: "Alice", email: "alice@company.com" },
        { id: 2, name: "Bob", email: "bob@example.org" },
      ];
      const result = filterArray(users, (user) => user.email.includes("@company.com"));
      expect(result).toEqual([{ id: 1, name: "Alice", email: "alice@company.com" }]);
    });
  });
  