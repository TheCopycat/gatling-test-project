package training

import io.gatling.core.Predef._
import io.gatling.http.Predef._


class Exo6CSVFeeder extends Simulation {

  val httpProtocol = http.baseURL("http://oscobai064s.sys.meshcore.net:8000/petstoreee7-7.0")

  val feeder = csv("categories.csv")

  val scn = scenario("Exo6")
    .repeat(10)(
      feed(feeder)
        .exec(
          http("createCategory")
            .post("/rest/categories")
            //.body(RawFileBody("createCategory.xml"))
            .body(ElFileBody("createCategory.xml"))
            .asXML
        ))

  setUp(scn
    .inject(atOnceUsers(1))
    .protocols(httpProtocol)
  ).assertions(
    global.failedRequests.count.is(0),
    forAll.requestsPerSec.greaterThan(5),
    global.responseTime.percentile1.lessThan(200)
  )

}
