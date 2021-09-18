#language: en
Feature: Repository Functionality
  @UiTest
  Scenario: User successfully creates repository
    Given Sign in
    Then Create repository
    And Copy repository link
    When Find created repository in repositories list
    Then Delete repository
    And Repository is not displayed in repositories list
    Then Sign out