package ms.customer.bank.config;

import ms.customer.bank.handler.CustomerHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> routes(CustomerHandler customerHandler){
        return route(GET("/getAll"), customerHandler::getAllCustomer)
                .andRoute(GET("/getById/{id}"), customerHandler::getByIdCustomer)
                .andRoute(POST("/create/{code}"), customerHandler::createCustomer)
                .andRoute(PUT("/update/{id}"), customerHandler::updateCustomer)
                .andRoute(DELETE("/delete/{id}"), customerHandler::deleteCustomer)
                .andRoute(GET("/customerType/getAll"), customerHandler::getAllCustomerType)
                .andRoute(POST("/customerType/create"), customerHandler::createCustomerType)
                .andRoute(DELETE("/customerType/delete/{id}"), customerHandler::deleteCustomerType);
    }
}
