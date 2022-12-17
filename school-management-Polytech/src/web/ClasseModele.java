package web;

import java.util.ArrayList;
import java.util.List;

import metier.entities.Classe;

public class ClasseModele {
    private String motCle;
    List<Classe> Classes = new ArrayList<>();

    public String getMotCle() {
        return motCle;
    }

    public void setMotCle(String motCle) {
        this.motCle = motCle;
    }

    public List<Classe> getClasses() {
        return Classes;
    }

    public void setClasses(List<Classe> Classes) {
        this.Classes = Classes;
    }
}
