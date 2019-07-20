package fr.thomasschaller.springtodoserveur.resources;

import fr.thomasschaller.springtodoserveur.domaine.ListeTache;
import fr.thomasschaller.springtodoserveur.domaine.Utilisateur;
import fr.thomasschaller.springtodoserveur.domaine.repository.ListeTacheRepository;
import fr.thomasschaller.springtodoserveur.domaine.repository.TacheRepository;
import fr.thomasschaller.springtodoserveur.domaine.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path="/taches")
public class ListeTachesControleur {

    private UtilisateurRepository depotUtilisateurs;

    private ListeTacheRepository depotListesTaches;

    private TacheRepository depotTaches;

    public ListeTachesControleur(@NonNull UtilisateurRepository depotUtilisateurs, @NonNull ListeTacheRepository depotListesTaches, @NonNull TacheRepository depotTaches) {
        this.depotUtilisateurs=depotUtilisateurs;
        this.depotListesTaches=depotListesTaches;
        this.depotTaches=depotTaches;
    }

    @GetMapping
    public List<ListeTache> recupererToutesListeTache(Principal compte)
    {
        Utilisateur utilisateur = depotUtilisateurs.findByNom(compte.getName());
        return utilisateur.getPossede();
    }

    @GetMapping(path="/{id}")
    public ListeTache recupererListeTache(Principal compte,@PathVariable Long id)
    {
        Utilisateur utilisateur = depotUtilisateurs.findByNom(compte.getName());
        ListeTache listeTache =depotListesTaches.findById(id).orElse(null);
       if (listeTache != null && utilisateur.getPossede().contains(listeTache))
       {
           return listeTache;
       }
       return null;
    }
    @PutMapping
    public ListeTache miseAJourListeTache(Principal compte,@RequestBody ListeTache liste)
    {
        Utilisateur utilisateur = depotUtilisateurs.findByNom(compte.getName());
        if (utilisateur.getPossede().contains(liste))
        {
            depotListesTaches.save(liste);
            return liste;
        }
        else
        {
            throw new SecurityException("L'utilisateur n'est pas le propriétaire de la liste de tache d'identifiant: " + liste.getId());
        }
    }

    @PostMapping
    public ListeTache creationListeTache(Principal compte,@RequestBody ListeTache liste)
    {
        Utilisateur utilisateur = depotUtilisateurs.findByNom(compte.getName());
        depotListesTaches.save(liste);
        utilisateur.getPossede().add(liste);
        depotUtilisateurs.save(utilisateur);
        return liste;
    }

}
