package ms.customer.bank.documents.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.*;
import java.util.Date;

@Document(collection = "customers")
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    private String id;

    @Field(name = "customerIdentityType")
    private String customerIdentityType;

    @Indexed(unique=true)
    @Field(name = "customerIdentityNumber")
    private String customerIdentityNumber;

    @Size(max = 40)
    @Field(name = "name")
    private String name;

    @Size(max = 75)
    @Email
    @Field(name = "email")
    private String email;

    @Size(max = 9)
    @Field(name = "phone")
    private String phone;

    @Field(name = "address")
    private String address;

    @Field(name = "customerType")
    private CustomerType customerType;

    @Field(name = "create_client")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dateOperation = new Date();
}
