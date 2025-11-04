package umc.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.repository.MemberRepository;

import java.time.LocalDate;

@SpringBootApplication
@EnableJpaAuditing
public class ServerApplication {

	public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
	}

}
