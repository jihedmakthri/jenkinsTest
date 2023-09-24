package tn.esprit.revisionsyrine.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.revisionsyrine.entities.Evenement;
import tn.esprit.revisionsyrine.entities.Logistique;
import tn.esprit.revisionsyrine.entities.Participant;
import tn.esprit.revisionsyrine.services.AllServices;

import java.util.Date;
import java.util.Set;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class AllControllers {

    AllServices allServices;

    @PostMapping("/participant/add")
    public Participant ajouterParticipant (@RequestBody Participant p){
        return allServices.ajouterParticipant(p);
    }

    @PostMapping("/hello")
    public String helloTest(){return "hello world !!";}
    //skjdfh
    @PostMapping("/event/addandaffect")
    public Evenement ajoutAffectEvenParticip (@RequestBody Evenement e){
        return allServices.ajoutAffectEvenParticip(e);
    }
    @PostMapping("/log/ajoutAffectLogEvnm")
    public Logistique ajoutAffectLogEvnm (@RequestBody Logistique l,@RequestParam(name ="string") String descriptionEvnmt){
        return  allServices.ajoutAffectLogEvnm(l,descriptionEvnmt);
    }
    @GetMapping("/get/logistiquesbetweendates/{dateDeb}/{dateFin}")
    public Set<Logistique> getLogistiquesDates (@PathVariable Date dateDeb,@PathVariable Date dateFin){
        return allServices.getLogistiquesDates(dateDeb,dateFin);
    }
    @GetMapping("/getOrganisateurEvenementLogistiqueNonReserve")
    public Set<Participant> getParReservLogis (){
        return allServices.getParReservLogis();
    }
}
