package com.learning.courses.service;

import com.learning.courses.dto.ContactDTO;
import com.learning.courses.dto.CreateContactDTO;
import com.learning.courses.dto.CreatePersonDTO;
import com.learning.courses.dto.DetailedPersonDTO;
import com.learning.courses.dto.PersonDTO;
import com.learning.courses.exception.EntityNotFoundException;
import com.learning.courses.mapper.PersonMapper;
import com.learning.courses.model.Address;
import com.learning.courses.model.Contact;
import com.learning.courses.model.Email;
import com.learning.courses.model.Person;
import com.learning.courses.model.PhoneNumber;
import com.learning.courses.repository.PersonRepository;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

  private final PersonRepository personRepository;
  private final PersonMapper personMapper;

  @Transactional
  public Long createPerson(CreatePersonDTO createPersonDTO) {
    final Person person = personMapper.toEntity(createPersonDTO);

    return personRepository.save(person).getId();
  }

  @Transactional(readOnly = true)
  public DetailedPersonDTO getPerson(@NotNull @Positive Long id) {
    return personRepository.findById(id)
        .map(personMapper::toDetailedDTO)
        .orElseThrow(() -> new EntityNotFoundException(id, Person.class.getSimpleName()));
  }

  @Transactional(readOnly = true)
  public Person getPersonEntity(@NotNull @Positive Long id) {
    return personRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(id, Person.class.getSimpleName()));
  }

  @Transactional
  public PersonDTO updatePerson(Long id, PersonDTO updatedPerson) {
    var person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, Person.class.getSimpleName()));
    person.setRole(updatedPerson.getRole());
    person.setFirstName(updatedPerson.getFirstName());
    person.setLastName(updatedPerson.getLastName());
    person.setIdentityNumber(updatedPerson.getIdentityNumber());
    person = personRepository.save(person);
    return personMapper.toDTO(person);
  }

  @Transactional
  public ContactDTO addContact(@NotNull @Positive Long personId, CreateContactDTO createContactDTO) {
    Person person = personRepository.findById(personId)
        .orElseThrow(() -> new EntityNotFoundException(personId, Person.class.getSimpleName()));

    List<Email> emails = createContactDTO.getEmails() == null ? List.of() :
        createContactDTO.getEmails().stream()
            .map(e -> Email.builder().email(e).build())
            .toList();

    List<PhoneNumber> phoneNumbers = createContactDTO.getPhoneNumbers() == null ? List.of() :
        createContactDTO.getPhoneNumbers().stream()
            .map(p -> PhoneNumber.builder().phoneNumber(p).build())
            .toList();

    List<Address> addresses = createContactDTO.getAddresses() == null ? List.of() :
        createContactDTO.getAddresses().stream()
            .map(a -> Address.builder().address(a).build())
            .toList();

    person.setContact(Contact.builder()
        .emails(emails)
        .phoneNumbers(phoneNumbers)
        .addresses(addresses)
        .build());

    personRepository.save(person);

    return ContactDTO.builder()
        .emails(createContactDTO.getEmails())
        .phoneNumbers(createContactDTO.getPhoneNumbers())
        .addresses(createContactDTO.getAddresses())
        .build();
  }

  @Transactional
  public void deleteContact(@NotNull @Positive Long personId) {
    Person person = personRepository.findById(personId)
        .orElseThrow(() -> new EntityNotFoundException(personId, Person.class.getSimpleName()));

    person.setContact(null);
    personRepository.save(person);
  }

}
