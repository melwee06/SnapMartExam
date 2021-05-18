# SnapMartExam

This Framework was created for SnapMart Exam.

This Framework was created using Java, Selenium, JUnit and ExtentReport


*Utilities

The Framework provides Utility Classes that contains code snippet for fetching element, wait element, validating page, check visibility, taking screenshots and handling test data.

These classes are located at snapmartexam.utils.


*Page Events

The Page Event Classes contains the logical process of automation test.

These classes handles different scenarios of your testing.

These are included on the snapmartexam.pageevents package.


*Test Classes

The Test Classes contains the test setup and test cases for your automation testing.

You could execute your tests using JUnit run test.

These are located at snapmartexam.test package.


*Usage

Data Driven Testing

You could create test data on Excel file for your testing.

The framework will read the values from an Excel file and then feed it to the test.

You could modify the path and file name from the test classes.

Headers on Excel should match the declared values from the HandleTestData.java class.

The framework will read the test data by rows.

You could add multiple rows on the Excel test data file so that you will be able to test for different types of input data.

Make sure your excel is clean and plain as possible, some modifications or formats might cause an error when using apache poi.

When validating blank input use space in excel cell.

Items/orders and their quantity should be separated by column.

When you try to input multiple values for items and their quantity, you could separate them by ~ (tilde).

Make sure that they are in the same order as you want.


Reports and Screenshots

Every test execution ExtentReport will generate an html report.

Reporst are located at reports folder.

On every report there are screenshots of the test and the status whether if it is Pass or Fail

Screenshots are located at screenshots folder.


*Maven

The framework could also be run using maven command: mvn clean test.


*Jenkins

A build can be run using jenkins by setting the command to locate the project directory and then run mvn command as well.
