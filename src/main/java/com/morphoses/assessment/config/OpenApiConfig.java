package com.morphoses.assessment.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for OpenAPI documentation.
 *
 * <p>This class configures the OpenAPI bean with project-specific information such as name,
 * version, and description.
 *
 * <p>Author: Dimitrios Milios
 */
@Configuration
public class OpenApiConfig {

  @Value("${project.name}")
  private String projectName;

  @Value("${project.version}")
  private String projectVersion;

  /**
   * Creates a custom OpenAPI bean with project information.
   *
   * @return OpenAPI instance with project details.
   */
  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(
            new Info()
                .title(projectName)
                .version(projectVersion)
                .description("API documentation for " + projectName));
  }
}
