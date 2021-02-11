Business Need: Loggin on
  New Frequent Flyer members need to register to book a flight

  Background:
    Given Trixie is a Frequent Flyer member with the following details:
      | email    | trixie@example.org |
      | password | correct-password   |

  Rule: Users must enter a correct username and password to connect
    Scenario Template: Trixie enters incorrect credentials
    The error message should not tell the user which field is incorrect for security reasons.

      When Trixie attempts to log on as "<email>" with password "<password>"
      Then she should not be able to log in
      And she should be presented with an error message containing "Invalid email or password"
      Examples:
        | email              | password         |
        | wrong-username     | correct-password |
        | trixie@example.org | wrong-password   |

  Rule: Username and password cannot be empty
    Scenario Template: Trixie forgets to enter a username or a password

      When Trixie attempts to log on as "<email>" with password "<password>"
      Then she should not be able to log in
      And she should be told "<message>" about <field>
      Examples:
        | email              | password     | field    | message                    |
        |                    | any-password | email    | Please enter your email    |
        | trixie@example.org |              | password | Please enter your password |
