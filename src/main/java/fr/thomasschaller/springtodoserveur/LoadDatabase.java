package fr.thomasschaller.springtodoserveur;

import fr.thomasschaller.springtodoserveur.domaine.ListeTache;
import fr.thomasschaller.springtodoserveur.domaine.Tache;
import fr.thomasschaller.springtodoserveur.domaine.Utilisateur;
import fr.thomasschaller.springtodoserveur.domaine.repository.ListeTacheRepository;
import fr.thomasschaller.springtodoserveur.domaine.repository.TacheRepository;
import fr.thomasschaller.springtodoserveur.domaine.repository.UtilisateurRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Slf4j
@Configuration
public class LoadDatabase {


    @Bean
    CommandLineRunner initDatabase(UtilisateurRepository depotUtilisateur, ListeTacheRepository depotListeTache, TacheRepository depotTache, PasswordEncoder passwordEncoder)   {
        return args -> {
            Calendar c= Calendar.getInstance();
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setNom("thomas");
            utilisateur.setMotdePasse(passwordEncoder.encode("password"));
            utilisateur.setPossede(new ArrayList<>());
            depotUtilisateur.save(utilisateur);
            ListeTache listeDeTache = new ListeTache();
            listeDeTache.setTitre("Courses");
            utilisateur.getPossede().add(listeDeTache);
            listeDeTache.setaRealiser( new ArrayList<>());

            Tache task = new Tache();
            task.setTitre("acheter du lait");
            depotTache.save(task);
            listeDeTache.getaRealiser().add(task);

            task = new Tache();
            task.setTitre("acheter des oeufs.");
            depotTache.save(task);
            listeDeTache.getaRealiser().add(task);
            task = new Tache();
            task.setTitre("acheter des céréales.");
            depotTache.save(task);
            listeDeTache.getaRealiser().add(task);

            task = new Tache();
            task.setTitre("acheter des fruits.");
            depotTache.save(task);
            listeDeTache.getaRealiser().add(task);
            depotListeTache.save(listeDeTache);

            listeDeTache = new ListeTache();
            listeDeTache.setTitre("Taches ménagères");
            utilisateur.getPossede().add(listeDeTache);

            listeDeTache.setaRealiser( new ArrayList<>());

            task = new Tache();
            task.setTitre("faire la vaisselle");
            task.setDateLimite(new Date());
            depotTache.save(task);
            listeDeTache.getaRealiser().add(task);

            task = new Tache();
            task.setTitre("passer l'aspirateur");
            c.add(Calendar.DAY_OF_MONTH,2);
            task.setDateLimite(c.getTime());
            depotTache.save(task);
            listeDeTache.getaRealiser().add(task);

            task = new Tache();
            task.setTitre("faire le repas");
            task.setDateLimite(new Date());
            depotTache.save(task);
            listeDeTache.getaRealiser().add(task);

            task = new Tache();
            task.setTitre("ranger la cave");
            c.add(Calendar.DAY_OF_MONTH,9);
            task.setDateLimite(c.getTime());
            depotTache.save(task);
            listeDeTache.getaRealiser().add(task);
            depotListeTache.save(listeDeTache);
            log.info(utilisateur.getNom()+ ": "+utilisateur.getPossede().toString());
            depotUtilisateur.save(utilisateur);

            utilisateur = new Utilisateur();
            utilisateur.setNom("david");
            utilisateur.setMotdePasse(passwordEncoder.encode("thief"));
            depotUtilisateur.save(utilisateur);
            utilisateur.setPossede(new ArrayList<>());

            listeDeTache = new ListeTache();
            utilisateur.getPossede().add(listeDeTache);
            listeDeTache.setTitre("Choses à faire");
            listeDeTache.setaRealiser( new ArrayList<>());
            task = new Tache();
            task.setTitre("peindre le mur.");
            depotTache.save(task);
            listeDeTache.getaRealiser().add(task);
            depotListeTache.save(listeDeTache);

            log.info(utilisateur.getNom()+ ": "+utilisateur.getPossede().toString());
            depotUtilisateur.save(utilisateur);
        };
    }
}
