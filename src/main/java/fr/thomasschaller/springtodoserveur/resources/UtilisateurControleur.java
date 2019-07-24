package fr.thomasschaller.springtodoserveur.resources;

import fr.thomasschaller.springtodoserveur.domaine.Utilisateur;
import fr.thomasschaller.springtodoserveur.domaine.repository.UtilisateurRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.Base64;

@RestController
@RequestMapping(path="/utilisateurs")
@Slf4j
public class UtilisateurControleur {
    UtilisateurRepository utilisateurs;
    PasswordEncoder passwordEncoder;

    UtilisateurControleur(UtilisateurRepository depotUtilisateurs, PasswordEncoder passwordEncoder)
    {
        utilisateurs=depotUtilisateurs;
        this.passwordEncoder=passwordEncoder;
    }

    @PostMapping(path = "/connexion")
    public boolean seConnecter(@RequestBody Utilisateur utilisateur) throws UnsupportedEncodingException {
        String password = new String (Base64.getDecoder().decode(utilisateur.getMotdePasse()),"utf8");
        log.warn("utilisateur recu :" + utilisateur.getNom()+":"+utilisateur.getMotdePasse() + " : " +password);
        if (utilisateur.getNom() == null){
            return false;
        }
        Utilisateur u = utilisateurs.findByNom(utilisateur.getNom());
        if (u == null)
        {
            return false;
        }
        log.warn("utilisateur trouve :" + u.getNom()+ " : " +u.getMotdePasse());

        if (  passwordEncoder.matches(password,u.getMotdePasse()) ){
            return true;
        }
        else
        {
            return false;
        }
    }
    @GetMapping
    public Utilisateur[] toutRecuperer()
    {
        ArrayList<Utilisateur> listeUtilisateur= new ArrayList<>();
        utilisateurs.findAll().forEach(  u -> {
            u.setPossede(null);
            listeUtilisateur.add(u);} );
        return listeUtilisateur.toArray(new Utilisateur[listeUtilisateur.size()]);

    }
}
