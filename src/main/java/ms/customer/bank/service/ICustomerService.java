package ms.customer.bank.service;

import ms.customer.bank.documents.entities.Customer;
import reactor.core.publisher.Mono;

public interface ICustomerService extends ICrudService<Customer, String>{
    Mono<Customer> saveCustomer (Customer customer);
}
