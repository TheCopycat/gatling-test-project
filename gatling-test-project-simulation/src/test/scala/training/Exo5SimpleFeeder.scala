package training

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._


class Exo5SimpleFeeder extends Simulation {

  val httpProtocol = http.baseURL("http://oscobai064s.sys.meshcore.net:8000/petstoreee7-7.0")

  val feeder = Array(
    Map("itemId" → 1000),
    Map("itemId" → 1001),
    Map("itemId" → 1002),
    Map("itemId" → 1003),
    Map("itemId" → 1004),
    Map("itemId" → 1005),
    Map("itemId" → 1006),
    Map("itemId" → 1007),
    Map("itemId" → 1008),
    Map("itemId" → 1009),
    Map("itemId" → 1010)
  ).random                  //or .circular

  //or
  //val feeder = (for (i ← 1000 to 1010) yield Map("itemId" → i)).random

  val scn = scenario("Exo5")
    .feed(feeder)
    // Repeat 5 times
    .repeat(5)(
      //Go to main page
      exec(http("homepage").get("/shopping/main.xhtml"))
      // Pause
      .pause(2,3)
      // Go to category page
      .exec(http("category").get("/shopping/showproducts.xhtml?categoryName=Reptiles"))
        // Pause
      .pause(1)
        //repeat 3 times
      .repeat(3)(
        // Go to product page
        exec(http("product").get("/shopping/showitems.xhtml?productId=${itemId}"))
        // Pause
        .pause(1)
        // Go to item page
        .exec(http("item").get("/shopping/showitem.xhtml?itemId=${itemId}"))
        // Pause
        .pause(1)
      )
    )

  setUp(scn
    .inject(
      atOnceUsers(1),
      nothingFor(10.seconds),
      rampUsers(10) over 10.seconds,
      heavisideUsers(100) over 5.seconds)
    .protocols(httpProtocol)
  ).assertions(
    global.failedRequests.count.is(0),
    forAll.requestsPerSec.greaterThan(5),
    global.responseTime.percentile1.lessThan(200)
  )

}
