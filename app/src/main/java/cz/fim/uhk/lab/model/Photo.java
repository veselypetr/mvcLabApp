/*
 * Vytvořeno jako studentský zápočtový projekt v rámci studia FIM UHK.
 * 2021 - Petr Veselý
 */

package cz.fim.uhk.lab.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

/*
   Class Photo is a java class representation of the db table "photo".
   Hibernate is used to facilitate the database relationships.
 */

@Entity
@Table(name = "photo")
public class Photo {


    @Column(name = "datemodified")
    Timestamp dateModified;
    @Column(name = "image")
    String image;
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "photoid", nullable = false)
    private Long photoid;
    @OneToOne(optional = true, mappedBy = "photo")
    private Item item;
    @OneToOne(optional = true, mappedBy = "photo")
    private Person person;
    @Column(name = "imagedatatype")
    private String dataType;


    public Photo() {
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getDateModified() {
        return dateModified.toString();
    }

    public void setDateModified(Timestamp dateCreated) {
        this.dateModified = dateCreated;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String extension) {
        this.dataType = extension;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
