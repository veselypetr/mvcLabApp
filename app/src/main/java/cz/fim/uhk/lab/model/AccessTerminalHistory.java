/*
 * Vytvořeno jako studentský zápočtový projekt v rámci studia FIM UHK.
 * 2021 - Petr Veselý
 */

package cz.fim.uhk.lab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;

/*
   Class AccessTerminalHistory is a java class representation of the db table "terminalaccesshistory".
   Hibernate is used to facilitate the database relationships.
 */

@Entity
@Table(name = "terminalaccesshistory")
public class AccessTerminalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "terminalaccesshistoryid", nullable = false)
    private Long terminalAccessHistoryId;

    @Column(name = "event")
    private String event;

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "personid")
    private Person person;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "accessterminalid")
    private AccessTerminal terminal;

    @Column(name = "approved", nullable = true)
    private boolean approved;

    public AccessTerminalHistory() {
    }

    public Long getTerminalAccessHistoryId() {
        return terminalAccessHistoryId;
    }

    public void setTerminalAccessHistoryId(Long terminalAccessHistoryId) {
        this.terminalAccessHistoryId = terminalAccessHistoryId;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public AccessTerminal getTerminal() {
        return terminal;
    }

    public void setTerminal(AccessTerminal terminal) {
        this.terminal = terminal;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

}
