import { describe, it, expect } from '@jest/globals';
import { Admin, getProperty } from './q1';

describe("getProperty", () => {
  it("should retrieve the correct property value from an object", () => {
    const admin: Admin = {
      id: 1,
      name: "Alice",
      email: "alice@example.com",
      role: "Manager",
      permissions: ["read", "write"],
    };

    const result = getProperty(admin, "permissions");
    expect(result).toEqual(["read", "write"]);
  });
});
