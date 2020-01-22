#language: en
Feature: salary managment 

Scenario Outline: Salary management depending on the name of the employee
	Given a list of employees salary
	When it is a <name>
	Then the salary should be <pay>

Examples:
|name  	 |pay   |
|"bob"   |35k€  |
|"bill"  |50k€  |