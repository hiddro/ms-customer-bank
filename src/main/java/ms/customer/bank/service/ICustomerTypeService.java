package ms.customer.bank.service;

import ms.customer.bank.documents.entities.CustomerType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICustomerTypeService extends ICrudService<CustomerType, String>{
    Mono<CustomerType> saveCustomerType (CustomerType customerType);

    Flux<CustomerType> getAllCustomertype();

    Mono<Void> deleteCustomerType (String id);
}
