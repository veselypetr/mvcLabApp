/*
 * Vytvořeno jako studentský zápočtový projekt v rámci studia FIM UHK.
 * 2021 - Petr Veselý
 */

package cz.fim.uhk.lab.model;

import javax.persistence.*;

/*
   Class PrivilegeLevelCodebook is a java class representation of the db table "privilegelevelcodebook".
   Hibernate is used to facilitate the database relationships.
 */

@Entity
@Table(name = "privilegelevelcodebook")
public class PrivilegeLevelCodebook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "privilegelevelcodebookid", nullable = false)
    private Long privilegelevelcodebookid;

    @Column(name = "privilegeleveltitle")
    private String privilegeLevelTitle;

    @Column(name = "privilegeleveldescription")
    private String privilegeLevelDescription;

    public PrivilegeLevelCodebook() {

    }

    public Long getPrivilegelevelcodebookid() {
        return privilegelevelcodebookid;
    }

    public void setPrivilegelevelcodebookid(Long privilegelevelcodebookid) {
        this.privilegelevelcodebookid = privilegelevelcodebookid;
    }

    public String getPrivilegeLevelTitle() {
        return privilegeLevelTitle;
    }

    public void setPrivilegeLevelTitle(String privilegeLevelTitle) {
        this.privilegeLevelTitle = privilegeLevelTitle;
    }

    public String getPrivilegeLevelDescription() {
        return privilegeLevelDescription;
    }

    public void setPrivilegeLevelDescription(String privilegeLevelDescription) {
        this.privilegeLevelDescription = privilegeLevelDescription;
    }

    @Override
    public String toString() {
        return "PrivilegeLevelCodebook{" +
                "privilegelevelcodebookid=" + privilegelevelcodebookid +
                ", privilegeLevelTitle='" + privilegeLevelTitle + '\'' +
                ", privilegeLevelDescription='" + privilegeLevelDescription + '\'' +
                '}';
    }
}
