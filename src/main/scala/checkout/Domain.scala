package checkout

case class Fruit(name: String, price: BigDecimal)

case class FruitBasket(contents: Map[Fruit, Int]) {
  val total: BigDecimal = (contents map { fruitAndQuantity =>
    val (fruit, quantity) = fruitAndQuantity
    fruit.price * quantity
  }).sum
}

case class Offer(fruitName: String, bogof: Int)

object Catalogue {

  val fruits: Map[String, BigDecimal] = Map(
    "Apple" -> 0.6,
    "Orange" -> 0.25
  )
}

object Offers {
  val fruitOffers: List[Offer] = List(
    Offer("Apple", 2),
    Offer("Orange", 3)
  )
}