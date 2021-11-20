package ms.customer.bank.repository;

import ms.customer.bank.documents.entities.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {

    Mono<Customer> findByCustomerIdentityNumber(String customerIdentityNumber);
}
