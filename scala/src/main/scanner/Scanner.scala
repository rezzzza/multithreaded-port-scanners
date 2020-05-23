
import java.net.{InetSocketAddress, Socket}
import java.util.concurrent.TimeUnit

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor, Future}
import scala.concurrent.duration.Duration
import scala.compat.Platform.EOL

object Scanner extends App {

  implicit val ec: ExecutionContextExecutor = ExecutionContext.fromExecutor(
    new java.util.concurrent.ForkJoinPool(1000)
  )

  val FutureTimeOut = Duration(4, TimeUnit.SECONDS)
  val TCPTimeOut = Duration(3, TimeUnit.SECONDS)
  val Ports = Range(1, 65535)

  def main(): Unit = {
    scan(getHost)
    synchronized {
      wait()
    }
  }

  private def scan(host: String): Unit = {
    def checkPort(portNumber: Int): Future[Boolean] = {
      Future {
        try {
          val client = JavaSocket().create
          client.connect(SocketAddress(host, portNumber).create, TCPTimeOut.toMillis.toInt)
          val res = true
          client.close()
          res
        } catch {
          case _: Throwable => false
        }
      }
    }


    Ports.foreach { port =>
      checkPort(port) foreach { done =>
        if (done) println(s"${EOL}Connected to the [$port]")
      }
    }
  }

  private def getHost: String = {
    print("Enter your host: ")
    val host = scala.io.StdIn.readLine()
    println(s"Trying to scan $host...")
    host
  }

  main()
}

case class SocketAddress(host: String, port: Int) {

  def create = new InetSocketAddress(host, port)
}

case class JavaSocket() {

  def create = new Socket()
}
