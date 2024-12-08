const html = require("@html-eslint/eslint-plugin");
const js = require("@eslint/js");

module.exports = [
  js.configs.recommended,
  {
    rules: {
      "no-undef": "warn",
    },
  },
  {
    // recommended configuration included in the plugin
    ...html.configs["flat/recommended"],
    rules: {
      "@html-eslint/indent": ["error", 2],
    },
    files: ["src/*.html"],
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
    files: ["src/*.js"],
  },
];
