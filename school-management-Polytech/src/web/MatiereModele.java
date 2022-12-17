package web;

import java.util.ArrayList;
import java.util.List;

import metier.entities.Matiere;

public class MatiereModele {
    private String motCle;
    List<Matiere> Matieres = new ArrayList<>();

    public String getMotCle() {
        return motCle;
    }

    public void setMotCle(String motCle) {
        this.motCle = motCle;
    }

    public List<Matiere> getMatieres() {
        return Matieres;
    }

    public void setMatieres(List<Matiere> Matieres) {
        this.Matieres = Matieres;
    }
}
