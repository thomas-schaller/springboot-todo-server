package fr.thomasschaller.springtodoserveur.domaine.repository;

import fr.thomasschaller.springtodoserveur.domaine.Tache;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface TacheRepository extends PagingAndSortingRepository<Tache,Long> {
}
