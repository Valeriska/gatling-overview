package gatling.tests

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class TestAPI extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost")

  val scn = scenario("Get Request")
    .exec(http("Get IP - request_1").get("/ip")
      .check(status.is(200)))
    .pause(1)
    .exec(http("Get Headers - request_2").get("/headers")
      .check(status.is(200)))
    .pause(1)
    .exec(http("Get User-Agent - request_3").get("/user-agent")
      .check(status.is(200)))
    .pause(1)
    .exec(http("Get Anything - request_4").get("/anything")
      .check(status.is(200)))
    .pause(1)
    .exec(http("Get base64 (Hello World!)- request_5").get("/base64/SGVsbG8gV29ybGQh")
      .check(status.is(200)))
    .pause(1)

  setUp(
    scn.inject(rampUsers(1).during(5))
      .protocols(httpProtocol))
}
