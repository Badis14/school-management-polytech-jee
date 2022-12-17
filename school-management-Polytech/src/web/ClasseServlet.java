package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import dao.IClasseDao;
import dao.ClasseDaoImpl;
import metier.entities.Classe;

@WebServlet(name = "claServ", urlPatterns = { "/Classes", "/chercherClasses", "/saisieClasse", "/saveClasse",
        "/supprimerClasse", "/editerClasse", "/updateClasse" })
public class ClasseServlet extends HttpServlet {

    IClasseDao metier;

    @Override
    public void init() throws ServletException {
        metier = new ClasseDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.equals("/Classes")) {
            request.getRequestDispatcher("classes.jsp").forward(request, response);
        }

        else if (path.equals("/chercherClasses")) {
            String motCle = request.getParameter("motCle");
            ClasseModele model = new ClasseModele();
            model.setMotCle(motCle);
            List<Classe> mats = metier.ClassesParMC(motCle);
            model.setClasses(mats);
            request.setAttribute("model", model);
            request.getRequestDispatcher("classes.jsp").forward(request, response);

        } else if (path.equals("/saisieClasse")) {
            request.getRequestDispatcher("saisieClasse.jsp").forward(request, response);
        }

        else if (path.equals("/saveClasse") && request.getMethod().equals("POST")) {
            String nom = request.getParameter("nom");
            String slug_classe = request.getParameter("slug_classe");
            Long au_classe = Long.parseLong(request.getParameter("au_classe"));
            Classe p = metier.save(new Classe(nom, slug_classe, au_classe));
            request.setAttribute("classe", p);
            request.getRequestDispatcher("classes.jsp").forward(request, response);

        } else if (path.equals("/supprimerClasse")) {
            Long id = Long.parseLong(request.getParameter("id"));
            metier.deleteClasse(id);
            response.sendRedirect("chercherClasses?motCle=");

            // request.getRequestDispatcher("confirmationProduit.jsp").forward(request,response);
        }

        else if (path.equals("/editerClasse")) {
            Long id = Long.parseLong(request.getParameter("id"));
            Classe p = metier.getClasse(id);
            request.setAttribute("Classe", p);
            request.getRequestDispatcher("editerClasse.jsp").forward(request, response);
        }

        else if (path.equals("/updateClasse")) {
            Long id = Long.parseLong(request.getParameter("id"));
            String nom = request.getParameter("nomClasse");
            String slug_classe = request.getParameter("slug_classe");
            Long au_classe = Long.parseLong(request.getParameter("au_classe"));
            Classe p = new Classe();
            p.setIdClasse(id);
            p.setNomClasse(nom);
            p.setSlugClasse(slug_classe);
            p.setAuClasse(au_classe);
            metier.updateClasse(p);
            request.setAttribute("Classe", p);
            request.getRequestDispatcher("classes.jsp").forward(request, response);
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
