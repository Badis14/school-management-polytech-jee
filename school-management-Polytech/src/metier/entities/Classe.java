package metier.entities;

import java.io.Serializable;

public class Classe implements Serializable {
    private Long idClasse;
    private String nomClasse;
    private String slugClasse;
    private Long auClasse;

    public Classe() {
        super();
    }

    public Classe(String nomClasse, String slugClasse, Long auClasse) {
        super();
        this.nomClasse = nomClasse;
        this.slugClasse = slugClasse;
        this.auClasse = auClasse;
    }

    public Long getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(Long idClasse) {
        this.idClasse = idClasse;
    }

    public Long getAuClasse() {
        return auClasse;
    }

    public void setAuClasse(Long auClasse) {
        this.auClasse = auClasse;
    }

    

    public String getNomClasse() {
        return nomClasse;
    }

    public void setNomClasse(String nomClasse) {
        this.nomClasse = nomClasse;
    }

    public String getSlugClasse() {
        return slugClasse;
    }

    public void setSlugClasse(String slugClasse) {
        this.slugClasse = slugClasse;
    }


    @Override
    public String toString() {
        return "Classe [idClasse=" + idClasse + ", auClasse=" + auClasse + ", nomClasse=" + nomClasse + ", slugClasse=" + slugClasse + "]";
    }

}