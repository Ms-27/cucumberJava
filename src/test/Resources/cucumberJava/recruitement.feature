#language: en
Feature: Floorball player’s recruitement  
 
   Scenario Outline: I want to know more about the nearest floorball club 
    	Given I have opened a browser
				And I search for floorball France
			When I click on the French floorball federation website
			Then the menu <menu> is clickable
			
Examples:
	|menu          |
	|"Où pratiquer"|
	|"Le Floorball"|