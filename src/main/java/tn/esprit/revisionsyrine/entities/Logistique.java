package tn.esprit.revisionsyrine.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Logistique implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdLog;
    private String description;
    private boolean reserve;
    private float prixUnit;
    private int quantite;
}
