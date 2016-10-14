package training

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class RecordedSimulation extends Simulation {

	val httpProtocol = http
		.baseURL("http://oscobai064s.sys.meshcore.net:8000")
		.inferHtmlResources(BlackList(), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate, sdch")
		.acceptLanguageHeader("fr-FR,fr;q=0.8,en-US;q=0.6,en;q=0.4")
		.userAgentHeader("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36")

	val headers_0 = Map("Upgrade-Insecure-Requests" -> "1")

    val uri1 = "http://oscobai064s.sys.meshcore.net:8000/petstoreee7-7.0"

	val scn = scenario("RecordedSimulation")
		.exec(http("request_0")
			.get("/petstoreee7-7.0")
			.headers(headers_0))
		.pause(5)
		.exec(http("request_1")
			.get("/petstoreee7-7.0/shopping/showproducts.xhtml?categoryName=Fish")
			.headers(headers_0))
		.pause(2)
		.exec(http("request_2")
			.get("/petstoreee7-7.0/shopping/showitems.xhtml?productId=1000")
			.headers(headers_0))
		.pause(1)
		.exec(http("request_3")
			.get("/petstoreee7-7.0/shopping/showitem.xhtml?itemId=1000")
			.headers(headers_0))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}