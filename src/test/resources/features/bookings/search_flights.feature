Business Need: Search Flights

  Background:
    Given Donald is a registered Frequent Flyer
    And Donald has logged on

  @current
  Example: Searching for a single trip
    Given Donald wants to travel from New York to London in Economy
    When he searches for available flights
#    Then he should be shown all matching flights
