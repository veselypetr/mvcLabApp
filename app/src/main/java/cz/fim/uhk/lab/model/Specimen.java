/*
 * Vytvořeno jako studentský zápočtový projekt v rámci studia FIM UHK.
 * 2021 - Petr Veselý
 */

package cz.fim.uhk.lab.model;

import javax.persistence.*;

/*
   Class Specimen is a java class representation of the db table "specimen".
   Hibernate is used to facilitate the database relationships.
 */

@Entity
@Table(name = "specimen")
public class Specimen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "specimenid", nullable = false)
    private Long specimenId;

    @ManyToOne
    @JoinColumn(name = "privilegelevelcodebookid")
    private PrivilegeLevelCodebook privilegeCategory;

    @Column(name = "additionalinfo")
    private String additionalInfo;

    @OneToOne
    @JoinColumn(name = "itemid")
    private Item item;

    public Specimen() {
    }

    public Long getSpecimenId() {
        return specimenId;
    }

    public void setSpecimenId(Long specimenId) {
        this.specimenId = specimenId;
    }

    public PrivilegeLevelCodebook getPrivilegeCategory() {
        return privilegeCategory;
    }

    public void setPrivilegeCategory(PrivilegeLevelCodebook privilegeCategory) {
        this.privilegeCategory = privilegeCategory;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    @Override
    public String toString() {
        return "Specimen{" +
                "specimenId=" + specimenId +
                ", privilegeCategory=" + privilegeCategory +
                ", additionalInfo='" + additionalInfo + '\'' +
                '}';
    }
}
