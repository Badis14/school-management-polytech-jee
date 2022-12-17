package metier.entities;

import java.io.Serializable;

public class Matiere implements Serializable {
    private Long idMatiere;
    private String nomMatiere;
    private double NbHeuresMat;

    public Matiere() {
        super();
    }

    public Matiere(String nomMatiere, double NbHeuresMat) {
        super();
        this.nomMatiere = nomMatiere;
        this.NbHeuresMat = NbHeuresMat;
    }

    public Long getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(Long idMatiere) {
        this.idMatiere = idMatiere;
    }

    public String getNomMatiere() {
        return nomMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }

    public double getNbHeuresMat() {
        return NbHeuresMat;
    }

    public void setNbHeuresMat(double NbHeuresMat) {
        this.NbHeuresMat = NbHeuresMat;
    }

    @Override
    public String toString() {
        return "Matiere [idMatiere=" + idMatiere + ", nomMatiere=" + nomMatiere + ", NbHeuresMat=" + NbHeuresMat + "]";
    }

}