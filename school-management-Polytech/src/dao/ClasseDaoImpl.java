package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metier.SingletonConnection;
import metier.entities.Classe;

public class ClasseDaoImpl implements IClasseDao {

    @Override
    public Classe save(Classe p) {
        Connection conn = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = conn
                    .prepareStatement("INSERT INTO Classe(NOM_Classe, departement_classe, a_u) VALUES(?,?,?)");
            ps.setString(1, p.getNomClasse());
            ps.setString(1, p.getSlugClasse());
            ps.setLong(1, p.getAuClasse());
            ps.executeUpdate();

            PreparedStatement ps2 = conn.prepareStatement("SELECT MAX(IDClasse) as MAX_ID FROM Classe");
            ResultSet rs = ps2.executeQuery();
            if (rs.next()) {
                p.setIdClasse(rs.getLong("MAX_ID"));
            }
            ps.close();
            ps2.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return p;
    }

    @Override
    public List<Classe> ClassesParMC(String mc) {
        List<Classe> prods = new ArrayList<Classe>();
        Connection conn = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Classe where NOM_Classe LIKE ?");
            ps.setString(1, "%" + mc + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Classe p = new Classe();
                p.setIdClasse(rs.getLong("IDClasse"));
                p.setNomClasse(rs.getString("NOM_Classe"));
                p.setSlugClasse(rs.getString("departement_classe"));
                p.setAuClasse(rs.getLong("a_u"));
                prods.add(p);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return prods;
    }

    @Override
    public Classe getClasse(Long id) {

        Connection conn = SingletonConnection.getConnection();
        Classe p = new Classe();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Classe where IDClasse = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                p.setIdClasse(rs.getLong("IDClasse"));
                p.setNomClasse(rs.getString("NOM_Classe"));
                p.setSlugClasse(rs.getString("departement_classe"));
                p.setAuClasse(rs.getLong("a_u"));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return p;
    }

    @Override
    public Classe updateClasse(Classe p) {
        Connection conn = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = conn
                    .prepareStatement("UPDATE Classe SET NOM_Classe=?,departement_classe=?, a_u=?, WHERE IDClasse=?");
            ps.setString(1, p.getNomClasse());
            ps.setString(1, p.getSlugClasse());
            ps.setLong(3, p.getAuClasse());
            ps.setLong(3, p.getIdClasse());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return p;
    }

    @Override
    public void deleteClasse(Long id) {
        Connection conn = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Classe WHERE IDClasse = ?");
            ps.setLong(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
}
