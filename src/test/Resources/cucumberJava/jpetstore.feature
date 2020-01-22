#language: en
Feature: jpetstore access

	Scenario: a user who acces to jpetstore
		Given a user with internet
		When he enters the "https://jpetstore.cfapps.io/catalog" of jpetstore in webbrowser
		Then the homepage of jpetsore appear
		
	Scenario: a user connects with login & pass
		Given a user with internet
		When he connects with login/pass "j2ee"/"j2ee"
		Then the user "ABC" is connected