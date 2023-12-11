package com.rbi.accounts;

import com.rbi.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = AccountsContactInfoDto.class)
@OpenAPIDefinition(info = @Info(title = "Accounts microservice REST API Documentation",
		description = "RBI Accounts microservice REST API Documentation",
		version = "V1.1",
		contact = @Contact(
				name = "Shreyas V",
				email = "shreyas@gmail.com",
				url = "www.google.com"
		), license = @License(
		name = "Apache 2.0",
		url = "https://asd.com"
)
), externalDocs = @ExternalDocumentation(
		description = "RBI Accounts microservice REST API documentation",
		url = "http://localhost:8080/swagger-ui/index.html"
)
)
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

}
