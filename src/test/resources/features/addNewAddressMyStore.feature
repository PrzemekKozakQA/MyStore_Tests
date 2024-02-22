Feature: Add new address to account

  @newAddress
  Scenario Outline: Add new addresses to user account
    Given User is logged into MyStore account
    When User goes to Your addressesPage
    And User clicks "Create new address"
    When User fills form fields: <alias>, <address>, <city>, <zip/postal code>, <country> and <phone>
    And User saves new address
    Then User can see "Address successfully added!"
    When User clicks "Delete" below last added address
    Then Address is deleted and users can see "Address successfully deleted!"
    And User close browser

    Examples:
      | alias  | address    | city   | zip/postal code | country        | phone            |
      | Home   | 1 Long St  | London | E11 3DB         | United Kingdom | +44 123 456 7890 |
      | Office | 2 Short St | London | SE1 8LJ         | United Kingdom | +44 098 765 4321 |