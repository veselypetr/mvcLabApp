/*
 * Vytvořeno jako studentský zápočtový projekt v rámci studia FIM UHK.
 * 2021 - Petr Veselý
 */

package cz.fim.uhk.lab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/*
   Class PersonPrivilege is a java class representation of the db table "personprivilege".
   Hibernate is used to facilitate the database relationships.
 */

@Entity
@Table(name = "personprivilege")
public class PersonPrivilege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personprivilegeid", nullable = false)
    private Long personprivilegeid;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "privilegelevelcodebookid")
    private PrivilegeLevelCodebook privilegeLevel;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "personid", nullable = false)
    private Person person;

    public PersonPrivilege() {
    }

    public Long getPersonprivilegeid() {
        return personprivilegeid;
    }

    public void setPersonprivilegeid(Long personprivilegeid) {
        this.personprivilegeid = personprivilegeid;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public PrivilegeLevelCodebook getPrivilegeLevel() {
        return privilegeLevel;
    }

    public void setPrivilegeLevel(PrivilegeLevelCodebook privilegeLevel) {
        this.privilegeLevel = privilegeLevel;
    }

    @Override
    public String toString() {
        return "PersonPrivilege{" +
                "personprivilegeid=" + personprivilegeid +
                ", privilegeLevel=" + privilegeLevel +
                ", person=" + person +
                '}';
    }
}

