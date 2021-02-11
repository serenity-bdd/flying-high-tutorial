Business Need: Registration
  New Frequent Flyer members need to register to book a flight.

  Rule: Customers must register to be able to use the Frequent Flyer members area
    Example: Trevor registers as a Frequent Flyer member
      Given Trevor does not have a Frequent Flyer account
      When he registers as a Frequent Flyer member
      And he logs on to the Frequent Flyer application
      Then he should be taken to the Frequent Flyer members area
      And he should be greeted by his name

  Rule: The unique username should be a valid email address
    Scenario Outline: Only correctly structured emails should be accepted
      Given Candy does not have a Frequent Flyer account
      When she tries to register with a username of "<username>"
      Then she should be told "Not a valid email format"
      Examples:
        | username     |
        | not-an-email |
        | notemail.com |

  Rule: Duplicate usernames are not allowed
    Example: Someone tries to registers with another user's username
      Given Mike is a Frequent Flyer member with the following details:
        | username | mike@example.org |
        | password | correct-password |
      When Michael tries to register with a username of "mike@example.org"
      Then he should be presented with an error message containing "Email exists, please try another name"

  Rule: Most fields are mandatory when registering
    Scenario Outline: Candy forgets to enter a mandatory field
      Given Candy does not have a Frequent Flyer account
      When she tries to register without entering her <missing-field>
      Then she should be told "<error-message>" about <missing-field>
      Examples:
        | missing-field | error-message                |
        | email         | Please enter your email      |
        | password      | Please enter your password   |
        | firstName     | Please enter your first name |
        | lastName      | Please enter your last name  |
        | address       | Please enter your address    |
        | country       | Please enter a valid country |

    @current
    Scenario: Mandatory fields for registration
    This scenario uses a more declarative style and is less tightly coupled to the implementation
      When Candy wants to register for a Frequent Flyer account
      Then the following information should be mandatory to register:
        | email     |
        | password  |
        | firstName |
        | lastName  |
        | address   |
        | country   |

  Rule: Customers should be informed about mandatory fields
  This is a more light-weight variation of the previous scenario
    Scenario: Candy forgets to enter a mandatory value
      Given Candy does not have a Frequent Flyer account
      When she tries to register without entering her firstName
      Then she should be told "Please enter your first name"

  Rule: Customers must agree to the terms and conditions before registering
    Example: Candy forgets to agree to the terms and conditions
      Given Candy does not have a Frequent Flyer account
      When she tries to register without approving the terms and conditions
      Then she should be told "Please confirm the terms and conditions to continue"
