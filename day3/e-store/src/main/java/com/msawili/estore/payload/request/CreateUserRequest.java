package com.msawili.estore.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserRequest {
    @NotBlank(message = "'email' is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "'password' is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @JsonProperty("first_name")
    @NotBlank(message = "'first_name' is required")
    private String firstName;

    @JsonProperty("middle_name")
    @NotBlank(message = "'middle_name' is required")
    private String middleName;

    @JsonProperty("last_name")
    @NotBlank(message = "'last_name' is required")
    private String lastName;

    @JsonProperty("birth_date")
    @NotBlank(message = "'birth_date' is required")
    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "Birthdate must be in YYYY-MM-DD format")
    private String birthDate;
}
