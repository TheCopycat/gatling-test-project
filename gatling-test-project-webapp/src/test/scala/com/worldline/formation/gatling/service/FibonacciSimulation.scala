package com.worldline.formation.gatling.service

import java.util.Calendar

import scala.collection.JavaConversions._
import scala.util.Random

import com.typesafe.config.ConfigValue
import com.worldline.formation.gatling.service.sample.Feeders

import io.gatling.core.Predef._
import io.gatling.core.Predef.configuration
import io.gatling.core.structure.Feeds
import io.gatling.http.Predef._
import io.gatling.http.Predef.http

class FibonacciSimulation extends Simulation with CommonSimulation {

  val feeder = Iterator.continually(Map("value1" -> Random.nextInt(100), "value2" -> Calendar.getInstance.getTime.getTime))


  val session = config.getConfig("simulation.session").entrySet().map(entry => (entry.getKey, entry.getValue.unwrapped()))


  //	val session = Map[String,Any]("username" -> 200,
  //									"value1" -> 200)
  val feedercsv = csv("user_credentials.csv").circular.random
  val uri1 = config.getString("simulation.http.baseUrl") + "/fibo"

  import Feeders._

  val scn = scenario("FibonacciSimulation").feed(oneLineCsvFeeder)
    .exec(http("Fibo n")
      .get("/fibo/${value1}")
      .check(status.is(200))
      .check(bodyString.is(ElFileBody("FibonacciSimulation_0000_response.txt"))))


  //This is the setup
  before {
    println("loading values...")
    Thread.sleep(3000)
    println("loaded")
  }

  //This is the teardown
  after {
    println("removing values...")
    Thread.sleep(3000)
    println("removed")
  }

  setUp(scn.inject(atOnceUsers(10))).protocols(httpProtocol)
   // .assertions(global.responseTime.percentile4.lessThan(2000))

}