package ms.customer.bank.service.impl;

import ms.customer.bank.documents.entities.CustomerType;
import ms.customer.bank.repository.CustomerTypeRepository;
import ms.customer.bank.service.ICustomerService;
import ms.customer.bank.service.ICustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerTypeServiceImpl implements ICustomerTypeService {

    @Autowired
    private CustomerTypeRepository customerTypeRepository;

    @Override
    public Mono<CustomerType> create(CustomerType o) {
        return customerTypeRepository.save(o);
    }

    @Override
    public Flux<CustomerType> findAll() {
        return customerTypeRepository.findAll();
    }

    @Override
    public Mono<CustomerType> findById(String id) {
        return customerTypeRepository.findById(id);
    }

    @Override
    public Mono<CustomerType> update(CustomerType o) {
        return customerTypeRepository.save(o);
    }

    @Override
    public Mono<Void> delete(CustomerType o) {
        return customerTypeRepository.delete(o);
    }

    @Override
    public Mono<CustomerType> saveCustomerType(CustomerType customerType) {
        return customerTypeRepository.save(customerType);
    }

    @Override
    public Flux<CustomerType> getAllCustomertype() {
        return customerTypeRepository.findAll();
    }

    @Override
    public Mono<Void> deleteCustomerType(String id) {

        return customerTypeRepository.deleteById(id);
    }
}
