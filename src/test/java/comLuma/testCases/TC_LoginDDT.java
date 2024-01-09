package comLuma.testCases;

import comLuma.pageObjects.LoginPage;
import comLuma.utilities.XLUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_LoginDDT  extends  BaseClass
{
    @Test(dataProvider = "LoginData")
    public void loginDDT(String user, String pwd) {
        LoginPage lp = new LoginPage(driver);

        lp.goToLoginPage();
        lp.setEmailAddress(user);
        logger.info("User name provided");

        lp.setPassword_field(pwd);
        logger.info("Password provided");

        lp.signInButton();

        // Check for invalid credentials

        String invalidErrorMessage = lp.getInvalidEmailErrorMessage();
        if (invalidErrorMessage != null) {
            Assert.assertEquals(invalidErrorMessage, "Please enter a valid email address (Ex: johndoe@domain.com).");
        } else {
            String expectedSuccessMessage = "Welcome, Denisa Andron!"; // Modify this based on your actual success message

            Assert.assertEquals(lp.getSuccessMessage(), expectedSuccessMessage);
            lp.signOut();
        }
    }


    @DataProvider(name="LoginData")
    String [] [] getData() throws IOException {

        String path = System.getProperty("user.dir")+"/src/test/java/comLuma/testData/LoginData.xlsx";
        //Read the data from xlsx file

        int rownum = XLUtils.getRowCount(path,"Sheet1");
        int colcount = XLUtils.getCellCount(path,"Sheet1",1);

        //two-dimensional array
        String logindata[][] = new String[rownum][colcount];

        //for loop to get the actual data

        for (int i=1; i<=rownum; i++)
        {
            for (int j=0; j<colcount; j++)
            {
                logindata[i-1][j] = XLUtils.getCellData(path,"Sheet1",i,j); //1 0
            }
        }

        return logindata;
    }

}
