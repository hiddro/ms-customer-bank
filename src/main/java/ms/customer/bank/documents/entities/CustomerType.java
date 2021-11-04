package ms.customer.bank.documents.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;

@Document(collection = "customer_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerType {

    @Id
    @NotEmpty
    private String id;

    private String code;

    private String name;
}
