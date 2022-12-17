package dao;

import java.util.List;

import metier.entities.Classe;

public interface IClasseDao {
    public Classe save(Classe p);

    public List<Classe> ClassesParMC(String mc);

    public Classe getClasse(Long id);

    public Classe updateClasse(Classe p);

    public void deleteClasse(Long id);
}
