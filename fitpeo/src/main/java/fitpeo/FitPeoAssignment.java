package fitpeo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

public class FitPeoAssignment {

	public static void main(String[] args) throws Exception {
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();

		// Test1
		driver.get("https://www.fitpeo.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();

		// Test2
		WebElement revenueCalculator = driver.findElement(By.xpath("(//div[contains(@class,'satoshi')])[6]"));
		revenueCalculator.click();

		// Test3
		WebElement sliderElement = driver.findElement(By.xpath("(//span[contains(@class,'MuiSlider-thumb')])//input"));
		Actions action = new Actions(driver);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 450)");
		// action.scrollToElement(sliderElement).build().perform();

		// Test4
		int defaultSliderValue = 200;
		int requiredSliderValue = 820;
		for (int i = 0; i < (requiredSliderValue - defaultSliderValue); i++) {
			sliderElement.sendKeys(Keys.ARROW_RIGHT);
		}
		WebElement textField = driver.findElement(By.xpath("//input[@type='number']"));
		String textFieldValue = textField.getAttribute("value");
		if (textFieldValue.equals("820")) {
			System.out.println("Passed Test: Text box is set to 820");
		} else {
			System.out.println("Failed Test: Text box is not set to 820");
		}

		// Test 5
		textField.click();
		int valueLenght = textField.getAttribute("value").length();
		for (int i = 1; i <= valueLenght; i++) {
			action.sendKeys(Keys.BACK_SPACE).build().perform();
		}
		textField.sendKeys("560");

		// Test6
		String sliderValue = sliderElement.getAttribute("value");
		if (sliderValue.equals("560")) {
			System.out.println("Passed Test: Slider is on 560");
		} else {
			System.out.println("Failed Test: Slider is not on 560");
		}

		// Test7
		WebElement CPT99091 = driver.findElement(By.xpath("//p[text()='CPT-99091']/..//input"));
		WebElement CPT99453 = driver.findElement(By.xpath("//p[text()='CPT-99453']/..//input"));
		WebElement CPT99454 = driver.findElement(By.xpath("//p[text()='CPT-99454']/..//input"));
		WebElement CPT99474 = driver.findElement(By.xpath("//p[text()='CPT-99474']/..//input"));
		CPT99091.click();
		CPT99453.click();
		CPT99454.click();
		CPT99474.click();

		Thread.sleep(5000);

		// Test8
		action.scrollToElement(textField).build().perform();
		textField.click();
		for (int i = 1; i <= valueLenght; i++) {
			new Actions(driver).sendKeys(Keys.BACK_SPACE).build().perform();
		}
		textField.sendKeys("820");
		action.scrollToElement(CPT99091).build().perform();
		WebElement headerTotalTitle = driver.findElement(By.xpath("//div[contains(@class,'MuiToolbar')]//p[4]"));
		String totalHeaderLabel = headerTotalTitle.getText();
		WebElement headerTotalValueElement = driver
				.findElement(By.xpath("//div[contains(@class,'MuiToolbar')]//p[4]/p"));
		String totalValue = headerTotalValueElement.getText();
		String expectedValue = "$110700";
		if (totalValue.equals(expectedValue)) {

			System.out.println(totalHeaderLabel);
		} else {
			System.out.println("Failed Test: Total Value is incorrect");
		}

		driver.quit();
	}

}
