package tn.esprit.revisionsyrine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.revisionsyrine.entities.Participant;
import tn.esprit.revisionsyrine.entities.Tache;

import java.util.List;
import java.util.Set;

public interface ParticipantRepo extends JpaRepository<Participant,Integer> {

    @Query("SELECT DISTINCT p from Participant p join p.evenements evnts join evnts.logistiques logis where logis.reserve=?1 AND p.tache=?2")
    Set<Participant> participReservLogis (Boolean state, Tache tache);

    Set<Participant> findByTacheAndEvenementsLogistiquesReserve (Tache tache,Boolean reserve);
    @Query("SELECT p FROM Participant p WHERE p.tache = tn.esprit.revisionsyrine.entities.Tache.ORGANISATEUR")
    Set<Participant> getOrganizateur();
}
