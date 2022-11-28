package utils;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class ExpectedConditionsHelper implements ExpectedCondition<Boolean> {

    private final ExpectedCondition<Boolean> innerCondition;
    private boolean expectedNoElement;

    private ExpectedConditionsHelper(ExpectedCondition<Boolean> condition) {
        innerCondition = condition;
    }

    private ExpectedConditionsHelper(ExpectedCondition<Boolean> condition, boolean noElement) {
        innerCondition = condition;
        expectedNoElement = noElement;
    }

    @NullableDecl
    @Override
    public Boolean apply(@NullableDecl WebDriver webDriver) {
        try {
            return innerCondition.apply(webDriver);
        } catch (NoSuchElementException noSuchElementException) {
            return expectedNoElement;
        }
    }

    public static ExpectedConditionsHelper setCondition(ExpectedCondition<Boolean> innerCondition) {
        return new ExpectedConditionsHelper(innerCondition);
    }

    public static ExpectedConditionsHelper setCondition(ExpectedCondition<Boolean> innerCondition, boolean expectedNoElement) {
        return new ExpectedConditionsHelper(innerCondition, expectedNoElement);
    }
}
