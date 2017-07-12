package checkout

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito

class CheckoutSpec extends Specification with Mockito {

  "Given a list of input strings and no offer, calling the checkout" should {
    "return a formatted total if the basket parser returns a fruit basket" in {
      val basketParser = mock[BasketParser]
      basketParser.toBasket(any[List[String]], any[Map[String, BigDecimal]]) returns
        Right(FruitBasket(Map(Fruit("Pear", 0.5) -> 2, Fruit("Mango", 1.9) -> 1)))
      val checkout = new Checkout(basketParser, Map.empty, Nil)
      checkout.checkout(Nil) must_=== "£2.90"
    }

    "return the unchanged error string if the basket parser returns an error string" in {
      val basketParser = mock[BasketParser]
      basketParser.toBasket(any[List[String]], any[Map[String, BigDecimal]]) returns
        Left("This is an expected error")
      val checkout = new Checkout(basketParser, Map.empty, Nil)
      checkout.checkout(Nil) must_=== "This is an expected error"
    }
  }

  "Given a list of input strings and some offers, calling the checkout" should {
    "return a formatted total with offers applied if the basket parser returns a fruit basket" in {
      val basketParser = mock[BasketParser]
      basketParser.toBasket(any[List[String]], any[Map[String, BigDecimal]]) returns
        Right(FruitBasket(Map(Fruit("Pear", 0.5) -> 2, Fruit("Mango", 1.9) -> 1, Fruit("Plum", 0.19) -> 4)))

      val offers = List(Offer("Pear", 2), Offer("Plum", 3))

      val checkout = new Checkout(basketParser, Map.empty, offers)
      checkout.checkout(Nil) must_=== "£2.97"
    }

    "return the unchanged error string if the basket parser returns an error string" in {
      val basketParser = mock[BasketParser]
      basketParser.toBasket(any[List[String]], any[Map[String, BigDecimal]]) returns
        Left("This is an expected error")

      val offers = List(Offer("Pear", 2), Offer("Plum", 3))

      val checkout = new Checkout(basketParser, Map.empty, offers)
      checkout.checkout(Nil) must_=== "This is an expected error"
    }
  }
}
