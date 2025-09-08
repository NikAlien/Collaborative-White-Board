package project.CollaborativeWhiteBoard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CollaborativeWhiteBoardApplication {
	public static void main(String[] args) {
		SpringApplication.run(CollaborativeWhiteBoardApplication.class, args);
	}
}
