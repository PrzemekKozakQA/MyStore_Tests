$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/features/shoppingMyStore.feature");
formatter.feature({
  "name": "Buy product in MyStore",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Buy Hummingbird Printed Sweater and confirm order",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@buyInMyShop"
    }
  ]
});
formatter.step({
  "name": "User is logged into MyStore",
  "keyword": "Given "
});
formatter.match({
  "location": "MyStoreBuySteps.userIsLoggedIntoMyStore()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User chooses \"Hummingbird Printed Sweater\" to buy",
  "keyword": "When "
});
formatter.match({
  "location": "MyStoreBuySteps.userChoosesToBuy(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User can see that product has 20% discount",
  "keyword": "Then "
});
formatter.match({
  "location": "MyStoreBuySteps.userCanSeeThatProductHasDiscount(int)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User chooses size \"M\" and quantity \"5\"",
  "keyword": "When "
});
formatter.match({
  "location": "MyStoreBuySteps.userChoosesSizeAndQuantity(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User adds product to card",
  "keyword": "And "
});
formatter.match({
  "location": "MyStoreBuySteps.userAddsProductToCard()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User chooses proceed to checkout",
  "keyword": "And "
});
formatter.match({
  "location": "MyStoreBuySteps.userChoosesProceedToCheckout()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User confirms address",
  "keyword": "And "
});
formatter.match({
  "location": "MyStoreBuySteps.userConfirmAddress()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User chooses shipping method - \"pick up in store\"",
  "keyword": "And "
});
formatter.match({
  "location": "MyStoreBuySteps.userChoosesShippingMethod()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User chooses payment \"Pay by Check\"",
  "keyword": "And "
});
formatter.match({
  "location": "MyStoreBuySteps.userChoosesPayment()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User clicks \"Order with an obligation to pay\"",
  "keyword": "And "
});
formatter.match({
  "location": "MyStoreBuySteps.userClicks()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User can see information \"YOUR ORDER IS CONFIRMED\"",
  "keyword": "Then "
});
formatter.match({
  "location": "MyStoreBuySteps.userCanSeeInformation(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User goes to Order history and detail of last order",
  "keyword": "When "
});
formatter.match({
  "location": "MyStoreBuySteps.userGoesToOrderHistoryAndDetailOfLastOrder()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User can see order status \"Awaiting check payment\" and total price",
  "keyword": "Then "
});
formatter.match({
  "location": "MyStoreBuySteps.userCanSeeOrderStatusAndTotalPrize(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "close browser",
  "keyword": "And "
});
formatter.match({
  "location": "MyStoreBuySteps.closeBrowser()"
});
formatter.result({
  "status": "passed"
});
});