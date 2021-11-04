package ms.customer.bank.service.impl;

import ms.customer.bank.documents.entities.Customer;
import ms.customer.bank.repository.CustomerRepository;
import ms.customer.bank.repository.CustomerTypeRepository;
import ms.customer.bank.service.ICrudService;
import ms.customer.bank.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerTypeRepository customerTypeRepository;

    @Override
    public Mono<Customer> create(Customer o) {
        return null;
    }

    @Override
    public Flux<Customer> findAll() {
        return null;
    }

    @Override
    public Mono<Customer> findById(String s) {
        return null;
    }

    @Override
    public Mono<Customer> update(Customer o) {
        return null;
    }

    @Override
    public Mono<Void> delete(Customer o) {
        return null;
    }

    @Override
    public Mono<Customer> saveCustomer(Customer customer) {
        return customerTypeRepository.findByCode(customer.getCustomerType().getCode())
                .flatMap(type -> {
                    if(customer.getCustomerType() != null && !customer.getCustomerType().getCode().equals(customer.getCustomerType().getCode())){
                        return Mono.empty();
                    } else {
                        customer.setCustomerType(type);
                        return customerRepository.save(customer);
                    }
                });
    }
}
