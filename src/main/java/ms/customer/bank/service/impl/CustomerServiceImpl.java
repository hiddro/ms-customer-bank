package ms.customer.bank.service.impl;

import ms.customer.bank.documents.dto.AccountsDto;
import ms.customer.bank.documents.dto.CustomerDto;
import ms.customer.bank.documents.entities.Customer;
import ms.customer.bank.documents.entities.CustomerType;
import ms.customer.bank.repository.CustomerRepository;
import ms.customer.bank.repository.CustomerTypeRepository;
import ms.customer.bank.service.IAccountsService;
import ms.customer.bank.service.ICrudService;
import ms.customer.bank.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private IAccountsService accountsService;

    @Autowired
    private CustomerTypeRepository customerTypeRepository;

    @Override
    public Mono<Customer> create(Customer o) {
        return customerRepository.save(o);
    }

    @Override
    public Flux<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Mono<Customer> findById(String id) {
        return customerRepository.findById(id);
    }

    @Override
    public Mono<Customer> update(Customer o) {
        return customerRepository.save(o);
    }

    @Override
    public Mono<Void> delete(Customer o) {
        return customerRepository.delete(o);
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
    public Mono<CustomerDto> getAllAccounts(String identity) {
        CustomerDto customerDto = new CustomerDto();

        return customerRepository.findByCustomerIdentityNumber(identity).flatMap(c -> {
            if(c.getId() == null){
                return Mono.empty();
            }
            customerDto.setId(c.getId());
            customerDto.setName(c.getName());
            customerDto.setCustomerIdentityType(c.getCustomerIdentityType());
            customerDto.setCustomerIdentityNumber(c.getCustomerIdentityNumber());
            List<AccountsDto> listaCuentas = new ArrayList<>();

            return accountsService.getFixedTerm(identity).flatMap(fixed -> {
                AccountsDto fixedAccount = new AccountsDto();
                fixedAccount.setId(fixed.getId());
                fixedAccount.setAccountNumber(fixed.getAccountNumber());
                fixedAccount.setTypeOfAccount(fixed.getTypeOfAccount());
                fixedAccount.setCustomerIdentityNumber(fixed.getCustomerIdentityNumber());
                fixedAccount.setAmount(fixed.getAmount());
                listaCuentas.add(fixedAccount);

                return accountsService.getSaving(identity).flatMap(saving -> {
                    AccountsDto savingAccount = new AccountsDto();
                    savingAccount.setId(saving.getId());
                    savingAccount.setAccountNumber(saving.getAccountNumber());
                    savingAccount.setTypeOfAccount(saving.getTypeOfAccount());
                    savingAccount.setCustomerIdentityNumber(saving.getCustomerIdentityNumber());
                    savingAccount.setAmount(saving.getAmount());
                    listaCuentas.add(savingAccount);

                    return accountsService.getCurrent(identity).flatMap(current -> {
                        AccountsDto currentAccount = new AccountsDto();
                        currentAccount.setId(current.getId());
                        currentAccount.setAccountNumber(current.getAccountNumber());
                        currentAccount.setTypeOfAccount(current.getTypeOfAccount());
                        currentAccount.setCustomerIdentityNumber(current.getCustomerIdentityNumber());
                        currentAccount.setAmount(current.getAmount());
                        listaCuentas.add(currentAccount);

                        customerDto.setAccounts(listaCuentas);

                        return Mono.just(customerDto);
                    });
                });
            });

        });
    }

    @Override
    public Mono<Customer> getByIdCustomer(String id) {

        Mono<Customer> cust = customerRepository.findById(id).switchIfEmpty(Mono.empty());

        return cust;
    }

    @Override
    public Mono<Customer> getBydIdentity(String identity) {
        Mono<Customer> cust = customerRepository.findByCustomerIdentityNumber(identity).switchIfEmpty(Mono.empty());

        return cust;
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
