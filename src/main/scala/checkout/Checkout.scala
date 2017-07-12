package checkout

import java.text.DecimalFormat

case class Checkout(basketParser: BasketParser, catalogue: Map[String, BigDecimal]) {

  private val moneyFormat = new DecimalFormat("£0.00")

  def checkout(input: List[String]): String = {
    val parsedBasket: Either[String, FruitBasket] = basketParser.toBasket(input, catalogue)

    parsedBasket match {
      case Right(basket) => moneyFormat.format(basket.total)
      case Left(error)   => error
    }
  }

}
