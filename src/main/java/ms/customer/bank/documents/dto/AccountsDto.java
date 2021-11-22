package ms.customer.bank.documents.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountsDto {

    @Id
    private String id;

    private String typeOfAccount;

    private String customerIdentityNumber;

    private String accountNumber;

    private double amount;
}
