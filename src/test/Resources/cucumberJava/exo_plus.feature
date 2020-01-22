#language: en
Feature: Entrepôt de données 'Personne'

	Scenario: Création
    Given L'entrepôt contient N Personnes
    When Je crée une Personne
    Then J'obtiens l'ID de la Personne créée et l'entrepôt contient plus de N Personnes
    
	Scenario: Suppression
    Given L'entrepôt contient la Personnes Anakin Skywalker
    When Je supprime la Personne 1
    Then L'entrepôt contient moins de N Personnes