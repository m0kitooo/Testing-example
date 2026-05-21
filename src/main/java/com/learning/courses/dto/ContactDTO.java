package com.learning.courses.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ContactDTO {
    private List<String> emails;
    private List<String> phoneNumbers;
    private List<String> addresses;
}
