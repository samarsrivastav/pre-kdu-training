describe("Counter App", () => {
  beforeEach(() => {
    cy.visit("./counter/index.html"); // Update the path to your file
  });

  it("should display the initial counter value as 0", () => {
    cy.get("#counter-value").should("contain", "0");
  });

  it('should increment the counter when "+" is clicked', () => {
    cy.get("#increment").click();
    cy.get("#counter-value").should("contain", "1");
  });

  it('should decrement the counter when "-" is clicked', () => {
    cy.get("#decrement").click();
    cy.get("#counter-value").should("contain", "-1");
  });
});

describe("Calculator App", () => {
  beforeEach(() => {
    cy.visit("./calculator/index.html"); // Update the path to your file
  });

  it("should add two numbers correctly", () => {
    cy.get("#num1").type("5");
    cy.get("#num2").type("3");
    cy.get("#add").click();
    cy.on("window:alert", (str) => {
      expect(str).to.equal("Result: 8");
    });
  });

  it("should subtract two numbers correctly", () => {
    cy.get("#num1").type("10");
    cy.get("#num2").type("4");
    cy.get("#subtract").click();
    cy.on("window:alert", (str) => {
      expect(str).to.equal("Result: 6");
    });
  });

  it("should multiply two numbers correctly", () => {
    cy.get("#num1").type("6");
    cy.get("#num2").type("7");
    cy.get("#multiply").click();
    cy.on("window:alert", (str) => {
      expect(str).to.equal("Result: 42");
    });
  });

  it("should divide two numbers correctly", () => {
    cy.get("#num1").type("20");
    cy.get("#num2").type("5");
    cy.get("#divide").click();
    cy.on("window:alert", (str) => {
      expect(str).to.equal("Result: 4");
    });
  });

  it("should alert error when dividing by zero", () => {
    cy.get("#num1").type("20");
    cy.get("#num2").type("0");
    cy.get("#divide").click();
    cy.on("window:alert", (str) => {
      expect(str).to.equal("Cannot divide by zero");
    });
    Cypress.on("uncaught:exception", (err, runnable) => {
      // returning false here prevents Cypress from
      // failing the test
      return false;
    });
  });
});
