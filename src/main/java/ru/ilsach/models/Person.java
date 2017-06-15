package ru.ilsach.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Created by student on 3/16/17.
 */
@Entity
@Data
@Table(name = "people")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    @Size(min=2, max=30)
    @Column(name = "firstname")
    public String firstName;

    @NotNull
    @Size(min=2, max=30)

    @Column(name = "login")
    public String login;
    @NotNull
    @Size(min=2, max=30)
    @Column(name = "password")
    public String password;

    @NotNull
    @Size(min=2, max=30)
    @Column(name = "lastname")
    public String lastName;

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPassword() {return password;}

    public String getLogin() {return login;}
}
