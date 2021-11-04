package ms.customer.bank.repository;

import ms.customer.bank.documents.entities.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {
}
