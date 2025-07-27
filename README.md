# Insider QaBootcamp Final Project

## Overview

This project is an automated test suite developed for Insider's website (https://useinsider.com/).  
It focuses on verifying Quality Assurance job listings by checking location and department filters, validating job details, and ensuring correct redirection to the external Lever application page.

The test suite is integrated with a Jenkins pipeline, allowing tests to be executed automatically on each code push.

---
## Test Run Demo

Watch the test execution and Allure report generation here:  
[https://www.loom.com/share/0a2eef4be499426f9a55265424258ff5?sid=0b909948-2c20-41ac-9e61-ae3a6fcb398a](https://www.loom.com/share/0a2eef4be499426f9a55265424258ff5?sid=0b909948-2c20-41ac-9e61-ae3a6fcb398a)

---

## Features
- Navigate through Insider's main and careers pages
- Apply filters for job location and department
- Verify job listings contain expected information (position, department, location)
- Click on "View Role" buttons to validate redirection to Lever job application pages
- Take screenshots for reporting and debugging
- Supports running tests on Chrome browser with Selenium WebDriver and TestNG
- Integrated with Allure for detailed reporting

---

## Project Structure
- `Pages/` - Page Object Model classes for different pages
- `test/` - Test classes for different test scenarios

---

## Technologies
- Java
- Selenium WebDriver
- Allure Reporting
- Jenkins
- TestNG
- Maven for build and dependency management


---

## CI/CD Pipeline

This project uses **Jenkins** for CI/CD.

- A GitHub webhook triggers the Jenkins pipeline on each push.
- Jenkins runs tests and generates an **Allure** report.
- During development, Jenkins runs locally, and the webhook endpoint is exposed to the internet using **Ngrok**.

This setup ensures that test results are always up to date and the project remains stable throughout the development process.


---

## Screenshots in Allure Reports

This test automation framework is integrated with **Allure Reporting** to provide detailed and visually rich test reports

- During test execution, screenshots are automatically captured at key steps or upon failures
- These screenshots are attached to the Allure report, allowing quick visual verification of the application state
- This greatly aids in debugging by providing the exact page view when a test step passed or failed


---