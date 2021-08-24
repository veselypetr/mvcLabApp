/*
 * Vytvořeno jako studentský zápočtový projekt v rámci studia FIM UHK.
 * 2021 - Petr Veselý
 */

package cz.fim.uhk.lab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;

/*
   Class Borrowed is a java class representation of the db table "borrowed".
   Hibernate is used to facilitate the database relationships.
 */

@Entity
@Table(name = "borrowed")
public class Borrowed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrowedid", nullable = false)
    private Long borrowedId;

    @Column(name = "borrowedtimestamp")
    private Timestamp borrowedTimestamp;
    @Column(name = "isborrowed")
    private Boolean isBorrowed;
    @Column(name = "returnedtimestamp")
    private Timestamp returnedTimestamp;
    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "personid")
    private Person person;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "itemid")
    private Item item;


    public Borrowed() {
    }

    public Long getBorrowedId() {
        return borrowedId;
    }

    public void setBorrowedId(Long borrowedId) {
        this.borrowedId = borrowedId;
    }

    public Timestamp getBorrowedTimestamp() {
        return borrowedTimestamp;
    }

    public void setBorrowedTimestamp(Timestamp borrowedTimestamp) {
        this.borrowedTimestamp = borrowedTimestamp;
    }

    public Boolean getBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(Boolean borrowed) {
        isBorrowed = borrowed;
    }

    public Timestamp getReturnedTimestamp() {
        return returnedTimestamp;
    }

    public void setReturnedTimestamp(Timestamp returnedTimestamp) {
        this.returnedTimestamp = returnedTimestamp;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Borrowed{" +
                "borrowedId=" + borrowedId +
                ", borrowedTimestamp='" + borrowedTimestamp + '\'' +
                ", isBorrowed=" + isBorrowed +
                ", returnedTimestamp='" + returnedTimestamp + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
