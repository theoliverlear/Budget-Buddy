const assert = require('assert');
const { describe, it } = require('mocha');
const { XMLHttpRequest } = require("xmlhttprequest");
describe('Graph', function() {
    // Testing for Pie Charts
    describe('Pie Chart', function () {
        // Assert that the response is not null.
        it('should return a not null object', function () {
            let response = null;
            // Make a POST request to the server for a pie chart.
            let request = new XMLHttpRequest();
            request.open('POST' , 'http://localhost:8443/graph/view/pie/default');
            request.onload = function() {
                response = request.responseText;
                console.log(response);
                // Assert that the response is not null.
                assert.notEqual(response, null);
            }
            request.send();
        });
    });
});