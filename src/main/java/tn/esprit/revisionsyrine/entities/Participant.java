package tn.esprit.revisionsyrine.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Participant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPart;
    private String nom;
    private String prenom;
    @Enumerated(EnumType.STRING)
    private Tache tache;
    @JsonIgnore
    @ManyToMany(mappedBy = "participants",cascade = CascadeType.ALL)
    Set<Evenement> evenements = new HashSet<>();
}
