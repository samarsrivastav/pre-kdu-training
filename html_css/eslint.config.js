const html = require("@html-eslint/eslint-plugin");

module.exports = [
  {
    // recommended configuration included in the plugin
    ...html.configs["flat/recommended"],
    rules: {
      "@html-eslint/indent": ["error", 2],
    },
    files: ["src/*.html"],
  },
  {
    ignores: ["**/*.js"],
  },
];
