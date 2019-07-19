package fr.thomasschaller.springtodoserveur.domaine;

import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
public class ListeTache {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @NonNull
    String titre;

    @OneToMany
    List<Tache> aRealiser;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public List<Tache> getaRealiser() {
        return aRealiser;
    }

    public void setaRealiser(List<Tache> aRealiser) {
        this.aRealiser = aRealiser;
    }
}
