package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class EmployeeListPO extends BasePage{
	private WebDriver driver;

	public EmployeeListPO(WebDriver driver) {
		this.driver = driver;
	}

	public AddNewEmployeePO clickToAddBt() {
		// TODO Auto-generated method stub
		return null;
	}

	public void enterToEmployeeIdSearchTextbox(String employeeId) {
		// TODO Auto-generated method stub
		
	}

	public void clickToSearchBt() {
		// TODO Auto-generated method stub
		
	}

	public boolean isValueDisplayedAtColumnName(String string, String employeeId) {
		// TODO Auto-generated method stub
		return false;
	}

}
