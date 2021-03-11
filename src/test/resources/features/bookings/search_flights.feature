Business Need: Search Flights

  In order to book more flights
  As an airline
  I want travellers to be able to find flights that match their needs

  Background:
    Given Tracy is a registered Frequent Flyer
    And Tracy has logged on

  Rule: Travellers can search for single or return trips
    Example: Searching for a single trip
      Given Tracy wants to travel from New York to London in Economy
      When she searches for available flights
      Then she should be shown all matching flights

  Rule: Travellers can book a flight they have found
    @current
    Example: Tracy books a flight she has found
      Given Tracy has found a flight from New York to London in Economy
      When she books the flight
      Then the flight should appear in her bookings



