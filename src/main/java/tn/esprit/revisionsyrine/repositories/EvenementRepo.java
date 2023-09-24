package tn.esprit.revisionsyrine.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.revisionsyrine.entities.Evenement;


import java.util.Date;
import java.util.List;


public interface EvenementRepo extends JpaRepository<Evenement,Integer> {
    @Query("select e from Evenement e where e.dateDebut between :dateDeb and :dateFin")
    List<Evenement> getEvenementReserveBetweenDates(@Param("dateDeb") Date dateDeb,@Param("dateFin") Date dateFin);

    Evenement findByDescription(String desc);
}
