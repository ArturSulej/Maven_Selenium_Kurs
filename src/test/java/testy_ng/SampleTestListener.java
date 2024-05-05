package testy_ng;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class SampleTestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult iTestResult){
        System.out.println("I am taking a screenshot");
    }

    @Override
    public void onTestStart(ITestResult iTestResult){
        System.out.println("I am starting a test");
    }
}
