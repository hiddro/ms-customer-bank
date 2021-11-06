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

    /*Customer - Rest*/

    public Mono<ServerResponse> getAllCustomer(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(customerService.getAllCustomer(), CustomerType.class);
    }

    public Mono<ServerResponse> getByIdCustomer(ServerRequest request){

        String id = request.pathVariable("id");

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(customerService.getByIdCustomer(id), CustomerType.class);
    }

    public Mono<ServerResponse> createCustomer(ServerRequest request){

        Mono<Customer> customerMono = request.bodyToMono(Customer.class);

        String code = request.pathVariable("code");

        return customerMono.flatMap(customer -> customerService.saveCustomer(code, customer))
                .flatMap(c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c))
                        .switchIfEmpty(ServerResponse.badRequest().build()));
    }

    public Mono<ServerResponse> updateCustomer(ServerRequest request){

        Mono<Customer> customerMono = request.bodyToMono(Customer.class);

        String id = request.pathVariable("id");

        return customerMono.flatMap(customer -> customerService.updateCustomer(id, customer))
                .flatMap(c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c))
                        .switchIfEmpty(ServerResponse.badRequest().build()));
    }

    public Mono<ServerResponse> deleteCustomer(ServerRequest request){

        String id = request.pathVariable("id");

        Mono<Customer> customerMono = customerService.findById(id);

        return customerMono.flatMap(customer -> customerService.deleteCustomer(customer.getId()))
                .flatMap(c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c))
                        .switchIfEmpty(ServerResponse.badRequest().build()));
    }

    /*Customer Type - Rest*/

    public Mono<ServerResponse> getAllCustomerType(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(customerTypeService.getAllCustomertype(), CustomerType.class);
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

    public Mono<ServerResponse> deleteCustomerType(ServerRequest request){

        String id = request.pathVariable("id");

        Mono<CustomerType> customerTypeMono = customerTypeService.findById(id);

        return customerTypeMono.flatMap(customerType -> customerTypeService.deleteCustomerType(customerType.getId()))
                .flatMap(c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c))
                        .switchIfEmpty(ServerResponse.badRequest().build()));
    }
}
