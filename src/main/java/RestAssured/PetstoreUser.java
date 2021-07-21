
package RestAssured;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class PetstoreUser {

    private String email;
    private String firstName;
    private Long id;
    private String lastName;
    private String password;
    private String phone;
    private Long userStatus;
    private String username;

}
