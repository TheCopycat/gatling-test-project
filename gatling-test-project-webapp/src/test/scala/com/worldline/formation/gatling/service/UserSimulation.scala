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

import scala.concurrent.duration.DurationLong

/**
  * Created by a501768 on 05/08/2016.
  */
class UserSimulation extends Simulation with CommonSimulation {

  val scn = scenario("UserSimulation").
      exec(http("postUser")
        .post("/users")
        .body(RawFileBody("user.json"))
        .header("Content-Type","application/json")
          .check(status.is(201))
        .check(jsonPath("$.id").saveAs("userId")).check(header("location").saveAs("newLocation")))

        .exec(http("getUser").get("${newLocation}")
      .check(jsonPath("$.id").is("${userId}")))

  setUp(scn.inject(constantUsersPerSec(10) during (5 seconds))).protocols(httpProtocol)



}
