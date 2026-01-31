package org.example.topprogramingstoriesdashboard;

import org.springframework.boot.SpringApplication;

public class TestTopProgramingStoriesDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.from(TopProgramingStoriesDashboardApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
