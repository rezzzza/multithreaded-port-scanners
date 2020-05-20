object Scanner extends App {

  def main: Unit = {
    val input = helloInput()
    Range(1, 65535).foreach { port =>

    }
  }

  private def checkPort(portNumber: Int): Int = {
    0
  }

  private def helloInput(): String = {
    print("Enter your host: ")
    val input = scala.io.StdIn.readLine()
  }
  main
}