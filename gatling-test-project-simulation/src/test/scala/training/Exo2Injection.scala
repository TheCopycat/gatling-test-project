package training

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class Exo2Injection extends Simulation {

  val httpProtocol = http.baseURL("http://oscobai064s.sys.meshcore.net:8000/petstoreee7-7.0")

  val scn = scenario("Exo1")
    // Repeat 5 times
    .repeat(5)(
      //Go to main page
      exec(http("homepage").get("/shopping/main.xhtml"))
      // Go to category page
      .exec(http("category").get("/shopping/showproducts.xhtml?categoryName=Reptiles"))
        // Pause
      .pause(2,3)
      // Go to product page
      .exec(http("product").get("/shopping/showitems.xhtml?productId=1011"))
      // Pause
      .pause(1)
      // Go to item page
      .exec(http("item").get("/shopping/showitem.xhtml?itemId=1011"))
      // Pause
      .pause(1)
    )

  setUp(scn
    .inject(
      atOnceUsers(1),
      nothingFor(10.seconds),
      rampUsers(10) over 10.seconds,
      heavisideUsers(100) over 5.seconds)
    .protocols(httpProtocol)
  )
}