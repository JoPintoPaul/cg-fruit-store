package checkout

import scala.io.StdIn.readLine

object Main {

  def main(args: Array[String]): Unit = {
    println("Welcome! Please enter your basket contents as a list of strings, separated by commas, e.g. Apple, Apple, Orange, Apple")
    println("At this time, out store sells Apples and Oranges only")

    val input: String = readLine()
    val inputAsList: List[String] =
      input.split(",")
        .map(_.trim)
        .toList

    val checkout = Checkout(new BasketParser(), Catalogue.fruits, Offers.fruitOffers)
    val checkoutTotal = checkout.checkout(inputAsList)

    println(checkoutTotal)
  }
}