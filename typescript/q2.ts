import { User } from "./q1";

export enum Status {
  Active,
  Inactive,
  Suspended,
}
export type UserStatus = [User, Status];

export function printUserStatus(UserStatus: UserStatus) {
  // console.log(UserStatus)
  const [user, statusIdx] = UserStatus;
  console.log(`${user.name} is currently ${Status[statusIdx]}.`);
}
