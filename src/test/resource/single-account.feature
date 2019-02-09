Feature: Single Account

  Scenario: Total Number of Holdings for all accounts
    Given portfolio holdings DB by mock object
    Then the total number of account 5

  Scenario: Total Number of Holdings for all accounts
    Given portfolio holdings DB is "input.data.01.csv"
    When I call CalcEngine
    Then the total number of account 2 number holding 16


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


  Scenario Outline: Number of Holding for single account
    Given Source data "<posn_file>" and view account is "<account_id>" "<stockCode>"
    When I call CalcEngine
    Then I should get portfolio weight "<tmv>"

    Examples:
      | account_id  | stockCode | tmv         | posn_file         |
      |660001       | 0001.HK   | 0.14368     | input.data.01.csv |
      |660001       | 0002.HK   | 0.14224     | input.data.01.csv |
      |660001       | 0003.HK   | 0.13793     | input.data.01.csv |
      |660001       | 0004.HK   | 0.13075     | input.data.01.csv |
      |660001       | 0005.HK   | 0.12069     | input.data.01.csv |
      |660001       | 1001.JP   | 0.10776     | input.data.01.csv |
      |660001       | 1002.JP   | 0.09195     | input.data.01.csv |
      |660001       | 1003.JP   | 0.07328     | input.data.01.csv |
      |660001       | 1004.JP   | 0.05172     | input.data.01.csv |
      |670001       | 0003.HK   | 0.16420     | input.data.01.csv |
      |670001       | 0004.HK   | 0.16256     | input.data.01.csv |
      |670001       | 0005.HK   | 0.15764     | input.data.01.csv |
      |670001       | 0006.HK   | 0.14943     | input.data.01.csv |
      |670001       | 0007.HK   | 0.13793     | input.data.01.csv |
      |670001       | 1003.JP   | 0.12315     | input.data.01.csv |
      |670001       | 1004.JP   | 0.10509     | input.data.01.csv |

