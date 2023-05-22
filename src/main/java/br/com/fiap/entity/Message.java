package br.com.fiap.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Calendar;

@Entity
@Table(name = "TAB_MESSAGES")
@SequenceGenerator(name="message", sequenceName = "SQ_TB_MESSAGE", allocationSize = 1)
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message")
    @Column(name="id_message")
    private Long id;

    @Column(name="ds_question", nullable = false)
    private String question;


    @Column(name="ds_answer", nullable = false)
    private String answer;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="dt_register", nullable = false)
    private Calendar registerDate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_user")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Calendar getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Calendar registerDate) {
        this.registerDate = registerDate;
    }
}
