/*
 * Vytvořeno jako studentský zápočtový projekt v rámci studia FIM UHK.
 * 2021 - Petr Veselý
 */

package cz.fim.uhk.lab.security;

import cz.fim.uhk.lab.exeption.RecordNotFoundException;
import cz.fim.uhk.lab.model.*;
import cz.fim.uhk.lab.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/*
    UserManagementService is a service that is primarily used by the User Management controller,
    and primarily interfaces with the Person entity and PersonRepository JPA Repository.
    It encapsulates the methods that the User Management controller needs to function.
 */

@Service
public class UserManagementService {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    PersonPrivilegeRepository personPrivilegeRepository;
    @Autowired
    PrivilegeLevelCodebookRepository privilegeLevelCodebookRepository;
    @Autowired
    SecuredPersonInfoRepository securedPersonInfoRepository;
    @Autowired
    PhotoRepository photoRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    public List<Person> getAllPersons() {
        List<Person> result = personRepository.findAll();
        result.sort(Comparator.comparing(Person::getLastName));

        if (result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Person>();
        }
    }

    public Person getPersonById(Long id) throws RecordNotFoundException {
        Optional<Person> person = personRepository.findById(id);

        if (person.isPresent()) {
            return person.get();
        } else {
            throw new RecordNotFoundException("No person record exist for given id");
        }
    }

    public Long createPerson() {

        Person newPerson = new Person();
        Photo newPhoto = new Photo();
        SecuredPersonInfo newSecuredPersonInfo = new SecuredPersonInfo();


        newPerson.setFirstName("");
        newPerson.setLastName("");
        newPerson.setDescription("");
        newPerson.setBorrowedList(new ArrayList<>());
        newPerson.setPersonPrivilegeList(new ArrayList<>());

        personRepository.save(newPerson);

        newSecuredPersonInfo.setPerson(newPerson);
        newPhoto.setPerson(newPerson);

        newSecuredPersonInfo.setLogin("");
        newSecuredPersonInfo.setPassword("");
        newSecuredPersonInfo.setUserrole("ROLE_USER");
        newSecuredPersonInfo.setPerson(newPerson);
        newSecuredPersonInfo.setPersonid(newPerson.getPersonid());
        //newPhoto.setImage("/img/blank-user.png");
        newPhoto.setDataType("");
        newPhoto.setPerson(newPerson);

        securedPersonInfoRepository.save(newSecuredPersonInfo);
        photoRepository.save(newPhoto);

        newPerson.setSecuredDetails(newSecuredPersonInfo);
        newPerson.setPhoto(newPhoto);

        personRepository.save(newPerson);

        return newPerson.getPersonid();
    }

    public void deletePersonById(Long id) throws RecordNotFoundException {
        Optional<Person> person = personRepository.findById(id);

        if (person.isPresent()) {
            personRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }


    public void updatePerson(Long id, Person personUpdate) {
        Optional<Person> personOptional = personRepository.findById(id);
        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            if (personUpdate.getFirstName() != null) {
                person.setFirstName(personUpdate.getFirstName());
            }
            if (personUpdate.getLastName() != null) {
                person.setLastName(personUpdate.getLastName());
            }
            if (personUpdate.getDescription() != null) {
                person.setDescription(personUpdate.getDescription());
            }
            if (personUpdate.getBorrowedList() != null) {
                person.setBorrowedList(personUpdate.getBorrowedList());
            }
            if (personUpdate.getPersonPrivilegeList() != null) {
                person.setPersonPrivilegeList(personUpdate.getPersonPrivilegeList());
            }
            if (personUpdate.getSecuredDetails() != null) {
                if (personUpdate.getSecuredDetails().getUserrole() != null) {
                    person.getSecuredDetails().setUserrole(personUpdate.getSecuredDetails().getUserrole());
                }
                if (personUpdate.getSecuredDetails().getLogin() != null) {
                    person.getSecuredDetails().setLogin(personUpdate.getSecuredDetails().getLogin());
                }
                if (personUpdate.getSecuredDetails().getPassword() != null) {
                    String password = personUpdate.getSecuredDetails().getPassword();
                    password = passwordEncoder.encode(password);
                    person.getSecuredDetails().setPassword(password);
                }
                securedPersonInfoRepository.save(person.getSecuredDetails());
            }
            personRepository.save(person);
        }
    }

    public List<PrivilegeLevelCodebook> getAllPrivilegeCodebookLevels() {
        return privilegeLevelCodebookRepository.findAll();
    }

    public List<PrivilegeLevelCodebook> getAllAvailablePrivilegeCodebookLevels(Long personid) {
        try {
            Person person = getPersonById(personid);
            List<PrivilegeLevelCodebook> codebookList = privilegeLevelCodebookRepository.findAll();
            List<PrivilegeLevelCodebook> result = new ArrayList<>();
            for (PrivilegeLevelCodebook c : codebookList) {
                result.add(c);
                for (int i = 0; i < person.getPersonPrivilegeList().size(); i++) {
                    if (c.getPrivilegelevelcodebookid() == person.getPersonPrivilegeList().get(i).getPrivilegeLevel().getPrivilegelevelcodebookid()) {
                        result.remove(c);
                    }
                }
            }
            return result;
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<PrivilegeLevelCodebook>();
    }

    public void deletePrivilege(Long personid, Long privid) {
        Optional<PersonPrivilege> p = personPrivilegeRepository.findById(privid);
        if (p.isPresent()) {
            personPrivilegeRepository.delete(p.get());
        }
    }

    public void addPrivilege(Long personid, Long privCodebookId) {
        PersonPrivilege newPrivilege = new PersonPrivilege();
        try {
            newPrivilege.setPerson(getPersonById(personid));
            newPrivilege.setPrivilegeLevel(privilegeLevelCodebookRepository.findById(privCodebookId).get());
            personPrivilegeRepository.save(newPrivilege);
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
        }
    }
}