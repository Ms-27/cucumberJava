#language: en
Feature: Addition

	Scenario: Add two numbers
		Given I have entered 50 into the calculator
			And I have entered 70 into the calculator
			And I have entered 20 into the calculator
		When I press add
		Then the result should be 140 on the screen