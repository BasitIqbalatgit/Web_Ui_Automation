package com.se.tests.smoke;

import com.se.config.Constants;
import com.se.rolesbase.StudentLoginBase;
import com.se.utils.NavigationUtil;
import com.se.utils.UtilsSet;
import jdk.jshell.execution.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class StudentAccountTest extends StudentLoginBase {

    @Test(priority = 1)
    public void verifyStudentIsLoggedIn() {
        System.out.println("Starting verifyStudentIsLoggedIn test");
        try {
            int timeoutSeconds = 27;

            UtilsSet.waitForElementToBeVisible(Constants.Login.BY_hi_userName, timeoutSeconds);
            String loginText = UtilsSet.getElementText(Constants.Login.BY_hi_userName);
            System.out.println("Name of user who logged in is : " + loginText);

            String actualOutcome = loginText;
            String expectedOutcome = Constants.STUDENT_LOGIN_DETAILS.getUsername() + "!";
            Assert.assertEquals(actualOutcome, expectedOutcome, "Mismatch Username on the Navbar");
            Assert.assertNotNull(loginText, "User Name should not be Null");
            Assert.assertFalse(loginText.isEmpty(), "User Name should not be empty");

            System.out.println("verifyStudentIsLoggedIn test completed successfully");
        } catch (Exception e) {
            System.err.println("The navCoursesElement was not found or did not behave as expected.");
            Assert.fail("The navCoursesElement was not found or did not behave as expected.", e);
        }
    }

    @Test(dependsOnMethods = "verifyStudentIsLoggedIn", priority = 2)
    public void verifyLearnButtonIsShowingCourses() {
        System.out.println("Starting verifyLearnButtonIsShowingCourses test");
        NavigationUtil.clickLearnButton();
        System.out.println("Learn button is clicked");

        try {
            int timeoutSeconds = 27;

            UtilsSet.waitForElementToBeVisible(Constants.Learn.BY_navCourses, timeoutSeconds);
            String navCoursesText = UtilsSet.getElementText(Constants.Learn.BY_navCourses);
            System.out.println("Courses section is visible with text: " + navCoursesText);

            Assert.assertNotNull(navCoursesText, "navCoursesText should not be null");
            Assert.assertFalse(navCoursesText.isEmpty(), "navCoursesText should not be empty");
            Assert.assertEquals(navCoursesText, "Courses", "MisMatch text when the learn button is clicked");
            System.out.println("verifyLearnButtonIsShowingCourses test completed successfully");
        } catch (Exception e) {
            System.err.println("The navCoursesElement was not found or did not behave as expected.");
            Assert.fail("The navCoursesElement was not found or did not behave as expected.", e);
        }
    }

    @Test(dependsOnMethods = "verifyLearnButtonIsShowingCourses", priority = 3)
    public void verifyNavigationToSpecificCourse() {
        System.out.println("Starting verifyNavigationToSpecific Course Test");
        String subjectName = "Software Testing";
        NavigationUtil.openSubjectFromLearnMenu(subjectName);
        System.out.println("Software Testing course is clicked");
        try {
            int timeout = 30;

            UtilsSet.waitForElementToBeVisible(Constants.Learn.By_specificNameOfCourseAsTitle, timeout);
            String specificCourseTitle = UtilsSet.getElementText(Constants.Learn.By_specificNameOfCourseAsTitle);
            System.out.println("Specific course section is available with title : " + specificCourseTitle);

            Assert.assertNotNull(specificCourseTitle, "Course Title Should Not be null");
            Assert.assertFalse(specificCourseTitle.isEmpty(), "Course Title Should Not be Empty");

            String actualOutcome = specificCourseTitle;
            String expectedOutcome = subjectName;
            Assert.assertEquals(actualOutcome, expectedOutcome, "Mismatch Name of Specific course as Title");

            System.out.println("verifyNavigationToSpecificCourse test completed Successfully");

        } catch (Exception e) {
            System.err.println("The navCoursesElement was not found or did not behave as expected.");
            Assert.fail("The navCoursesElement was not found or did not behave as expected.", e);
        }
    }

    @Test(dependsOnMethods = "verifyNavigationToSpecificCourse", priority = 4)
    public void verifySubTabsAreClickable() {
        System.out.println("Starting verifySubTabsAreClickable test");

        try {
            // Find all child elements under the parentXPath
            List<WebElement> childElements = UtilsSet.findChildElements(Constants.Learn.By_subTabsParent);

            boolean allClickable = true;

            // Check each child element for clickability
            for (WebElement element : childElements) {
                boolean clickable = UtilsSet.isElementClickable(element);
                String clickableStatus = clickable ? "clickable" : "not clickable";
                System.out.println(element.getText() + "' is " + clickableStatus);
                allClickable = allClickable && clickable;
            }

            Assert.assertTrue(allClickable, "Not all Sub Tabs are clickable");

            System.out.println("verifySubTabsAreClickable test completed successfully");
        } catch (Exception e) {
            System.err.println("Failed to verify if Sub Tabs are clickable: " + e.getMessage());
            Assert.fail("Failed to verify if Sub Tabs are clickable", e);
        }
    }



    @Test(dependsOnMethods = "verifyNavigationToSpecificCourse", priority = 5)
    public void verifyLecturesAreAccessible() {
        System.out.println("Starting verifyLecturesAreAccessible test");
        NavigationUtil.clickLectureTab();

        try {
            // Find all child elements under the parentXPath
            List<WebElement> childElements = UtilsSet.findChildElements(Constants.Learn.By_subLecturesParent);

            boolean allClickable = true;

            // Check each child element for clickability
            for (WebElement element : childElements) {
                boolean clickable = UtilsSet.isElementClickable(element);
                String clickableStatus = clickable ? "clickable" : "not clickable";
                System.out.println(element.getText() + "' is " + clickableStatus);
                allClickable = allClickable && clickable;
            }

            Assert.assertTrue(allClickable, "Not all lectures are clickable");

            System.out.println("verifyLecturesAreAccessible test completed successfully");
        } catch (Exception e) {
            System.err.println("Failed to verify if lectures are clickable: " + e.getMessage());
            Assert.fail("Failed to verify if lectures are clickable", e);
        }
    }

//    @Test(dependsOnMethods = "verifyLecturesAreAccessible", priority = 6)
//    public void verifyLectureContentAreAccessible() {
//        System.out.println("Starting verifyLectureContentAreAccessible test");
//
//        try {
//            // Find all child elements under the parentXPath
//            List<WebElement> childElements = UtilsSet.findChildElements(Constants.Learn.By_subLecturesParent);
//
//            // Check each child element for clickability and validate the lecture content
//            for (WebElement element : childElements) {
//                UtilsSet.clickOnElement((By) element);
//
//                String dynamicPath = "//*[@id='" + element.getText() + "']";
//                String actualTitle = UtilsSet.getElementText(By.xpath(dynamicPath));
//
//                Assert.assertEquals(actualTitle, element.getText(), "Mismatch Lecture Titles");
//            }
//
//            System.out.println("verifyLectureContentAreAccessible test completed successfully");
//        } catch (Exception e) {
//            System.err.println("Failed to verify if lectures content are accessible: " + e.getMessage());
//            e.printStackTrace();  // Log the full stack trace
//            Assert.fail("Failed to verify if lectures content are accessible", e);
//        }
//    }
    @Test(priority = 6)
    public void verifyWelcomeToTrainStudent() {
        System.out.println("Starting verifyWelcomeToTrainStudent test");
        // Implement this test method as needed
    }
}
