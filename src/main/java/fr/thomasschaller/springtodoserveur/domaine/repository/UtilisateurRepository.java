package fr.thomasschaller.springtodoserveur.domaine.repository;

import fr.thomasschaller.springtodoserveur.domaine.Utilisateur;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UtilisateurRepository extends PagingAndSortingRepository<Utilisateur,Long> {

    Utilisateur findByNom(String nom);
}
