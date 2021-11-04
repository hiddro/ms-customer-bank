package ms.customer.bank.handler;

import ms.customer.bank.documents.entities.Customer;
import ms.customer.bank.documents.entities.CustomerType;
import ms.customer.bank.service.ICustomerService;
import ms.customer.bank.service.ICustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class CustomerHandler {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ICustomerTypeService customerTypeService;

    public Mono<ServerResponse> createCustomer(ServerRequest request){

        Mono<Customer> customerMono = request.bodyToMono(Customer.class);

        return customerMono.flatMap(customer -> customerService.saveCustomer(customer))
                .flatMap(c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c))
                        .switchIfEmpty(ServerResponse.badRequest().build()));
    }

    public Mono<ServerResponse> createCustomerType(ServerRequest request){

        Mono<CustomerType> customerTypeMono = request.bodyToMono(CustomerType.class);

        return customerTypeMono.flatMap(customerType -> customerTypeService.saveCustomerType(customerType))
                .flatMap(c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c))
                        .switchIfEmpty(ServerResponse.badRequest().build()));
    }
}
