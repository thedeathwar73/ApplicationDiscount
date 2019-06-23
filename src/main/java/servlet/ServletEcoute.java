/**
 * Ecoute de la Servlet
 * @author sjeandet
 */

package servlet;

import modele.DAO;
import modele.CreationDataSource;
import modele.CodeDiscount;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServletEcoute implements ServletContextListener {

    	private void initialisationBaseDeDonnees() {
		OutputStream vide = new OutputStream() {
			@Override
			public void write(int b) {
			}
		};
	}
        
	private boolean existeBaseDeDonnees() {
		boolean resultat = false;

		DAO dao = new DAO(CreationDataSource.getDataSource());
		try {
			List<CodeDiscount> tousLesCodesDiscount = dao.tousLesCodesDiscount();
			Logger.getLogger("ApplicationDiscount").log(Level.INFO, "La base de données existe déjà");
			resultat = true;
		} catch (SQLException ex) {
			Logger.getLogger("ApplicationDiscount").log(Level.INFO, "La base de données n'existe pas");
		}
		return resultat;
	}
        
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		if (!existeBaseDeDonnees()) {
			initialisationBaseDeDonnees();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
}
