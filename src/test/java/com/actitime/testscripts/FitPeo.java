package com.actitime.testscripts;



	import java.time.Duration;

import org.monte.screenrecorder.ScreenRecorder;
import org.monte.screenrecorder.ScreenRecorderCompactMain;
import org.monte.screenrecorder.ScreenRecorderMain;
import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.Keys;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.interactions.Actions;
	import org.testng.Assert;
	import org.testng.annotations.Test;
	import org.testng.asserts.SoftAssert;


	public class FitPeo {
		@Test
		public void testA() throws InterruptedException {
		
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get("https://fitpeo.com/");
			driver.findElement(By.xpath("//div[text()='Revenue Calculator']")).click();
			JavascriptExecutor j = (JavascriptExecutor) driver;
			WebElement ele = driver.findElement(By.xpath("//h4[text()='Medicare Eligible Patients']"));
			j.executeScript("arguments[0].scrollIntoView()", ele);
			WebElement drag = driver.findElement(By.xpath("//span[contains(@class,'MuiSlider-thumb')]"));
			WebElement draggableValue = driver.findElement(By.xpath("//span[contains(@class,'MuiSlider-thumb')]/input"));
			slider(driver, draggableValue, drag, 820);
			Thread.sleep(2000);
			WebElement txtb1 = driver.findElement(By.xpath("//input[@aria-invalid='false']"));
			txtb1.sendKeys(Keys.CONTROL+"A"+Keys.BACK_SPACE);
			txtb1.sendKeys("560");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//p[.='CPT-99091']/..//input[@type='checkbox']")).click();
			driver.findElement(By.xpath("//p[.='CPT-99453']/..//input[@type='checkbox']")).click();
			driver.findElement(By.xpath("//p[.='CPT-99454']/..//input[@type='checkbox']")).click();
			driver.findElement(By.xpath("//p[.='CPT-99474']/..//input[@type='checkbox']")).click();
			String actRes = driver.findElement(By.xpath("//p[contains(text(),'Total Recurring Reimbursement')]/p")).getText();
			System.out.println(actRes);
			String expRes = "$110700";
			SoftAssert s=new SoftAssert();
			s.assertEquals(expRes, actRes);
			driver.quit();
			
			s.assertAll();
			
			
		}

		public static void slider(WebDriver driver, WebElement draggableValue, WebElement drag, int value) {
			Actions a = new Actions(driver);
			for (int i = 0; i < 1000; i++) {
				String val = draggableValue.getAttribute("value");
				Integer actualValue = Integer.valueOf(val);
				if (actualValue == value) {
					break;
				} else if (i == 0) {
					a.clickAndHold(drag).moveByOffset(90, 0).perform();
				} else {
					a.sendKeys(Keys.ARROW_RIGHT).perform();
				}

			}
		}
	}


