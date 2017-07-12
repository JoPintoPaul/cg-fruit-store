package checkout

class BasketParser {

  def toBasket(input: List[String], catalogue: Map[String, BigDecimal]): Either[String, FruitBasket] = {
    val parsedFruit: List[Either[String, Fruit]] =  input map { name =>
      catalogue.get(name) match {
        case Some(price) => Right(Fruit(name, price))
        case None        => Left(name)
      }
    }

    parsedFruit collect { case Left(unknownFruit) => unknownFruit} match {
      case notFound if notFound.nonEmpty =>
        Left(s"One or more unknown fruits was passed through: ${notFound.mkString(", ")}")
      case _ =>
        val fruits = parsedFruit collect { case Right(fruit) => fruit }
        val grouped =
          fruits
            .groupBy(identity)
            .map(group => (group._1, group._2.length))
        Right(FruitBasket(grouped))
    }
  }
}