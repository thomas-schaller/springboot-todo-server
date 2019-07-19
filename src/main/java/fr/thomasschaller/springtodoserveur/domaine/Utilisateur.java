package fr.thomasschaller.springtodoserveur.domaine;

import lombok.Generated;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @NonNull
    @Column(unique = true)
    String nom;

    @NonNull
    String motdePasse;

    @OneToMany
    List<ListeTache> possede;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMotdePasse() {
        return motdePasse;
    }

    public void setMotdePasse(String motdePasse) {
        this.motdePasse = motdePasse;
    }

    public List<ListeTache> getPossede() {
        return possede;
    }

    public void setPossede(List<ListeTache> possede) {
        this.possede = possede;
    }
}
