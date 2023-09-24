package tn.esprit.revisionsyrine.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Evenement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    private float cout;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Logistique> logistiques = new HashSet<>();
    @ManyToMany
    private Set<Participant> participants = new HashSet<>();
}
