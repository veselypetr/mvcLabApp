/*
 * Vytvořeno jako studentský zápočtový projekt v rámci studia FIM UHK.
 * 2021 - Petr Veselý
 */

package cz.fim.uhk.lab.model;

import javax.persistence.*;

/*
   Class ItemCodebook is a java class representation of the db table "itemcodebook".
   Hibernate is used to facilitate the database relationships.
 */

@Entity
@Table(name = "itemcodebook")
public class ItemCodebook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemcodebookid", nullable = false)
    private Long itemcodebookid;


    @Column(name = "categoryname")
    private String categoryName;

    @Column(name = "info")
    private String info;

    public ItemCodebook() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Long getItemcodebookid() {
        return itemcodebookid;
    }

    public void setItemcodebookid(Long itemcodebookid) {
        this.itemcodebookid = itemcodebookid;
    }

    @Override
    public String toString() {
        return "ItemCodebook{" +
                ", categoryName='" + categoryName + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
