package com.github.fernandotaa.springbatch.multipleexecutions;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchMultipleExecutionsApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBatchMultipleExecutionsApplication.class, args);
	}
}
