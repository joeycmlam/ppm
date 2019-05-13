Feature: Single Account

  Scenario Outline: MV for single account
    Given my view account is "<account_id>" "<posn_file>"
    When I call CalcEngine
    Then I should get MV Report "<mv_report>"

    Examples:
      | account_id | mv_report | posn_file          |
      | 670001     | 6090      | input.data.01.csv  |
      | 660001     | 7320      | input.data.01.csv  |


  Scenario Outline: Number of Holding for single account
    Given Source data "input.data.01.csv" and view account is "<account_id>" "<stockCode>"
    When I call CalcEngine
    Then I should get portfolio weight "<tmv>"

    Examples:
      | account_id  | stockCode | tmv         |
      |660001       | 0001.HK   | 0.13661     |
      |660001       | 0002.HK   | 0.13525     |
      |660001       | 0003.HK   | 0.13115     |
      |660001       | 0004.HK   | 0.12432     |
      |660001       | 0005.HK   | 0.11475     |
      |660001       | 1001.JP   | 0.10246     |
      |660001       | 1002.JP   | 0.08743     |
      |660001       | 1003.JP   | 0.06967     |
      |660001       | 1004.JP   | 0.04918     |


  Scenario Outline: single stock Holding
    Given holding enquiry "<stockCode>" "input.data.01.csv"
    When I call CalcEngine
    Then company holding "<units>"

    Examples:
      | stockCode | units       |
      | 0002.HK   | 110.0       |
      | 0004.HK   | 240.0       |


  Scenario: Total Number of Holdings for all accounts
    Given portfolio holdings DB by mock object
    Then the total number of account 5

  Scenario: Total Number of Holdings for all accounts
    Given portfolio holdings DB is "input.data.01.csv"
    When I call CalcEngine
    Then the total number of account 2 number holding 17


  Scenario Outline: Number of Holding for single account
    Given my view account is "<account_id>" "<posn_file>"
    When I call CalcEngine
    Then I should get Number Holding "<num_holding>"

    Examples:
      | account_id | num_holding | posn_file          |
      | 660001     | 10          | input.data.01.csv  |
      | 670001     | 7           | input.data.01.csv  |


