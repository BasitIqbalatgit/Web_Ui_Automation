package com.se.utils;

import com.se.config.Constants;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.yaml.snakeyaml.scanner.Constant;

import static com.se.utils.UtilsSet.clickOnElement;

public class NavigationUtil {
    @Step("Opening a subject base on {0}.")
    public static void openSubjectFromLearnMenu(String subName) {
        clickOnElement(By.linkText(subName));
    }

    public static void clickLearnButton() {
        // Use appropriate locator for the "Learn" button

        UtilsSet.clickOnElement(Constants.Learn.BY_learn);

    }

    public static void clickLectureTab() {
        // Use appropriate locator for the "Learn" button

        UtilsSet.clickOnElement(Constants.Learn.By_LectureTab);

    }
    public static void clickTopicTab() {
        // Use appropriate locator for the "Learn" button

        UtilsSet.clickOnElement(Constants.Learn.By_TopicTab, "Topic Tab is not Clickable");

    }public static void clickAssignmentTab() {
        // Use appropriate locator for the "Learn" button

        UtilsSet.clickOnElement(Constants.Learn.By_AssignmentTab, "Assignemnt Tab is not Clickable");

    }

    @Step("Clicking on the Due Exame button.")
    public static void clickDueExameButton() {
        clickOnElement(Constants.DueExame.BY_dueExameButton);
    }
    @Step("Clicking on the quiz title link.")
    public static void clickQuizTitleLink() {
        clickOnElement(Constants.DueExame.BY_examTitleLink);
    }


}
