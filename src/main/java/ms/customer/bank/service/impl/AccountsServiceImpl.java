package ms.customer.bank.service.impl;

import ms.customer.bank.documents.dto.AccountsDto;
import ms.customer.bank.documents.dto.CustomerDto;
import ms.customer.bank.service.IAccountsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AccountsServiceImpl implements IAccountsService {

    private static final Logger log = LoggerFactory.getLogger(AccountsServiceImpl.class);

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public Mono<AccountsDto> getFixedTerm(String customerIdentityNumber) {
        return webClientBuilder
                .build()
                .get()
                .uri("http://localhost:8100/api/fixedterm/getByIdCustomer/"+customerIdentityNumber)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(customerResponse -> customerResponse.bodyToMono(AccountsDto.class))
                .doOnNext(c -> log.info("Customer Response: Customer={}"+ c.getTypeOfAccount()))
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<AccountsDto> getSaving(String customerIdentityNumber) {
        return webClientBuilder
                .build()
                .get()
                .uri("http://localhost:8100/api/saving/getByIdCustomer/"+customerIdentityNumber)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(customerResponse -> customerResponse.bodyToMono(AccountsDto.class))
                .doOnNext(c -> log.info("Customer Response: Customer={}"+ c.getTypeOfAccount()))
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<AccountsDto> getCurrent(String customerIdentityNumber) {
        return webClientBuilder
                .build()
                .get()
                .uri("http://localhost:8100/api/current/getByIdCustomer/"+customerIdentityNumber)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(customerResponse -> customerResponse.bodyToMono(AccountsDto.class))
                .doOnNext(c -> log.info("Customer Response: Customer={}"+ c.getTypeOfAccount()))
                .switchIfEmpty(Mono.empty());
    }
}
