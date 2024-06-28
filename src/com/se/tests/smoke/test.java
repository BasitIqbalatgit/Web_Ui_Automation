package com.se.tests.smoke;

import com.se.config.Constants;
import com.se.rolesbase.StudentLoginBase;
import com.se.utils.NavigationUtil;
import com.se.utils.UtilsSet;
import org.testng.Assert;
import org.testng.annotations.Test;

public class test extends StudentLoginBase {


    @Test
    public void verifyStudentIsLoggedIn(){


        System.out.println("A Student is now logged in");
    }

    @Test
    public void verifyWelcomeToTrainStudent(){

    }

    @Test(dependsOnMethods = "verifyStudentIsLoggedIn")
    public void verifyDueExameButtonIsClicked() {
        System.out.println("Starting verifyDueExamButtonIsClicked test");
        NavigationUtil.clickDueExameButton();
        System.out.println("Due Exam button is clicked");

        try {
            int timeoutSeconds = 27;

            UtilsSet.waitForElementToBeVisible(Constants.DueExame.BY_dueExameSection, timeoutSeconds);
            String dueExameSectionText = UtilsSet.getElementText(Constants.DueExame.BY_dueExameSection);
            System.out.println("Due Exame section is visible with text: " + dueExameSectionText);

//            Assert.assertNotNull(dueExameSectionText, "dueExameSectionText should not be null");
            Assert.assertFalse(dueExameSectionText.isEmpty(), "dueExameSectionText should not be empty");
            Assert.assertEquals(dueExameSectionText, "Exams", "Mismatch text when the Due Exame button is clicked");
            System.out.println("verifyDueExameButtonIsClicked test completed successfully");
        } catch (Exception e) {
            System.err.println("The dueExameSectionElement was not found or did not behave as expected.");
            Assert.fail("The dueExameSectionElement was not found or did not behave as expected.", e);
        }

    }

    @Test(dependsOnMethods = "verifyDueExameButtonIsClicked")
    public void verifyExamTitleLinkIsClicked() {
        System.out.println("Starting verifyExameTitleLinkIsClicked test");
        NavigationUtil.clickQuizTitleLink();
        System.out.println("The quiz title link is clicked");

        try{
            int timeoutSeconds = 15;

            UtilsSet.waitForElementToBeVisible(Constants.DueExame.BY_examSummarySection, timeoutSeconds);
            String examSummarySectionText = UtilsSet.getElementText(Constants.DueExame.BY_examSummarySection);
            System.out.println("Exam Summary section is visible with text: " + examSummarySectionText);
            Assert.assertFalse(examSummarySectionText.isEmpty(), "examSummarySection should not be empty");
            Assert.assertEquals(examSummarySectionText, "Exam Summary", "Mismatch text when the exam title link is clicked");
            System.out.println("verifyExamTitleLinkIsClicked test completed successfully");
        } catch (Exception e) {
            System.err.println("The verifyExamTitleLink was not found or did not behave as expected.");
            Assert.fail("The verifyExamTitleLink was not found or did not behave as expected.", e);
        }
    }

}