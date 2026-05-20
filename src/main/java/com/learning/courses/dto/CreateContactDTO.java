package com.learning.courses.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@Valid
public class CreateContactDTO {

    @NotEmpty(message = "At least one email is required, passing null allowed")
    private List<@NotNull(message = "Email cannot be null") @Email(message = "Invalid email format") String> emails;

    @NotEmpty(message = "At least one phone number is required, passing null allowed")
    private List<@NotBlank(message = "Phone number cannot be blank") String> phoneNumbers;

    @NotEmpty(message = "At least one address is required, passing null allowed")
    private List<@NotBlank(message = "Address cannot be blank") String> addresses;
}
