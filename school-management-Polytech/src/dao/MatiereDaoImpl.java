package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metier.SingletonConnection;
import metier.entities.Matiere; 

public class MatiereDaoImpl implements IMatiereDao {

    @Override
    public Matiere save(Matiere p) {
        Connection conn = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Matiere(NOM_Matiere,nb_heures_matiere) VALUES(?,?)");
            ps.setString(1, p.getNomMatiere());
            ps.setDouble(2, p.getNbHeuresMat());
            ps.executeUpdate();

            PreparedStatement ps2 = conn.prepareStatement("SELECT MAX(IDMatiere) as MAX_ID FROM Matiere");
            ResultSet rs = ps2.executeQuery();
            if (rs.next()) {
                p.setIdMatiere(rs.getLong("MAX_ID"));
            }
            ps.close();
            ps2.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return p;
    }

    @Override
    public List<Matiere> matieresParMC(String mc) {
        List<Matiere> prods = new ArrayList<Matiere>();
        Connection conn = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Matiere where NOM_Matiere LIKE ?");
            ps.setString(1, "%" + mc + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Matiere p = new Matiere();
                p.setIdMatiere(rs.getLong("IDMatiere"));
                p.setNomMatiere(rs.getString("NOM_Matiere"));
                p.setNbHeuresMat(rs.getDouble("nb_heures_matiere"));
                prods.add(p);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return prods;
    }

    @Override
    public Matiere getMatiere(Long id) {

        Connection conn = SingletonConnection.getConnection();
        Matiere p = new Matiere();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Matiere where IDMatiere = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                p.setIdMatiere(rs.getLong("IDMatiere"));
                p.setNomMatiere(rs.getString("NOM_Matiere"));
                p.setNbHeuresMat(rs.getDouble("nb_heures_matiere"));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return p;
    }

    @Override
    public Matiere updateMatiere(Matiere p) {
        Connection conn = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE Matiere SET NOM_Matiere=?,nb_heures_matiere=? WHERE IDMatiere=?");
            ps.setString(1, p.getNomMatiere());
            ps.setDouble(2, p.getNbHeuresMat());
            ps.setLong(3, p.getIdMatiere());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return p;
    }

    @Override
    public void deleteMatiere(Long id) {
        Connection conn = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Matiere WHERE IDMatiere = ?");
            ps.setLong(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
}
