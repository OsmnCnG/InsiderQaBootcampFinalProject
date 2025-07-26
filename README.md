# Insider QaBootcamp Final Project

## Overview
This project is an automated test suite developed for Insider's website(https://useinsider.com/).  
The tests cover the Quality Assurance job listings, including filtering by location and department, validating job details, and verifying redirection to the external Lever application page.

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
- TestNG
- Allure Reporting
- Maven for build and dependency management


---


## Screenshots in Allure Reports

This test automation framework is integrated with **Allure Reporting** to provide detailed and visually rich test reports

- During test execution, screenshots are automatically captured at key steps or upon failures
- These screenshots are attached to the Allure report, allowing quick visual verification of the application state
- This greatly aids in debugging by providing the exact page view when a test step passed or failed


---