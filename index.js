const report = require('multiple-cucumber-html-reporter');
const open = require('open');
const path = require('path');

const reportFile = path.resolve(
  'target/cucumber-html-report/index.html'
);

report.generate({
    jsonDir: 'target/cucumber-report',
    reportPath: 'target/cucumber-html-report',

    metadata: {
        browser: {
            name: 'chrome',
            version: 'latest'
        },
        device: 'Local machine',
        platform: {
            name: process.platform
        }
    },

    customData: {
        title: 'Run info',
        data: [
            { label: 'Project', value: 'Proyecto POM Selenium' },
            { label: 'Generated on', value: new Date().toLocaleString() }
        ]
    }
});

// Espera segura y abre
setTimeout(() => {
    open(reportFile, { wait: false });
    console.log('✅ Report opened automatically');
}, 2000);
