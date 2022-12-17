package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import dao.IMatiereDao;
import dao.MatiereDaoImpl;
import metier.entities.Matiere;

@WebServlet(name = "matServ", urlPatterns = { "/Matieres", "/chercherMatieres", "/saisieMatiere", "/saveMatiere", "/supprimerMatiere", "/editerMatiere", "/updateMatiere" })
public class MatiereServlet extends HttpServlet {

    IMatiereDao metier;

    @Override
    public void init() throws ServletException {
        metier = new MatiereDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.equals("/Matieres")) {
            request.getRequestDispatcher("matieres.jsp").forward(request, response);
        }

         else if (path.equals("/chercherMatieres")) {
            String motCle = request.getParameter("motCle");
            MatiereModele model = new MatiereModele();
            model.setMotCle(motCle);
            List<Matiere> mats = metier.matieresParMC(motCle);
            model.setMatieres(mats);
            request.setAttribute("model", model);
            request.getRequestDispatcher("matieres.jsp").forward(request, response);
        }
         else if (path.equals("/saisieMatiere")) {
            request.getRequestDispatcher("saisieMatiere.jsp").forward(request, response);
        }

        else if (path.equals("/saveMatiere") && request.getMethod().equals("POST")) {
            String nom = request.getParameter("nom");
            double NbHeuresMat = Double.parseDouble(request.getParameter("NbHeuresMat"));
            Matiere p = metier.save(new Matiere(nom, NbHeuresMat));
            request.setAttribute("matiere", p);
            request.getRequestDispatcher("matieres.jsp").forward(request, response);
        }
        else if (path.equals("/supprimerMatiere")) {
            Long id = Long.parseLong(request.getParameter("id"));
            metier.deleteMatiere(id);
            response.sendRedirect("chercherMatieres?motCle=");

            // request.getRequestDispatcher("confirmationProduit.jsp").forward(request,response);
        }

        else if (path.equals("/editerMatiere")) {
            Long id = Long.parseLong(request.getParameter("id"));
            Matiere p = metier.getMatiere(id);
            request.setAttribute("matiere", p);
            request.getRequestDispatcher("editerMatiere.jsp").forward(request, response);
        }

        else if (path.equals("/updateMatiere")) {
            Long id = Long.parseLong(request.getParameter("id"));
            String nom = request.getParameter("nomMatiere");
            double NbHeuresMat = Double.parseDouble(request.getParameter("nbHeuresMat"));
            Matiere p = new Matiere();
            p.setIdMatiere(id);
            p.setNomMatiere(nom);
            p.setNbHeuresMat(NbHeuresMat);
            metier.updateMatiere(p);
            request.setAttribute("Matiere", p);
            request.getRequestDispatcher("matieres.jsp").forward(request, response);
        }

        else {
            response.sendError(Response.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
