/*
 * Vytvořeno jako studentský zápočtový projekt v rámci studia FIM UHK.
 * 2021 - Petr Veselý
 */

package cz.fim.uhk.lab.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/*
   Class Person is a java class representation of the db table "person".
   Hibernate is used to facilitate the database relationships.
 */

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "personid", nullable = false)
    private Long personid;

    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToOne
    @JoinColumn(name = "photoid")
    private Photo photo;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private SecuredPersonInfo securedDetails;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<PersonPrivilege> personPrivilegeList;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Borrowed> borrowedList;

    public Person() {
    }

    public Long getPersonid() {
        return personid;
    }

    public void setPersonid(Long personid) {
        this.personid = personid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public SecuredPersonInfo getSecuredDetails() {
        return securedDetails;
    }

    public void setSecuredDetails(SecuredPersonInfo securedDetails) {
        this.securedDetails = securedDetails;
    }

    public List<PersonPrivilege> getPersonPrivilegeList() {
        return personPrivilegeList;
    }

    public void setPersonPrivilegeList(List<PersonPrivilege> personPrivilegeList) {
        this.personPrivilegeList = personPrivilegeList;
    }

    public List<Borrowed> getBorrowedList() {
        return borrowedList;
    }

    public void setBorrowedList(List<Borrowed> borrowedList) {
        this.borrowedList = borrowedList;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personid=" + personid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", description='" + description + '\'' +
                ", securedDetails=" + securedDetails +
                ", personPrivilegeList=" + personPrivilegeList +
                ", borrowedList=" + borrowedList +
                '}';
    }

}