package org.webitems;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;


@Entity
@Table(name="WebItem")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name="WebItem_Type",
        discriminatorType=DiscriminatorType.STRING
)
@DiscriminatorValue(value="WebItem")
public abstract class WebItem {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idItem")
    private int idItem;
    @Column(name="name")
    private String name;


    public WebItem(String name) {
        this.name = name;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
