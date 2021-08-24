/*
 * Vytvořeno jako studentský zápočtový projekt v rámci studia FIM UHK.
 * 2021 - Petr Veselý
 */

package cz.fim.uhk.lab.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/*
   Class AccesTerminal is a java class representation of the db table "acessterminal".
   Hibernate is used to facilitate the database relationships.
 */

@Entity
@Table(name = "accessterminal")
public class AccessTerminal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accessterminalid", nullable = false)
    private Long accessTerminalId;

    @OneToMany(mappedBy = "terminal")
    private List<AccessTerminalHistory> terminalHistoryList;

    @Column(name = "specimenallowed", nullable = false)
    private Boolean specimenAllowed;

    @Column(name = "event")
    private String event;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "PrivilegelevelcodebookID")
    private PrivilegeLevelCodebook privilegeLevel;


    public AccessTerminal() {
    }

    public Long getAccessTerminalId() {
        return accessTerminalId;
    }

    public void setAccessTerminalId(Long accessTerminalId) {
        this.accessTerminalId = accessTerminalId;
    }

    public List<AccessTerminalHistory> getTerminalHistoryList() {
        return terminalHistoryList;
    }

    public void setTerminalHistoryList(List<AccessTerminalHistory> terminalHistoryList) {
        this.terminalHistoryList = terminalHistoryList;
    }

    public Boolean getSpecimenAllowed() {
        return specimenAllowed;
    }

    public void setSpecimenAllowed(Boolean specimenAllowed) {
        this.specimenAllowed = specimenAllowed;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public PrivilegeLevelCodebook getPrivilegeLevel() {
        return privilegeLevel;
    }

    public void setPrivilegeLevel(PrivilegeLevelCodebook privilegeLevel) {
        this.privilegeLevel = privilegeLevel;
    }

}
