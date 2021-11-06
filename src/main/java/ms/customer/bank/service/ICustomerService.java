package ms.customer.bank.service;

import ms.customer.bank.documents.entities.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICustomerService extends ICrudService<Customer, String>{
    Mono<Customer> saveCustomer (String code, Customer customer);

    Flux<Customer> getAllCustomer();

    Mono<Customer> getByIdCustomer(String id);

    Mono<Customer> updateCustomer (String id, Customer customer);

    Mono<Void> deleteCustomer (String id);
}
