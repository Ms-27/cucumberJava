package cucumberJava;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AnnotationSteps {
	WebDriver driver;

//france floorball
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
	}
	
//salary management
	Map<String, Integer> list_salary = new HashMap<String, Integer>();
	String name;
	
	@Given("^a list of employees salary$")
	public void a_list_of_employees_salary() throws Throwable {
		list_salary.put("bob", 35);
		list_salary.put("bill", 50);
	}

	@When("^it is a \"([^\"]*)\"$")
	public void it_is_a(String arg1) throws Throwable {
		name = arg1;
	}

	@Then("^the salary should be (\\d+)k€$")
	public void the_salary_should_be_k€(int arg1) throws Throwable {
		int salary = list_salary.get(name);
		assertEquals("Problème de salaire", arg1, salary);
	}
	
//achat produit
	List<Produit> list_produits;
	int sumTotal;
	
	@Given("^le prix de produit de cette liste$")
	public void le_prix_de_produit_de_cette_liste(List<Produit> list_produits) throws Throwable {
		this.list_produits = list_produits;
	}

	@When("^je commande (\\d+) \"([^\"]*)\"$")
	public void je_commande(int nb_produit, String nom_produit) throws Throwable {
		System.out.println("\n"+nom_produit.toUpperCase());
		for (Produit p: list_produits) {
			System.out.println("\n"+p.name);
			if (p.name==nom_produit) {
				System.out.println("YES");
				int price = p.prix;
				sumTotal += price*nb_produit;
				break;
			}
		}
	}

	@Then("^je dois payer (\\d+) euros$")
	public void je_dois_payer_euros(int arg1) throws Throwable {
		assertEquals(arg1, sumTotal);
	}
	
//casino
	List<Person> list_person;
	String bouncer_line = "of course, come in";
	
	@Given("^the following persons$")
	public void the_following_persons(List<Person> list_person) throws Throwable {
		this.list_person = list_person;
	}

	@When("^they ask if they could go in a casino$")
	public void they_ask_if_they_could_go_in_a_casino() throws Throwable {
	    for (Person p: list_person) {
	    	if (p.age < 18 || p.gambler == true) {
	    		bouncer_line = "no way, get out my face !";
		    	break;
	    	}
	    }
	}

	@Then("^the bouncer should say \"([^\"]*)\"$")
	public void the_bouncer_should_say(String arg1) throws Throwable {
	    assertEquals(arg1, bouncer_line); 
	}
	
//jpetstore
	@Given("^a user with internet$")
	public void a_user_with_internet() throws Throwable {
		driver = new FirefoxDriver();
	}

	@When("^he enters the \"([^\"]*)\" of jpetstore in webbrowser$")
	public void he_enters_the_of_jpetstore_in_webbrowser(String url) throws Throwable {
		driver.get(url);
	}

	@Then("^the homepage of jpetsore appear$")
	public void the_homepage_of_jpetsore_appear() throws Throwable {
	    assertEquals("JPetStore Demo", driver.getTitle());
	    driver.quit();
	}

	@When("^he connects with login/pass \"([^\"]*)\"/\"([^\"]*)\"$")
	public void he_connects_with_login_pass(String login, String pass) throws Throwable {
		driver.get("https://jpetstore.cfapps.io/catalog");
		driver.findElement(By.xpath("//a[@href='/login']")).click();
		WebElement user_field = driver.findElement(By.xpath("//input[@name='username']"));
		user_field.clear();
		user_field.sendKeys(login);
		WebElement pass_field = driver.findElement(By.xpath("//input[@name='password']"));
		pass_field.clear();
		pass_field.sendKeys(pass);
		driver.findElement(By.xpath("//input[@id='login']")).click();
	}

	@Then("^the user \"([^\"]*)\" is connected$")
	public void the_user_is_connected(String name) throws Throwable {
	    WebElement user_name = driver.findElement(By.xpath("//span[.='ABC']"));
	    assertEquals(name, user_name.getText());
	    driver.quit();
	}
	
//exo++
	List<Personne> liste_personnes = new ArrayList<Personne>(10);
	int N;
	Personne personne1;
	Personne personne0 = new Personne(1);
	Personne personne2 = new Personne(2);
	Personne personne3 = new Personne(3);
	
	
	@Given("^L'entrepôt contient N Personnes$")
	public void l_entrepôt_contient_N_Personnes() throws Throwable {
	    N = liste_personnes.size();
	}

	@When("^Je crée une Personne$")
	public void je_crée_une_Personne() throws Throwable {
	    personne1 = new Personne(1924);
	    liste_personnes.add(personne1);
	}

	@Then("^J'obtiens l'ID de la Personne créée et l'entrepôt contient plus de N Personnes$")
	public void j_obtiens_l_ID_de_la_Personne_créée_et_l_entrepôt_contient_plus_de_N_Personnes() throws Throwable {
		Integer id_personne = personne1.id;
		assertFalse(id_personne == null);
		assertTrue(N < liste_personnes.size());
	}

	@Given("^L'entrepôt contient la Personnes Anakin Skywalker$")
	public void l_entrepôt_contient_la_Personnes_Anakin_Skywalker() throws Throwable {
	    if (liste_personnes.contains("Anakin Skywalker")) {
	    	System.out.println("Anakin Skywalker est bien présent dan l'entrepôt");
	    }
	}

	@When("^Je supprime la Personne (\\d+)$")
	public void je_supprime_la_Personne(int id_nbr) throws Throwable {
		N = liste_personnes.size();
		System.out.println(N);
	    for (Personne p: liste_personnes) {
	    	if (p.id == id_nbr) {
	    		liste_personnes.remove(1);
	    	}
	    }
	}

	@Then("^L'entrepôt contient moins de N Personnes$")
	public void l_entrepôt_contient_moins_de_N_Personnes() throws Throwable {
	    Integer x = liste_personnes.size();
	    System.out.println(x);
	    assertTrue(N > x);
	}
}