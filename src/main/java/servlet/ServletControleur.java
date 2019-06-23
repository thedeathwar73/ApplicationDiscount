/**
 * contrôleur de la servlet
 * @author sjeandet
 */

package servlet;

import modele.DAO;
import modele.CreationDataSource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;


@WebServlet(name = "ApplicationDiscount", urlPatterns = {"/ApplicationDiscount"})
public class ServletControleur extends HttpServlet {

	protected void processRequest(HttpServletRequest requete, HttpServletResponse reponse)
		throws ServletException, IOException {
		String action = requete.getParameter("action");
		action = (action == null) ? "" : action; 
		String code = requete.getParameter("code");
		String taux = requete.getParameter("taux");
		try {
			DAO dao = new DAO(CreationDataSource.getDataSource());
			requete.setAttribute("codes", dao.tousLesCodesDiscount());			
			
                        if("AJOUTER".equals(action)){
                            dao.ajouterCodeDiscount(code, Float.valueOf(taux));
                            requete.setAttribute("message", "Code " + code + " Ajouté");
                            requete.setAttribute("codes", dao.tousLesCodesDiscount());
                        }
                        else if ("SUPPRIMER".equals(action)){
                            try {
                                dao.supprimerCodeDiscount(code);
                                requete.setAttribute("message", "Code " + code + " Supprimé");
                                requete.setAttribute("codes", dao.tousLesCodesDiscount());								
                            } catch (SQLIntegrityConstraintViolationException e) {
                                requete.setAttribute("message", "Impossible de supprimer " + code + ", ce code est utilisé.");
                            }
                        }

		} catch (Exception ex) {
			Logger.getLogger("ApplicationDiscount").log(Level.SEVERE, "Action en erreur", ex);
			requete.setAttribute("message", ex.getMessage());
		} 
		requete.getRequestDispatcher("index.jsp").forward(requete, reponse);
	}


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		processRequest(request, response);
	}     
}
