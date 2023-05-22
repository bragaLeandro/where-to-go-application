package br.com.fiap.entity;

import jakarta.persistence.*;

@Entity
@Table(name="TAB_ADDRESS")
@SequenceGenerator(name="address", sequenceName = "SQ_TB_ADDRESS", allocationSize = 1)
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address")
    @Column(name="id_address")
    private Long id;
    private String street;

    @Column(name="nm_house")
    private String number;
    private String city;

    @Column(name="ds_state")
    private String state;
    private String country;
    private String zipCode;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
