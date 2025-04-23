package com.example;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.SimpleRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestdbApplication {

	public static void main(String[] args) {

		SpringApplication.run(RestdbApplication.class, args);

	}


}
