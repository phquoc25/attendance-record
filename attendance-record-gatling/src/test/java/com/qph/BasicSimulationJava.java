package com.qph;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.atOnceUsers;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;

public class BasicSimulationJava extends Simulation {
    HttpProtocolBuilder httpProtocol = http // 4
            .baseUrl("http://computer-database.gatling.io") // 5
            .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // 6
            .doNotTrackHeader("1")
            .acceptLanguageHeader("en-US,en;q=0.5")
            .acceptEncodingHeader("gzip, deflate")
            .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0");

    ScenarioBuilder scn = scenario("BasicSimulation") // 7
            .exec(http("request_1") // 8
                    .get("/")) // 9
            .pause(5); // 10

    {
        setUp( // 11
                scn.injectOpen(atOnceUsers(1)) // 12
        ).protocols(httpProtocol); // 13
    }
}
