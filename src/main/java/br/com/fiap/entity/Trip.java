package br.com.fiap.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TAB_TRIP")
@SequenceGenerator(name="trip", sequenceName = "SQ_TB_TRIP", allocationSize = 1)
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trip")
    @Column(name = "id_trip")
    private Long id;
    private String destination;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @ManyToOne
    private User user;

    @ManyToMany
    @JoinTable(
            name = "trip_attraction",
            joinColumns = @JoinColumn(name = "trip_id"),
            inverseJoinColumns = @JoinColumn(name = "attraction_id"))
    private List<Attraction> attractions;

    public Long getId() {
        return id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Attraction> getAttractions() {
        return attractions;
    }

    public void setAttractions(List<Attraction> attractions) {
        this.attractions = attractions;
    }
}
