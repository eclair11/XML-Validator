

package doc.num.projet;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;

import doc.num.projet.modele.Avion;
import doc.num.projet.modele.Moteur;
import doc.num.projet.modele.Message;
import doc.num.projet.modele.AvionRepository;
import doc.num.projet.modele.MoteurRepository;
import doc.num.projet.modele.MessageRepository;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Solofo R.
 * @author Nicolas T.
 */
@RestController
public class AvionRestController {

    @Inject
    public AvionRepository avionRep;

    @Inject
    public MoteurRepository moteurRep;
    
    @Inject
    public MessageRepository messageRep;
    
    @RequestMapping(value="/avions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Avion[] getAllAvion() {
        List<Avion> avions = new ArrayList<Avion>();
        for (Avion avion : avionRep.findAll()) {
            avions.add(avion);
        }
        Avion[] avionArr = new Avion[avions.size()];
        avionArr = avions.toArray(avionArr);
        return avionArr;
    }
    
    @RequestMapping(value="/moteurs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Moteur[] getAllMoteur() {
        List<Moteur> moteurs = new ArrayList<Moteur>();
        for (Moteur moteur : moteurRep.findAll()) {
            moteurs.add(moteur);
        }
        Moteur[] moteurArr = new Moteur[moteurs.size()];
        moteurArr = moteurs.toArray(moteurArr);
        return moteurArr;
    }
    
    @RequestMapping(value="/messages", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Message[] getAllMessage() {
        List<Message> messages = new ArrayList<Message>();
        for (Message message : messageRep.findAll()) {
            messages.add(message);
        }
        Message[] messageArr = new Message[messages.size()];
        messageArr = messages.toArray(messageArr);
        return messageArr;
    }

    @RequestMapping(value = "/avionAdd", method = RequestMethod.POST)
    public ResponseEntity<String> addAVion(@RequestBody Avion avion) {
        /** tout ce qui touche le moteur doit pouvoir être retiré
         *  repercutions en cascade (voir classe modifiée)
         */
        /*
        System.out.println(avion.getMoteur());
        moteurRep.save(avion.getMoteur());
        */
        System.out.println("new moteur saved !");
        System.out.println(avion);
        avionRep.save(avion);
        System.out.println("new avion saved !");
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/avionUpdate/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateAVion(@PathVariable("id") Long id, @RequestBody Avion avion) {
        avionRep.findById(id);
       
        System.out.println(avion);
        avionRep.save(avion);
        System.out.println("new avion updated !");
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/avionDel/{id}")
    public void delAVion(@PathVariable("id") Long id) {
        avionRep.deleteById(id);
        System.out.println("avion deleted !");
    }

}