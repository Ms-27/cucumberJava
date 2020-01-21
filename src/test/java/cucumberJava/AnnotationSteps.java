package cucumberJava;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AnnotationSteps {
	WebDriver driver;

	@Given("^I have opened a browser$")
	public void i_have_opened_a_browser() throws Throwable {
	    driver = new FirefoxDriver();
	    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	    driver.get("https://www.google.fr");
	}

	@Given("^I search for floorball France$")
	public void i_search_for_floorball_France() throws Throwable {
	    driver.findElement(By.xpath("//input[@class='gLFyf gsfi']")).sendKeys("France Floorball"); 
	    driver.findElement(By.xpath("//form[@id='tsf']/div[2]/div/div[3]/center/input[1]")).submit();
	}

	@When("^I click on the French floorball federation website$")
	public void i_click_on_the_french_floorball_federation_website() throws Throwable {
	    driver.findElement(By.xpath("//div/a[contains(@href,'www.floorball.fr')]")).click();
	}

	@Then("^the menu \"([^\"]*)\" is clickable$")
	public void the_menu_is_clickable(String nom_menu) throws Throwable {
		driver.findElement(By.xpath("//a[.='"+ nom_menu +"']")).click();
		driver.quit();
	}
	
	//calculator
	ArrayList<Integer> values = new ArrayList<Integer>();
	int result;
	
	@Given("^I have entered (\\d+) into the calculator$")
	public void setArgument(int arg1) throws Throwable {
		values.add(arg1);
		System.out.println(arg1);
	}
	
	@When("^I press add$")
	public void pressAdd() throws Throwable {
		for (int value : values)
			result += value;
		System.out.println(result);
	}

	@Then("^the result should be (\\d+) on the screen$")
	public void the_result_should_be_on_the_screen(int arg1) throws Throwable {
		assertEquals(arg1, result);
//		if (arg1 == result) {
//			System.out.println("Resultat correct");
//		}
	}
	
	// salary management
	@Given("^the name of the employee$")
	public void the_name_of_the_employee() throws Throwable {
	}

	@When("^it is a \"([^\"]*)\"$")
	public void it_is_a_bob(String arg1) throws Throwable {
		if (arg1 == "bob") {
			System.out.println("bob");
		} else {
			System.out.println("bill");
		}
	}

	
	@Then("^the salary should be (\\d+)k€$")
	public void the_salary_should_be_k€(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
}