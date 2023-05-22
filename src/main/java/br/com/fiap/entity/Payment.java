package br.com.fiap.entity;

import jakarta.persistence.*;

@Entity
@Table(name="TAB_PAYMENT")
@SequenceGenerator(name="payment", sequenceName = "SQ_TB_PAYMENT", allocationSize = 1)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment")
    @Column(name = "id_payment")
    private Long id;

    @OneToOne
    private Booking booking;

    private String status; //"PENDING", "COMPLETED", "FAILED"
    private String transactionId;
    private String paymentMethod; //"CREDIT_CARD", "PAYPAL"
    private Double amount;
    private String currency;

    public Long getId() {
        return id;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}

