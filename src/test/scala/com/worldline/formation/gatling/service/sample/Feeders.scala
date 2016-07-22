package com.worldline.formation.gatling.service.sample

import com.typesafe.config.ConfigFactory
import com.worldline.formation.gatling.service.CommonSimulation
import io.gatling.core.Predef._
import io.gatling.core.feeder.{Random, RecordSeqFeederBuilder}
import io.gatling.core.session.Expression
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import io.gatling.jdbc
import scala.collection.JavaConversions._
import scala.util

/**
  * Created by a501768 on 08/06/2016.
  */
object Feeders {

  //This feeder will always return value1 and 23
  //continually ensures that the feeder is never depleted
  val simpleFeeder = Iterator.continually(Map("key1" -> "value1", "key2" -> 23))

  // Csv Feeder : loads a csv file to provide a feeder
  val feederCsv = csv("user_credentials.csv")

  //circular guarantees that the feeder is never depleted
  //random ensure randomness
  val randomCsv = feederCsv.circular.random

  //This jdbc feeder will provide values for ${username}, ${email}, and ${age}
  //important : call Class.forName before initiating the jdbcFeeder to ensure the driver is loaded
  //of course the class must be in the dependencies
  Class.forName("com.mysql.jdbc.Driver")
  lazy val feederJdbc = jdbcFeeder("jdbc:mysql://localhost:3306/myschema","login","password","SELECT username,email,age from users")


  // Expression : call it in scenario using
  // expression where an expression is needed
  // expression(_) when concat with a string : "/fibo/"+expression(_)
  // if no failure is possible, prefer type Session => Int over Expression[Int]
  val expression: Session => Int = {
    session: Session =>
      util.Random.nextInt(100)
  }

  // Static session in conf file
  // a bit overkill
  val session = ConfigFactory.load("simulation.conf").getConfig("simulation.session").entrySet().map(entry => (entry.getKey,entry.getValue.unwrapped()))
  // entry.getValue.unwrapped returns the correctly typed value (int, string, bool, etc.)
  // add in scenario using .exec(_.setAll(session))

  //prefere this one
  val oneLineCsvFeeder = csv("onelinefeeder.csv").circular

}
