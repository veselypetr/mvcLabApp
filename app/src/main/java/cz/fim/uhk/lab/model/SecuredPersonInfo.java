/*
 * Vytvořeno jako studentský zápočtový projekt v rámci studia FIM UHK.
 * 2021 - Petr Veselý
 */

package cz.fim.uhk.lab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/*
   Class SecuredPersonInfo is a java class representation of the db table "securedpersoninfo".
   Hibernate is used to facilitate the database relationships.
 */


@Entity
@Table(name = "securedpersoninfo")
public class SecuredPersonInfo {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "securedpersoninfoid", nullable = false)
    private long securedpersoninfoid;

    @Column(name = "personid", nullable = false)
    private long personid;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "userrole", nullable = false)
    private String userrole;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "personid", insertable = false, updatable = false)
    private Person person;

    public SecuredPersonInfo() {
    }

    public SecuredPersonInfo(String login, String password, String userrole, long personid) {
        this.login = login;
        this.password = password;
        this.userrole = userrole;
        this.personid = personid;
    }


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public long getSecuredpersoninfoid() {
        return securedpersoninfoid;
    }

    public void setSecuredpersoninfoid(long securedpersoninfoid) {
        this.securedpersoninfoid = securedpersoninfoid;
    }

    public long getPersonid() {
        return personid;
    }

    public void setPersonid(long personid) {
        this.personid = personid;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserrole() {
        return userrole;
    }

    public void setUserrole(String userrole) {
        this.userrole = userrole;
    }

    @Override
    public String toString() {
        return "SecuredPersonInfo{" +
                "securedpersoninfoid=" + securedpersoninfoid +
                ", personid=" + personid +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", userrole='" + userrole + '\'' +
                '}';
    }
}