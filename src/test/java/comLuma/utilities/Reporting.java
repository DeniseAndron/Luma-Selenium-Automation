package comLuma.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;


public class Reporting extends TestListenerAdapter {

    ExtentSparkReporter htmlReporter = new ExtentSparkReporter("Spark.html");

    public ExtentReports extent;
    public ExtentTest logger;

    public void onStart(ITestContext testContext) {

        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM/uuuu");
        OffsetDateTime dateTime = OffsetDateTime.now(); // You can replace this with your specific date logic
        String monthYear = dateTime.format(monthFormatter);

        String repName = "Test-Report-" + monthYear + ".html";


        htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/" + repName); //specify location

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host name", "localhost");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("user", "Denise");

        htmlReporter.config().setDocumentTitle("Luma Test Project"); // Tile of report
        htmlReporter.config().setReportName("Functional Test Automation Report"); // name of the report
        htmlReporter.config().setTheme(Theme.DARK);
    }

    public void onTestSuccess(ITestResult tr) {
        logger = extent.createTest(tr.getName()); //create new entry in the report
        logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN)); //send the passed information to the report with GREEN color highlighted

        String screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + tr.getName() + ".png";
        File f = new File(screenshotPath);

        if (f.exists()) {
            logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
        }
    }

    public void onTestSkipped(ITestResult tr) {
        logger = extent.createTest(tr.getName()); //create new entry in the report
        logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
    }

    public void onFinish(ITestContext testContext) {
        extent.flush();
    }
}