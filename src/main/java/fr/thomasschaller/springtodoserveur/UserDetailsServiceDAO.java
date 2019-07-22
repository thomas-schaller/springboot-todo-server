package fr.thomasschaller.springtodoserveur;

import fr.thomasschaller.springtodoserveur.domaine.Utilisateur;
import fr.thomasschaller.springtodoserveur.domaine.repository.UtilisateurRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceDAO implements UserDetailsService {

    private UtilisateurRepository depotUtilisateur;

    public UserDetailsServiceDAO(UtilisateurRepository depotUtilisateur) {
        this.depotUtilisateur = depotUtilisateur;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur Utilisateur = depotUtilisateur.findByNom(username);
        if (Utilisateur == null)
        {
           throw new UsernameNotFoundException(username +" not found in database !");
        }
        else
        return new AccountPrincipal(Utilisateur);
    }
}
