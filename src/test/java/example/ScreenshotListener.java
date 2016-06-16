package example;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ScreenshotListener implements ITestListener {

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailure(ITestResult result) {
		try {
			final WebDriver webDriver = AbstractTestBase.driver;
			final File screenshotFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
			final String currentDateTime = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
			final String fileName = currentDateTime + "_" + result.getName();
			final File targetFile = new File("build/reports/tests/html/screenshots/" + fileName + ".png");

			FileUtils.copyFile(screenshotFile, targetFile);

			result.setAttribute("screenshot", "<a target='blank' href='../screenshots/" + fileName + ".png'>Screenshot</a>");
			Reporter.setCurrentTestResult(result);
			Reporter.log("<a target='blank' href='screenshots/" + fileName + ".png'> Screenshot</a>\n");
		} catch (IOException e) {
			System.err.println("Can't take screenshot");
		}
	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

}
