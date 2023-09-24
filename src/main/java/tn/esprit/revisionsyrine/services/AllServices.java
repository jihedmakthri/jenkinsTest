package tn.esprit.revisionsyrine.services;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import tn.esprit.revisionsyrine.entities.Evenement;
import tn.esprit.revisionsyrine.entities.Logistique;
import tn.esprit.revisionsyrine.entities.Participant;
import tn.esprit.revisionsyrine.entities.Tache;
import tn.esprit.revisionsyrine.repositories.EvenementRepo;
import tn.esprit.revisionsyrine.repositories.LogistiqueRepo;
import tn.esprit.revisionsyrine.repositories.ParticipantRepo;

import javax.servlet.http.Part;
import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class AllServices {

    EvenementRepo evenementRepo;
    LogistiqueRepo logistiqueRepo;
    ParticipantRepo participantRepo;

    public Participant ajouterParticipant (Participant p){
        return participantRepo.save(p);
    }

    public Evenement ajoutAffectEvenParticip (Evenement e){
         return evenementRepo.save(e);
    }

    public Logistique ajoutAffectLogEvnm (Logistique l,String descriptionEvnmt){
        logistiqueRepo.save(l);
        List<Evenement> ev = evenementRepo.findAll();
        for (Evenement e: ev) {
            if (e.getDescription().equals(descriptionEvnmt)) {
                if(!e.getLogistiques().contains(l)){
                    e.getLogistiques().add(l);
                    evenementRepo.save(e);
                }
            }
        }
        return l;
    }

    public Set<Logistique> getLogistiquesDates (Date dateDeb, Date dateFin){
        List<Evenement> ev = evenementRepo.getEvenementReserveBetweenDates(dateDeb,dateFin);
        Set<Logistique> logistiqueList = new HashSet<>();
        for (Evenement e: ev) {
            e.getLogistiques().stream().forEach(logistique -> {
                if(logistique.isReserve()){
                    logistiqueList.add(logistique);
                }
            });
        }
        return logistiqueList;
    }
    public Set<Participant> getParReservLogis (){
        Set<Participant> organisateurs = participantRepo.getOrganizateur();
        Set<Participant> filtred = new HashSet<>();
        for(Participant p: organisateurs){
            p.getEvenements().stream().forEach(evenement -> {
                Set<Logistique> ls = evenement.getLogistiques();
                ls.stream().forEach(logistique -> {
                    if(!logistique.isReserve()){
                        filtred.add(p);
                    }
                });
            });
        }
        return filtred;
   }
    public Set<Participant> getParReservLogis1(){
        return participantRepo.findByTacheAndEvenementsLogistiquesReserve(Tache.ORGANISATEUR,false);
    }
    public Set<Participant> getParReservLogis2(){
        return participantRepo.participReservLogis(false,Tache.ORGANISATEUR);
    }

    @Scheduled(fixedDelay = 60000)
    public void calculCout (){
        List<Evenement> evenements = evenementRepo.findAll();
        float updatedCost = 0;
        for(Evenement syrine : evenements){
            Set<Logistique> lgs = syrine.getLogistiques();
            for(Logistique l:lgs){
                updatedCost += l.getPrixUnit() * l.getQuantite();
            }
            syrine.setCout(updatedCost);
            updatedCost = 0;
            evenementRepo.save(syrine);
            log.info("evenement:"+syrine.getDescription()+"-- cout: "+syrine.getCout());
        }
    }
}
