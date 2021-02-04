Business Need: Loggin on
  New Frequent Flyer members need to register to book a flight

  Rule: Users must enter a correct username and password to connect
    Scenario Template: Trixie enters incorrect credentials
    The error message should not tell the user which field is incorrect for security reasons.

      Given Trixie is a Frequent Flyer member with the following details:
        | username | trixie@example.org |
        | password | correct-password   |
      When she attempts to log on as "<username>" with password "<password>"
      Then she should not be able to log in
      And she should be presented with an error message containing "<message>"
      Examples:
        | username           | password         | message                    |
        |                    | any-password     | Please enter your username |
        | trixie@example.org |                  | Please enter your password |
        | wrong-username     | correct-password | user credentials invalid   |
        | trixie@example.org | wrong-password   | user credentials invalid   |
