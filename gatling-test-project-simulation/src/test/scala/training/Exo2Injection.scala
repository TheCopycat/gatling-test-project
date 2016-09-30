package training

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class Exo2Injection extends Simulation {

  val user1 = """ { "firstName" : "John", "lastName"  : "Doe", "age" : "42" } """
  val user2 = """ { "firstName" : "John", "lastName"  : "Doe", "age" : "42" } """
  val user3 = """ { "firstName" : "John", "lastName"  : "Doe", "age" : "42" } """

  val httpProtocol = http.baseURL("http://127.0.0.1:8080")

  val scn = scenario("Exo1")
    // Repeat 5 times
    .repeat(5)(
      //Go to homepage
      exec(http("HomePage").get("/"))
        // Add product1 to basket
        .exec(http("user1").post("/users").body(StringBody(user1)).asJSON)
        // Pause
        .pause(2,3)
        // Add product2 to basket
        .exec(http("user2").post("/users").body(StringBody(user2)).asJSON)
        // Pause
        .pause(2,3)
        // Add product3 to basket
        .exec(http("user3").post("/users").body(StringBody(user3)).asJSON)
        // Pause
        .pause(2,3)
        // Get Total
        .exec(http("list").get("/users"))
    )

  setUp(scn.inject(
    atOnceUsers(1),
    nothingFor(4 seconds),
    rampUsers(10) over(10 seconds),
    heavisideUsers(100) over(5 seconds)
  )
    .protocols(httpProtocol)
  )
}