package org.example;

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
@Table(name="User")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name="User_Type",
        discriminatorType=DiscriminatorType.STRING
)
@DiscriminatorValue(value="User")

public abstract class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idUser")
    private int idUser;
    @Column(name="email")
    private String email;

    public User () {

    }

    public User (int idUser, String email) {
        this.idUser = idUser;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
