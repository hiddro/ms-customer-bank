package ms.customer.bank.service;

import ms.customer.bank.documents.dto.AccountsDto;
import ms.customer.bank.documents.dto.CustomerDto;
import reactor.core.publisher.Mono;

public interface IAccountsService {
    Mono<AccountsDto> getFixedTerm(String customerIdentityNumber);

    Mono<AccountsDto> getSaving(String customerIdentityNumber);

    Mono<AccountsDto> getCurrent(String customerIdentityNumber);
}
