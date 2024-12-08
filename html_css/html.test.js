const { readFileSync } = require('fs');

describe('HTML tags presence tests', () => {
    beforeAll(() => {
        // Load the HTML file
        const html = readFileSync('./src/index.html', 'utf8');
        document.body.innerHTML = html;
    });

    // List of HTML tags to test
    const tagsToCheck = ['header', 'main', 'section', 'article', 'footer'];

    tagsToCheck.forEach((tag) => {
        test(`Tag <${tag}> should be present in the document`, () => {
            const element = document.querySelector(tag);
            expect(element).not.toBeNull(); // Check the element exists
        });
    });
});
