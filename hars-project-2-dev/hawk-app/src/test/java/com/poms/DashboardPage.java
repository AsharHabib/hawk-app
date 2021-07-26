package com.poms;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
	@FindBy(css=".number > a:nth-child(2)")
	public WebElement seatmapLink;
	@FindBy(css = "li.nav-item:nth-child(3) > a:nth-child(1)")
	public WebElement logOutLink;
	
	public DashboardPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void clickSeatmapLink() {
		this.seatmapLink.click();
	}
	public void clickLogOut(){this.logOutLink.click();}
}
