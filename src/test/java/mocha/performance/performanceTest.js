const assert = require('assert');
const { describe, it } = require('mocha');
const puppeteer = require('puppeteer');
const path = require('path');
describe('Web Performance Test', function () {
    this.timeout(60000); // Set timeout to 60 seconds
    let testBrowser;
    let testPage;
    // Before running the test, launch the browser and create a new page.
    before(async function () {
        testBrowser = await puppeteer.launch();
        testPage = await testBrowser.newPage();
    });
    // After running the test, close the browser.
    after(async function () {
        await testBrowser.close();
    });
    // Test the memory usage of a given page.
    describe('Memory Test', function () {
        // Test the memory usage of the home.html page.
        describe('home.html memory', function () {
            it('should use less than 250MB of memory', async function () {
                // Get the path of the home.html page.
                const localIndexPagePath = '../../../../main/resources/templates/home.html';
                const indexPagePath = path.join(__dirname, localIndexPagePath);
                // Go to the home.html page and get the memory usage.
                await testPage.goto(`file://${indexPagePath}`);
                const performanceMetrics = await testPage.metrics();
                // Calculate the memory usage in MB.
                const memoryUsage = performanceMetrics.JSHeapTotalSize / 1024 / 1024;
                const memoryUsageString = `Memory usage is ${memoryUsage}MB`;
                console.log(memoryUsageString);
                // Assert that the memory usage is less than 250MB.
                assert(memoryUsage < 250, memoryUsageString);
            });
        });
    });
});