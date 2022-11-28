package testrunner;

import dataset.Dataset;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.EMICalcPage;
import setup.Setup;

public class CalculateTestRunner extends Setup {
    EMICalcPage calcPage;

    @BeforeTest
    public void startEMICalculator() throws InterruptedException {
        calcPage = new EMICalcPage(driver);
        calcPage.btnStart.click();
        calcPage.btnStart.click();
        Thread.sleep(2000);
    }

    @Test(dataProvider = "data-provider", dataProviderClass = Dataset.class)
    public void doCalculateEMI(int loanAmount, double rInterest, int period, double pFee, int mEMI, int tInterest, int tpFee, int tPayment) throws InterruptedException {
        calcPage = new EMICalcPage(driver);
        calcPage.calculateEMI(loanAmount, rInterest, period, pFee);

        String monthlyEMI = calcPage.mEMI.getText();
        String totalInterest = calcPage.tInterest.getText();
        String totalProcessingFee = calcPage.tpFee.getText();
        String totalPayment = calcPage.tPayment.getText();

        monthlyEMI = String.valueOf((int) Math.round(Double.parseDouble(monthlyEMI.replace(",", ""))));
        totalInterest = String.valueOf((int) Math.round(Double.parseDouble(totalInterest.replace(",", ""))));
        totalProcessingFee = String.valueOf((int) Math.round(Double.parseDouble(totalProcessingFee.replace(",", ""))));
        totalPayment = String.valueOf((int) Math.round(Double.parseDouble(totalPayment.replace(",", ""))));

        Assert.assertEquals(monthlyEMI, String.valueOf(mEMI));
        Assert.assertEquals(totalInterest, String.valueOf(tInterest));
        Assert.assertEquals(totalProcessingFee, String.valueOf(tpFee));
        Assert.assertEquals(totalPayment, String.valueOf(tPayment));
        Allure.description("Calculation Assertion");

        calcPage.btnReset.click();

    }

}
