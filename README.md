# UI Automation Project

This project is a sample UI automation framework using Java, Selenium WebDriver, TestNG, and ExtentReports.

## Project Structure

```
ui-automation/
├── pom.xml
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/ui/automation/
│   │           (empty)
│   └── test/
│       ├── java/
│       │   └── com/ui/automation/
│       │       ├── Base/
│       │       │   └── BaseTest.java
│       │       ├── Tests/
│       │       │   └── AppTest.java
│       │       └── Utilities/
│       │           ├── DriverFactory.java
│       │           ├── ExtentReportManager.java
│       │           ├── RetryAnalyzer.java
│       │           ├── ScreenshotUtil.java
│       │           └── TestNGListener.java
│       └── resources/
├── testng.xml
└── test-output/
```

## Key Features
- **Selenium WebDriver** for browser automation
- **TestNG** for test orchestration and reporting
- **ExtentReports** for rich HTML reports with screenshots
- **Automatic screenshot capture on test failure** (via `TestNGListener`)
- **Manual screenshot capture at any test step** (via `ScreenshotUtil`)
- **Retry logic** for flaky tests (`RetryAnalyzer`)

## How to Run Tests

1. **Install dependencies:**
   - Make sure you have Java and Maven installed.
   - Run `mvn clean install` to download dependencies and build the project.

2. **Run tests:**
   - Execute `mvn test` or run tests via your IDE.
   - Test results and reports will be generated in the `test-output/` directory.

3. **View Reports:**
   - Open `test-output/ExtentReport.html` in your browser to view the test report with screenshots.

## Screenshot & Reporting Utilities
- **Automatic Failure Screenshots:**
  - On any test failure, a screenshot is captured and attached to the report automatically by the `TestNGListener`.
- **Manual Step Screenshots:**
  - Call `ScreenshotUtil.captureAndAttachScreenshot("Step description")` in your test to capture and attach a screenshot at any point.
- **See `ScreenshotUtil.java` for more details on usage and design.**

## Customization
- Update `DriverFactory` to use different browsers or capabilities as needed.
- Modify `testng.xml` to configure test suites and listeners.

## License
This project is for demonstration and educational purposes. 