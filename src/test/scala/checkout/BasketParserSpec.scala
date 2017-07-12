package checkout

import org.specs2.mutable.Specification

class BasketParserSpec extends Specification {

  private val testFruitCatalogue: Map[String, BigDecimal] = Map(
    "Pear" -> 0.5,
    "Mango" -> 1.99
  )

  private val basketParser = new BasketParser()

  "Given a list of user inputs and a map of fruit names and prices, parsing the basket" should {
    "return Right of a fruit basket if all inputs match fruits" in {
      val validInputs = List("Mango", "Pear", "Pear")
      val actualResult = basketParser.toBasket(validInputs, testFruitCatalogue)
      actualResult must_=== Right(
        FruitBasket(List(Fruit("Mango", 1.99), Fruit("Pear", 0.5), Fruit("Pear", 0.5)))
      )
    }

    "return Right of a fruit basket if all inputs is empty" in {
      val actualResult = basketParser.toBasket(Nil, testFruitCatalogue)
      actualResult must_=== Right(
        FruitBasket(List())
      )
    }

    "return Left with an error message if no inputs match fruits" in {
      val invalidInputs = List("Bread", "Cheese")
      val actualResult = basketParser.toBasket(invalidInputs, testFruitCatalogue)
      actualResult must_=== Left(
        "One or more unknown fruits was passed through: Bread, Cheese"
      )
    }

    "return Left with an error message if some but not all inputs match fruits" in {
      val invalidInputs = List("Mango", "Pear", "Cheese")
      val actualResult = basketParser.toBasket(invalidInputs, testFruitCatalogue)
      actualResult must_=== Left(
        "One or more unknown fruits was passed through: Cheese"
      )
    }
  }

}
