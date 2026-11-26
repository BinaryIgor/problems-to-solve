package com.example.some_api;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.client.RestTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseIntegrationTest {

    protected RestTestClient restTestClient;

    @LocalServerPort
    private int serverPort;

    @BeforeEach
    void setup() {
        var baseAppUrl = "http://localhost:" + serverPort;
        restTestClient = RestTestClient.bindToServer()
                .baseUrl(baseAppUrl)
                .build();
    }

}
