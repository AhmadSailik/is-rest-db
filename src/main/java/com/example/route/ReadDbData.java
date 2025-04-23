package com.example.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.stereotype.Component;

@Component
public class ReadDbData extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        restConfiguration()
                .component("netty-http")
                .contextPath("/api")
                .port(8081)
                .bindingMode(RestBindingMode.json);

        rest()
                .skipBindingOnErrorCode(true)
                .clientRequestValidation(true)
                .description("testAPI")
                .get("/getUsers").produces("application/json")
                .to("direct:getUsers")
                .post("/addUser")
                .param().name("name").type(RestParamType.query).dataType("string").required(true).endParam()
                .to("direct:addNewUser");

        from("direct:getUsers")
                .routeId("getUsersRoute")
                .to("sql:select * from users")
                .log("Query result: ${body}");

        from("direct:addNewUser")
                .routeId("addNewUserRoute")
                .to("sql:INSERT INTO users(FirstName)VALUES(:#name)")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200)).setBody(constant((Object) null));

    }
}
