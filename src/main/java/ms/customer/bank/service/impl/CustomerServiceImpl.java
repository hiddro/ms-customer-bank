package ms.customer.bank.service.impl;

import ms.customer.bank.documents.entities.Customer;
import ms.customer.bank.documents.entities.CustomerType;
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
    public Mono<Customer> findById(String id) {
        return customerRepository.findById(id);
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
    public Mono<Customer> saveCustomer(String code, Customer customer) {
        System.out.println(code + " " + customer);

        return customerTypeRepository.findByCode(code)
                .flatMap(type -> {
                    if(customer.getCustomerType() != null && !customer.getCustomerType().getCode().equals(customer.getCustomerType().getCode())){
                        return Mono.empty();
                    } else {
                        customer.setCustomerType(type);
                        return customerRepository.save(customer);
                    }
                });
    }

    @Override
    public Flux<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Mono<Customer> getByIdCustomer(String id) {
        return customerRepository.findById(id);
    }

    @Override
    public Mono<Customer> updateCustomer(String id, Customer customer) {

        Mono<Customer> customerExist = customerRepository.findById(id);
        return customerExist.flatMap(existCustomer -> {
                    existCustomer.setName(customer.getName());
                    existCustomer.setCustomerIdentityType(customer.getCustomerIdentityType());
                    existCustomer.setCustomerIdentityNumber(customer.getCustomerIdentityNumber());
                    existCustomer.setPhone(customer.getPhone());
                    existCustomer.setAddress(customer.getAddress());
                    existCustomer.setEmail(customer.getEmail());
                    existCustomer.setCustomerType(customer.getCustomerType());
                    return customerRepository.save(existCustomer);
                }).switchIfEmpty(Mono.just(Customer.builder().build()));
    }

    @Override
    public Mono<Void> deleteCustomer(String id) {
        return customerRepository.deleteById(id);
    }
}
