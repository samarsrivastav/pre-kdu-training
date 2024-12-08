import { describe, it, expect, jest } from "@jest/globals";
import { User } from "./q1";
import { printUserStatus, Status, UserStatus } from "./q2";

describe("printUserStatus", () => {
  it("should log the correct message for a given user and status", () => {
    const user: User = { id: 2, name: "Bob", email: "bob@example.com" };
    const userStatus: UserStatus = [user, Status.Active];

    const consoleSpy = jest.spyOn(console, "log").mockImplementation(() => {});
    printUserStatus(userStatus);

    expect(consoleSpy).toHaveBeenCalledWith("Bob is currently Active.");
    consoleSpy.mockRestore();
  });
});
