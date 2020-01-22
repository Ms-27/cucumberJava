#language: en
Feature: achat
	Scenario: Verifier la fonctionnalité d'achat d'un produit
	Given le prix de produit de cette liste
	|name|prix|
	|café|1|
	|donut|2|
	
	When je commande 2 "café"
	And je commande 1 "donut"
	Then je dois payer 4 euros