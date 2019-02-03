Feature: Single Account

  Scenario Outline: Number of Holding for single account
    Given my view account is "<account_id>"
    When I call CalcEngine
    Then I should get Number Holding "<num_holding>"

    Examples:
      | account_id | num_holding |
      | 660001     | 2           |
      | 670001     | 1           |


  Scenario Outline: MV for single account
    Given my view account is "<account_id>"
    When I call CalcEngine
    Then I should get MV Report "<mv_report>"

    Examples:
      | account_id | mv_report |
      | 670001     | 1300      |
      | 660001     | 3300      |