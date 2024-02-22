Feature: Buy product in MyStore

  @buyInMyShop
  Scenario: Buy Hummingbird Printed Sweater and confirm order
    Given User is logged into MyStore
    When User chooses "Hummingbird Printed Sweater" to buy
    Then User can see that product has 20% discount
    When User chooses size "M" and quantity "5"
    And User adds product to card
    And User chooses proceed to checkout
    And User confirms address
    And User chooses shipping method - "pick up in store"
    And User chooses payment "Pay by Check"
    And User clicks "Order with an obligation to pay"
    Then User can see information "YOUR ORDER IS CONFIRMED"
    When User goes to Order history and detail of last order
    Then User can see order status "Awaiting check payment" and total price
    And close browser


