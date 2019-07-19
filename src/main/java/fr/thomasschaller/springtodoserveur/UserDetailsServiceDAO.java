package fr.thomasschaller.springtodoserveur;

import fr.thomasschaller.springtodoserveur.domaine.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceDAO implements UserDetailsService {

    @Autowired
    fr.thomasschaller.springtodoserveur.domaine.repository.UtilisateurRepository UtilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur Utilisateur = UtilisateurRepository.findByNom(username);
        if (Utilisateur == null)
        {
           throw new UsernameNotFoundException(username +" not found in database !");
        }
        else
        return new AccountPrincipal(Utilisateur);
    }
}
