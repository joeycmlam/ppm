Feature: Single Account
  Scenario: Total Number of Holdings for all accounts
    Given portfolio holdings DB is "input.data.01.csv"
    Then the total number of account 2

  Scenario Outline: Number of Holding for single account
    Given my view account is "<account_id>" "<posn_file>"
    When I call CalcEngine
    Then I should get Number Holding "<num_holding>"

    Examples:
      | account_id | num_holding | posn_file          |
      | 660001     | 9           | input.data.01.csv  |
      | 670001     | 7           | input.data.01.csv  |


  Scenario Outline: MV for single account
    Given my view account is "<account_id>" "<posn_file>"
    When I call CalcEngine
    Then I should get MV Report "<mv_report>"

    Examples:
      | account_id | mv_report | posn_file          |
      | 670001     | 6090      | input.data.01.csv  |
      | 660001     | 6960      | input.data.01.csv  |
