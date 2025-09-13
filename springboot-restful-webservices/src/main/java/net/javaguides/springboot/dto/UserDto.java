package net.javaguides.springboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        description = "UserDTO Model Information"
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @Schema(
            description = "User First Name"
    )
    //User firstName shouldn't be null or empty
    @NotEmpty(message = "User firstName shouldn't be null or empty")
    private String firstName;

    @Schema(
            description = "User Last Name"
    )
    //User lastName shouldn't be null or empty
    @NotEmpty(message = "User lastName shouldn't be null or empty")
    private String lastName;

    @Schema(
            description = "User Email Address"
    )
    //User email shouldn't be null or empty
    //Email address should be valid
    @NotEmpty(message = "User email shouldn't be null or empty")
    @Email(message = "Email address should be valid")
    private String email;

}
