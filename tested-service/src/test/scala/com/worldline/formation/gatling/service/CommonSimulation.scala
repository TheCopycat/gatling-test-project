package com.worldline.formation.gatling.service

import com.typesafe.config.ConfigFactory

import io.gatling.core.Predef.configuration
import io.gatling.http.Predef.http

/**
  * Created by a501768 on 08/06/2016.
  */
trait CommonSimulation {
  val config = ConfigFactory.load("simulation.conf")

  config.entrySet().iterator()

  val httpProtocol = http
    .baseURL(config.getString("simulation.http.baseUrl"))
    .inferHtmlResources()

}
