package com.qph;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.constantUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.nothingFor;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;

public class AttendeesSimulationJava extends Simulation {
    HttpProtocolBuilder httpProtocol = http // 4
            .baseUrl("http://localhost:8080/attendees") // 5
            .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // 6
            .doNotTrackHeader("1")
            .acceptLanguageHeader("en-US,en;q=0.5")
            .acceptEncodingHeader("gzip, deflate")
            .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0");

    ScenarioBuilder scn = scenario("Get all attendees") // 7
            .exec(http("get all attendees") // 8
                    .get(""));

    {
        setUp(
                scn.injectOpen(constantUsersPerSec(20).during(15), nothingFor(40), constantUsersPerSec(20).during(15))
        ).protocols(httpProtocol);
    }
}
