const assert = require('assert');
const { describe, it } = require('mocha');
const puppeteer = require('puppeteer');
const path = require('path');

describe('Home page load speed test', function () {

    describe('home.html load time', function () {
        it('should load within 4 seconds', async function () {
            // relative path to home.html
            const localIndexPagePath = '../../../../main/resources/templates/home.html';
            const indexPagePath = path.join(__dirname, localIndexPagePath);
            const pageUrl = `file://${indexPagePath}`;

            // time at which the test started
            const startTime = Date.now();

            // this waits till page is loaded
            await testPage.goto(pageUrl, { waitUntil: 'networkidle0' });

            // calculate the website load time in seconds
            const loadTime = (Date.now() - startTime) / 1000;
            const loadTimeString = `Load time is ${loadTime} seconds`;
            console.log(loadTimeString);

            // this checks if the page successfully loads in under 4 seconds
            assert(loadTime < 4, loadTimeString);
        });
    });
});