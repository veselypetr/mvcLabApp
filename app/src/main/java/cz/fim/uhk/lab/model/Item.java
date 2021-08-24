/*
 * Vytvořeno jako studentský zápočtový projekt v rámci studia FIM UHK.
 * 2021 - Petr Veselý
 */

package cz.fim.uhk.lab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/*
   Class Item is a java class representation of the db table "item".
   Hibernate is used to facilitate the database relationships.
 */

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemid", nullable = false)
    private Long itemid;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToOne(optional = true, mappedBy = "item")
    private Specimen specimenDetails;

    @OneToOne
    @JoinColumn(name = "photoid")
    private Photo photo;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "itemcodebookid")
    private ItemCodebook itemCodebook;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Borrowed> borrowedList;

    @Column(name = "currentlyavailable")
    private int currentlyAvailable;

    public Item() {
    }

    public Long getItemid() {
        return itemid;
    }

    public void setItemid(Long itemid) {
        this.itemid = itemid;
    }

    public int getCurrentlyAvailable() {
        return currentlyAvailable;
    }

    public void setCurrentlyAvailable(int currentlyAvailable) {
        this.currentlyAvailable = currentlyAvailable;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Specimen getSpecimenDetails() {
        return specimenDetails;
    }

    public void setSpecimenDetails(Specimen specimenDetails) {
        this.specimenDetails = specimenDetails;
    }

    public ItemCodebook getItemCodebook() {
        return itemCodebook;
    }

    public void setItemCodebook(ItemCodebook itemCodebook) {
        this.itemCodebook = itemCodebook;
    }

    public List<Borrowed> getBorrowedList() {
        return borrowedList;
    }

    public void setBorrowedList(List<Borrowed> borrowedList) {
        this.borrowedList = borrowedList;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemid=" + itemid +
                ", quantity=" + quantity +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", specimenDetails=" + specimenDetails +
                ", item=" + itemCodebook +
                ", borrowedList=" + borrowedList +
                '}';
    }
}
