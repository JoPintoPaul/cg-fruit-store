package checkout

import java.text.DecimalFormat

case class Checkout(basketParser: BasketParser, catalogue: Map[String, BigDecimal], offers: List[Offer]) {

  private val moneyFormat = new DecimalFormat("£0.00")

  def checkout(input: List[String]): String = {

    val parsedBasket: Either[String, FruitBasket] = basketParser.toBasket(input, catalogue)

    parsedBasket match {
      case Right(basket) =>
        val updatedItems: Map[Fruit, Int] = offers.foldLeft(basket.contents)(applyOffer)
        val updatedBasket = FruitBasket(updatedItems)
        moneyFormat.format(updatedBasket.total)
      case Left(error)   => error
    }
  }

  private def applyOffer(beforeOffer: Map[Fruit, Int], offer: Offer) = {
    val key: Option[Fruit] = beforeOffer.find(_._1.name == offer.fruitName).map(_._1)
    key match {
      case Some(fruit) =>
        val totalFruit = beforeOffer.getOrElse(fruit, 0)
        val paidForFruit = totalFruit - (totalFruit / offer.bogof)
        beforeOffer.updated(fruit, paidForFruit)
      case None => beforeOffer
    }
  }

}
