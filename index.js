const report = require('multiple-cucumber-html-reporter');
const path = require('path');

// Detectar si estamos en CI (GitHub Actions define CI=true)
const isCI = process.env.CI === 'true';

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
        device: isCI ? 'GitHub Actions' : 'Local machine',
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

// 👉 SOLO abrir el reporte si es ejecución local
if (!isCI) {
    const open = require('open');

    setTimeout(() => {
        open(reportFile, { wait: false });
        console.log('✅ Report opened automatically (local)');
    }, 2000);
} else {
    console.log('ℹ️ CI environment detected – report generated but not opened');
}
