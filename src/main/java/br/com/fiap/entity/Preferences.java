package br.com.fiap.entity;

import jakarta.persistence.*;

@Entity
@Table(name="TAB_PREFERENCES")
@SequenceGenerator(name="preferences", sequenceName = "SQ_TB_PREFERENCES", allocationSize = 1)
public class Preferences {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "preferences")
    @Column(name="id_preferences")
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "tb_user_id")
    private User userId;

}
