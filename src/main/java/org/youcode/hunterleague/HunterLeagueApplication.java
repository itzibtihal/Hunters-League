package org.youcode.hunterleague;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
public class HunterLeagueApplication {

    public static void main(String[] args) {
        SpringApplication.run(HunterLeagueApplication.class, args);
    }

}
