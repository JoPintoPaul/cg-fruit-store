package checkout

case class Fruit(name: String, price: BigDecimal)

case class FruitBasket(contents: List[Fruit]) {
  val total: BigDecimal = contents.map(_.price).sum
}

object Catalogue {

  val fruits: Map[String, BigDecimal] = Map(
    "Apple" -> 0.6,
    "Orange" -> 0.25
  )
}