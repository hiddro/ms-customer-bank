package ms.customer.bank.repository;

import ms.customer.bank.documents.entities.CustomerType;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CustomerTypeRepository extends ReactiveMongoRepository<CustomerType, String> {
    Mono<CustomerType> findByCode(String code);
}
