package br.com.fiap.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TAB_BOOKING")
@SequenceGenerator(name="booking", sequenceName = "SQ_TB_BOOKING", allocationSize = 1)
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking")
    @Column(name="id_booking")
    private Long id;
    private String dateBooking;
    private String timeBooking;

    @ManyToOne
    private User user;

    @ManyToOne
    private Attraction attraction;

    public Long getId() {
        return id;
    }

    public String getDateBooking() {
        return dateBooking;
    }

    public void setDate(String dateBooking) {
        this.dateBooking = dateBooking;
    }

    public String getTime() {
        return timeBooking;
    }

    public void setTime(String timeBooking) {
        this.timeBooking = timeBooking;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Attraction getAttraction() {
        return attraction;
    }

    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }
}
