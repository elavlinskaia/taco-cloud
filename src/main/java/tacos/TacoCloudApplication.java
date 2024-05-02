package tacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * сборка: ./mvnw package
 * тестирование: ./mvnw spring-boot:run
 */

@SpringBootApplication // приложение SpringBoot
public class TacoCloudApplication {

	public static void main(String[] args) { // вызвывается в момент запуска файла JAR
		SpringApplication.run(TacoCloudApplication.class, args); // запуск приложения
	}

}
