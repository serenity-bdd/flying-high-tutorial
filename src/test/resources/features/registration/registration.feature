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
      Then she should be presented with an error message containing "Please enter a valid email address"
      Examples:
        | username     |
        | not-an-email |
        | not@email    |
        | notemail.com |

  Rule: Duplicate usernames are not allowed
    Example: Someone tries to registers with another user's username
      Given Mike is a Frequent Flyer member with the following details:
        | username | mike@example.org |
        | password | correct-password |
      When Michael tries to register with a username of "mike@example.org"
      Then he should be presented with an error message containing "Username exists"

  Rule: All fields are mandatory when registering
    Scenario Outline: : Candy forgets to enter a mandatory field
      Given Candy does not have a Frequent Flyer account
      When she tries to register without entering <missing-field>
      Then she should be presented with an error message containing "<error-message>"
      Examples:
        | missing-field | error-message                |
        | username      | Please enter your username   |
        | password      | Please enter your password   |
        | firstName     | Please enter your first name |
        | lastName      | Please enter your last name  |
        | address       | Please enter your address    |

  Rule: Customers must agree to the terms and conditions before registering
    Example: Candy forgets to agree to the terms and conditions
      Given Candy does not have a Frequent Flyer account
      When she tries to register without approving the terms and conditions
      Then she should be presented with an error message containing "<message>"