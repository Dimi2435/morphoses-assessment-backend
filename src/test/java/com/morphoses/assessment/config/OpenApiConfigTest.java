// package com.morphoses.assessment.config;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// import io.swagger.v3.oas.models.OpenAPI;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
// import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.ActiveProfiles;
// // If you specifically excluded FlywayAutoConfiguration, ensure its import is here too:
// // import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;

// @SpringBootTest(
//     webEnvironment = SpringBootTest.WebEnvironment.NONE
// )
// @ActiveProfiles("test")
// public class OpenApiConfigTest {

//   @Autowired private OpenApiConfig openApiConfig;

//   @Test
//   public void testCustomOpenAPI() {
//     OpenAPI openAPI = openApiConfig.customOpenAPI();

//     String expectedTitle = "Morphoses Assessment Backend";
//     String expectedVersion = "1.0.0";
//     String expectedDescription = "API documentation for Morphoses Assessment Backend";

//     assertEquals(expectedTitle, openAPI.getInfo().getTitle());
//     assertEquals(expectedVersion, openAPI.getInfo().getVersion());
//     assertEquals(expectedDescription, openAPI.getInfo().getDescription());
//   }
// }
