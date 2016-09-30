package training

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class SimplestSimulation extends Simulation {
  setUp(scenario("Hello")
    .exec(http("World").get("http://127.0.0.1:8080/fibo/2"))
    .inject(atOnceUsers(1)))
}