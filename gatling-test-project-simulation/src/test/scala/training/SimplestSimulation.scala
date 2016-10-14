package training

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class SimplestSimulation extends Simulation {
  setUp(scenario("Petstore")
    .exec(http("Home").get("http://oscobai064s.sys.meshcore.net:8000/petstoreee7-7.0"))
    .inject(atOnceUsers(1)))}