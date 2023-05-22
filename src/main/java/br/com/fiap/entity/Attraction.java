package br.com.fiap.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "TAB_ATTRACTION")
@SequenceGenerator(name="attraction", sequenceName = "SQ_TB_ATTRACTION", allocationSize = 1)
public class Attraction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attraction")
    @Column(name="id_address")
    private Long id;
    private String name;
    private String description;
    private String location;
    private String category;

    @ManyToMany(mappedBy = "attractions")
    private List<Trip> trips;

    @OneToMany(mappedBy = "attraction")
    private List<Booking> bookings;

    public Long getId() {
        return id;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
