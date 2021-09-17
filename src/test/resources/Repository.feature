#language: en
Feature: Repository Functionality

  Scenario: Repository is correctly created and deleted
    When Repository is created
    Then Get created repository
    And Delete created repository

  Scenario: Public repository is available for non-logged in user
    Given Public repository is available for non-logged in user

  Scenario: Not logged in user can not create repository
    Given Repository is not created for not logged in user

  Scenario: List of repositories is available
    Given Get list of repositories


