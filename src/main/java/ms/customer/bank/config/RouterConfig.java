package ms.customer.bank.config;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import ms.customer.bank.documents.entities.Customer;
import ms.customer.bank.documents.entities.CustomerType;
import ms.customer.bank.handler.CustomerHandler;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/getAll",
                    produces = {
                            MediaType.APPLICATION_JSON_VALUE
                    },
                    method = RequestMethod.GET,
                    beanClass = CustomerHandler.class,
                    beanMethod = "getAllCustomer",
                    operation = @Operation(
                            operationId = "getAllCustomer",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Operación Satisfactoria",
                                            content = @Content(
                                                    schema = @Schema(
                                                            implementation = Customer.class
                                                    )
                                            )
                                    )
                            }
                    )
            ),
            @RouterOperation(
                    path = "/getById/{id}",
                    produces = {
                            MediaType.APPLICATION_JSON_VALUE
                    },
                    method = RequestMethod.GET,
                    beanClass = CustomerHandler.class,
                    beanMethod = "getByIdCustomer",
                    operation = @Operation(
                            operationId = "getByIdCustomer",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Operación Satisfactoria",
                                            content = @Content(
                                                    schema = @Schema(
                                                            implementation = Customer.class
                                                    )
                                            )
                                    ),
                                    @ApiResponse(
                                            responseCode = "404",
                                            description = "Customer con el id no se encontro"
                                    )
                            },
                            parameters = {
                                    @Parameter(
                                            in = ParameterIn.PATH,
                                            name = "id"
                                    )
                            }
                    )
            ),
            @RouterOperation(
                    path = "/getByIdentity/{customerIdentityNumber}",
                    produces = {
                            MediaType.APPLICATION_JSON_VALUE
                    },
                    method = RequestMethod.GET,
                    beanClass = CustomerHandler.class,
                    beanMethod = "getByIdentity",
                    operation = @Operation(
                            operationId = "getByIdentity",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Operación Satisfactoria",
                                            content = @Content(
                                                    schema = @Schema(
                                                            implementation = Customer.class
                                                    )
                                            )
                                    ),
                                    @ApiResponse(
                                            responseCode = "404",
                                            description = "Customer con el numero de identidad no se encontro"
                                    )
                            },
                            parameters = {
                                    @Parameter(
                                            in = ParameterIn.PATH,
                                            name = "customerIdentityNumber"
                                    )
                            }
                    )
            ),
            @RouterOperation(
                    path = "/create/{code}",
                    produces = {
                            MediaType.APPLICATION_JSON_VALUE
                    },
                    method = RequestMethod.POST,
                    beanClass = CustomerHandler.class,
                    beanMethod = "createCustomer",
                    operation = @Operation(
                            operationId = "createCustomer",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Operación Satisfactoria",
                                            content = @Content(
                                                    schema = @Schema(
                                                            implementation = String.class
                                                    )
                                            )
                                    )
                            },
                            parameters = {
                                    @Parameter(
                                            in = ParameterIn.PATH,
                                            name = "code"
                                    )
                            },
                            requestBody = @RequestBody(
                                    content = @Content(
                                            schema = @Schema(
                                                implementation = Customer.class
                                            )
                                    )
                            )
                    )
            ),
            @RouterOperation(
                    path = "/update/{id}",
                    produces = {
                            MediaType.APPLICATION_JSON_VALUE
                    },
                    method = RequestMethod.PUT,
                    beanClass = CustomerHandler.class,
                    beanMethod = "updateCustomer",
                    operation = @Operation(
                            operationId = "updateCustomer",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Operación Satisfactoria",
                                            content = @Content(
                                                    schema = @Schema(
                                                            implementation = String.class
                                                    )
                                            )
                                    ),
                                    @ApiResponse(
                                            responseCode = "404",
                                            description = "Customer con el id no se encontro"
                                    )
                            },
                            parameters = {
                                    @Parameter(
                                            in = ParameterIn.PATH,
                                            name = "id"
                                    )
                            },
                            requestBody = @RequestBody(
                                    content = @Content(
                                            schema = @Schema(
                                                    implementation = Customer.class
                                            )
                                    )
                            )
                    )
            ),
            @RouterOperation(
                    path = "/delete/{id}",
                    produces = {
                            MediaType.APPLICATION_JSON_VALUE
                    },
                    method = RequestMethod.DELETE,
                    beanClass = CustomerHandler.class,
                    beanMethod = "deleteCustomer",
                    operation = @Operation(
                            operationId = "deleteCustomer",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Operación Satisfactoria",
                                            content = @Content(
                                                    schema = @Schema(
                                                            implementation = String.class
                                                    )
                                            )
                                    ),
                                    @ApiResponse(
                                            responseCode = "404",
                                            description = "Customer con el id no se encontro"
                                    )
                            },
                            parameters = {
                                    @Parameter(
                                            in = ParameterIn.PATH,
                                            name = "id"
                                    )
                            }
                    )
            ),
            @RouterOperation(
                    path = "/customerType/getAll",
                    produces = {
                            MediaType.APPLICATION_JSON_VALUE
                    },
                    method = RequestMethod.GET,
                    beanClass = CustomerHandler.class,
                    beanMethod = "getAllCustomerType",
                    operation = @Operation(
                            operationId = "getAllCustomerType",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Operación Satisfactoria",
                                            content = @Content(
                                                    schema = @Schema(
                                                            implementation = CustomerType.class
                                                    )
                                            )
                                    )
                            }
                    )
            ),
            @RouterOperation(
                    path = "/customerType/create",
                    produces = {
                            MediaType.APPLICATION_JSON_VALUE
                    },
                    method = RequestMethod.POST,
                    beanClass = CustomerHandler.class,
                    beanMethod = "createCustomerType",
                    operation = @Operation(
                            operationId = "createCustomerType",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Operación Satisfactoria",
                                            content = @Content(
                                                    schema = @Schema(
                                                            implementation = String.class
                                                    )
                                            )
                                    )
                            },
                            requestBody = @RequestBody(
                                    content = @Content(
                                            schema = @Schema(
                                                    implementation = CustomerType.class
                                            )
                                    )
                            )
                    )
            ),
            @RouterOperation(
                    path = "/customerType/delete/{id}",
                    produces = {
                            MediaType.APPLICATION_JSON_VALUE
                    },
                    method = RequestMethod.DELETE,
                    beanClass = CustomerHandler.class,
                    beanMethod = "deleteCustomerType",
                    operation = @Operation(
                            operationId = "deleteCustomerType",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Operación Satisfactoria",
                                            content = @Content(
                                                    schema = @Schema(
                                                            implementation = String.class
                                                    )
                                            )
                                    ),
                                    @ApiResponse(
                                            responseCode = "404",
                                            description = "CustomerType con el id no se encontro"
                                    )
                            },
                            parameters = {
                                    @Parameter(
                                            in = ParameterIn.PATH,
                                            name = "id"
                                    )
                            }
                    )
            )
    })
    public RouterFunction<ServerResponse> routes(CustomerHandler customerHandler){
        return route(GET("/getAll"), customerHandler::getAllCustomer)
                .andRoute(GET("/getById/{id}"), customerHandler::getByIdCustomer)
                .andRoute(GET("/getByIdentity/{customerIdentityNumber}"), customerHandler::getByIdentity)
                .andRoute(POST("/create/{code}"), customerHandler::createCustomer)
                .andRoute(PUT("/update/{id}"), customerHandler::updateCustomer)
                .andRoute(DELETE("/delete/{id}"), customerHandler::deleteCustomer)
                .andRoute(GET("/customerType/getAll"), customerHandler::getAllCustomerType)
                .andRoute(POST("/customerType/create"), customerHandler::createCustomerType)
                .andRoute(DELETE("/customerType/delete/{id}"), customerHandler::deleteCustomerType);
    }
}
