package orangehrm.pim;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.AddNewEmployeePO;
import pageObjects.DashboardPO;
import pageObjects.EmployeeListPO;
import pageObjects.LoginPO;
import pageObjects.PersonnalDetailPO;
import reportConfig.ExtentTestManager;

public class PIM_01_Employee extends BaseTest {
	WebDriver driver;
	private String browserName, employeeId;
	private LoginPO loginPage;
	private DashboardPO dashboardPage;
	private EmployeeListPO employeeListPage;
	private AddNewEmployeePO addNewEmployeePage;
	private PersonnalDetailPO personnalDetailPage;
	

	@Parameters({ "url", "browser" })
	@BeforeClass
	public void beforeClass(String url, String browserName) {
		driver = getWebDriver(browserName, url);
		this.browserName = browserName;
		loginPage = PageGeneratorManager.getLoginPO(driver);
		loginPage.enterToUsernameTextbox("hang_automationHR");
		loginPage.enterToPasswordTextbox("Hang95@***");
		dashboardPage = loginPage.clickToLoginBt();
		employeeListPage = dashboardPage.openEmployeeeList();

	}

	@Test
	public void Employee_01_add_new_employee(Method method) {
		ExtentTestManager.startTest(method.getName() + " Run on " + browserName, "User_01_Register_Success ");
		addNewEmployeePage = employeeListPage.clickToAddBt();
		addNewEmployeePage.enterToFirstnameTextbox("");
		addNewEmployeePage.enterToMiddlenameTextbox("");
		addNewEmployeePage.enterToLastnameTextbox("");
		employeeId = addNewEmployeePage.getEmployeeId();
		addNewEmployeePage.clickToSaveBt();
		Assert.assertTrue(addNewEmployeePage.isSucessMsgDisplayed("Successfully Saved"));
		
		personnalDetailPage = PageGeneratorManager.getPersonnalDetailPO(driver);
		Assert.assertEquals(personnalDetailPage.getFirstnameValue(), "");
		Assert.assertEquals(personnalDetailPage.getMiddlenameValue(), "");
		Assert.assertEquals(personnalDetailPage.getLastnameValue(), "");
		Assert.assertEquals(personnalDetailPage.getEmployeeIdValue(), employeeId);
		
		employeeListPage = personnalDetailPage.clickToEmployeeListBt();
		employeeListPage.enterToEmployeeIdSearchTextbox(employeeId);
		employeeListPage.clickToSearchBt();
		
		Assert.assertTrue(employeeListPage.isValueDisplayedAtColumnName("Id",employeeId));
		Assert.assertTrue(employeeListPage.isValueDisplayedAtColumnName("First (& Middle) Name",""));
		Assert.assertTrue(employeeListPage.isValueDisplayedAtColumnName("Last Name",""));
		
		
		
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowser();
	}

}
