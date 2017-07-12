package checkout

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito

class CheckoutSpec extends Specification with Mockito {

  "Given a list of input strings, calling the checkout" should {
    "return a formatted total is the basket parser returns a fruit basket" in {
      val basketParser = mock[BasketParser]
      basketParser.toBasket(any[List[String]], any[Map[String, BigDecimal]]) returns
        Right(FruitBasket(List(Fruit("Pear", 0.5), Fruit("Pear", 0.5), Fruit("Mango", 1.9))))
      val checkout = new Checkout(basketParser, Map.empty)
      checkout.checkout(Nil) must_=== "£2.90"
    }

    "return the unchanged error string if the basket parser returns an error string" in {
      val basketParser = mock[BasketParser]
      basketParser.toBasket(any[List[String]], any[Map[String, BigDecimal]]) returns
        Left("This is an expected error")
      val checkout = new Checkout(basketParser, Map.empty)
      checkout.checkout(Nil) must_=== "This is an expected error"
    }
  }
}
