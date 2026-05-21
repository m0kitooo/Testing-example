package com.learning.courses.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
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

    private List<@NotNull(message = "Email cannot be null") @Email(message = "Invalid email format") String> emails;

    private List<
            @NotBlank(message = "Phone number cannot be null")
            @Pattern(regexp = "^(\\+48|48)?[\\s-]?(\\d{3}[\\s-]?){2}\\d{3}$", message = "Invalid Polish phone number format") String>
            phoneNumbers;

    private List<@NotBlank(message = "Address cannot be blank") String> addresses;
}
