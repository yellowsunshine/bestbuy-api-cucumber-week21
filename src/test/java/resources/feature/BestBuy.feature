Feature: Run CRUD operation on BestBuy API
  User should be able create, read, update and delete store, products, services and categories details


  Scenario Outline: User can run successful CRUD operation on store details
    When    User creates new store record with "<name>", "<type>", "<address>", "<address2>", "<city>", "<state>", "<zip>", "<lat>", "<lng>", "<hours>"
    And     User can search new record using store "<name>"
    And     User can update new record using storeID, name, "<type>", "<address>", "<address2>", "<city>", "<state>", "<zip>", "<lat>", "<lng>", "<hours>"
    And     User can delete new record of storeID
    Then    User is able to run successful CRUD operation on store details
    Examples:
      | name          | type   | address         | address2 | city   | state | zip   | lat       | lng       | hours                                                                         |
      | Milton Keynes | BigBox | 12 1st Street A | abc      | London | MN    | 25687 | 44.879314 | 93.077156 | Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8 |


  Scenario Outline: User can run successful CRUD operation on product details
    When            User creates new product record with "<name>", "<type>", "<price>", "<upc>", "<shipping>", "<description>", "<manufacturer>", "<model>", "<url>", "<image>"
    And             User can search new record using product "<name>"
    And             User can update new record using productID, name, "<type>", "<price>", "<upc>", "<shipping>", "<description>", "<manufacturer>", "<model>", "<url>", "<image>"
    And             User can delete new record of productID
    Then            User is able to run successful CRUD operation on product details
    Examples:
      | name         | type  | price | upc          | shipping | description       | manufacturer | model     | url                                                                                                   | image                                                                |
      | Disney Books | Books | 3.49  | 041333424019 | 1234     | Suitable for kids | Amazon       | MN2400B4Z | htt://www.bestbuy.com/site/Amazon-aaa-batteries-4-pack/43900.p?id=1051384074145&skuId=43900&cmp=RMXCC | htt://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg |


  Scenario Outline: User can run successful CRUD operation on service records
    When            User creates new service record with "<name>"
    And             User can search new record using service "<name>"
    And             User can update new record using serviceID, name
    And             User can delete new record of serviceID
    Then            User is able to run successful CRUD operation on service records
    Examples:
      | name           |
      | Photo Scanning |


  Scenario Outline: User can run successful CRUD operation on category records
    When            User creates new category record with "<id>","<name>"
    And             User can search new record using categoryID "<id>"
    And             User can update new record using categoryID "<id>", name
    And             User can delete new record of categoryID "<id>"
    Then            User is able to run successful CRUD operation on category record with categoryID "<id>"
    Examples:
      | id     | name        |
      | abcat001 | video games |