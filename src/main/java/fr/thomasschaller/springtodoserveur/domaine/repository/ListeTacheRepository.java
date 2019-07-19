package fr.thomasschaller.springtodoserveur.domaine.repository;

import fr.thomasschaller.springtodoserveur.domaine.ListeTache;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ListeTacheRepository extends PagingAndSortingRepository<ListeTache,Long> {
}
