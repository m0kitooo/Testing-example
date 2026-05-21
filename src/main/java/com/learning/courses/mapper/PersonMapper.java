package com.learning.courses.mapper;

import com.learning.courses.dto.ContactDTO;
import com.learning.courses.dto.CreatePersonDTO;
import com.learning.courses.dto.DetailedPersonDTO;
import com.learning.courses.dto.PersonDTO;
import com.learning.courses.model.Address;
import com.learning.courses.model.Contact;
import com.learning.courses.model.Email;
import com.learning.courses.model.Person;
import com.learning.courses.model.PhoneNumber;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PersonMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "assignedCourses", ignore = true)
  @Mapping(target = "tutoringCourses", ignore = true)
  @Mapping(target = "contact", ignore = true)
  Person toEntity(CreatePersonDTO createPersonDTO);

  PersonDTO toDTO(Person person);

  @Mapping(target = "contact", source = "contact", qualifiedByName = "contactToDTO")
  DetailedPersonDTO toDetailedDTO(Person person);

  List<PersonDTO> toDTO(List<Person> personList);

  @Named("contactToDTO")
  default ContactDTO contactToDTO(Contact contact) {
    if (contact == null) return null;
    return ContactDTO.builder()
        .emails(contact.getEmails() == null ? List.of() :
            contact.getEmails().stream().map(Email::getEmail).toList())
        .phoneNumbers(contact.getPhoneNumbers() == null ? List.of() :
            contact.getPhoneNumbers().stream().map(PhoneNumber::getPhoneNumber).toList())
        .addresses(contact.getAddresses() == null ? List.of() :
            contact.getAddresses().stream().map(Address::getAddress).toList())
        .build();
  }
}
