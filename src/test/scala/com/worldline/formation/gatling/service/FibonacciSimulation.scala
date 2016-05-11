package com.worldline.formation.gatling.service

import java.util.Calendar
import java.util.concurrent.TimeUnit

import com.typesafe.config.ConfigFactory
import io.gatling.core.feeder.RecordSeqFeederBuilder
import org.springframework.boot.test.IntegrationTest

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import scala.util.Random

class FibonacciSimulation extends Simulation {

	val config = ConfigFactory.load("simulation.conf")

	val httpProtocol = http
		.baseURL(config.getString("simulation.http.baseUrl"))
		.inferHtmlResources()

	val feeder = Iterator.continually(Map("value1" -> Random.nextInt(100),"value2" -> Calendar.getInstance.getTime.getTime))

	val uri1 = config.getString("simulation.http.baseUrl")+"/fibo"

	val scn = scenario("FibonacciSimulation")
		.feed(feeder)
		.exec(http("Fibo n")
			.get("/fibo/${value1}")
			.check(status.is(200)))//bodyString.is(RawFileBody("FibonacciSimulation_0000_response.txt"))))
		.pause(4)
		.exec(http("Fibo 200")
			.get("/fibo/200")
			.check(bodyString.is(RawFileBody("FibonacciSimulation_0001_response.txt"))))

	setUp(scn.inject(heavisideUsers(500) over (5 seconds))).protocols(httpProtocol)
	  .assertions(global.responseTime.percentile4.lessThan(200))
}