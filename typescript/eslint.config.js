// @ts-check
import eslint from "@eslint/js";
import tseslint from "typescript-eslint";

export default [
  eslint.configs.recommended,
  ...tseslint.configs.recommended,
  {
    rules: {
      "no-undef": "warn",
      "@typescript-eslint/no-unused-expressions": [
        "error",
        {
          allowShortCircuit: true,
          allowTernary: true,
        },
      ],
    },
  },
  {
    languageOptions: {
      globals: {
        console: "readonly",
        document: "readonly",
        require: "readonly",
        module: "readonly",
      },
    },
  },
  {
    ignores: ["dist/", "cypress/", "cypress.config.js"],
  },
  {
    files: ["*.ts"],
  },
];
