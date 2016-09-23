package training

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class ExampleSimulation extends Simulation {

	val httpProtocol = http
		.baseURL("http://127.0.0.1:8080")
		.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.(t|o)tf""", """.*\.png"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate, sdch")
		.acceptLanguageHeader("fr-FR,fr;q=0.8,en-US;q=0.6,en;q=0.4")
		.userAgentHeader("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36")

	val headers_0 = Map(
		"Pragma" -> "no-cache",
		"Upgrade-Insecure-Requests" -> "1")

    val uri1 = "127.0.0.1"

	val scn = scenario("ExampleSimulation")
		.exec(http("request_0")
			.get("/fibo/5")
			.headers(headers_0))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}