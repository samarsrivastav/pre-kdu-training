export type User = {
  id: number;
  name: string;
  email: string;
  role?: string | undefined;
};
export interface Admin extends User {
  permissions: string[];
}
export function getProperty<T, K extends keyof T>(obj: T, key: K): T[K] {
  return obj[key];
}
