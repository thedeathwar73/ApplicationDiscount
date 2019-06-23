/**
 * @author sjeandet
 * Pour la base de donnnées
 */
package modele;
import javax.sql.DataSource;
import org.apache.derby.jdbc.*;

public class CreationDataSource {
	
	public static DataSource getDataSource() {
		DataSource resultat;
		
		ClientDataSource cds = new ClientDataSource();
		cds.setDatabaseName("sample");
		cds.setUser("app");
		cds.setPassword("app");
		cds.setServerName("localhost");
		cds.setPortNumber(1527);
		resultat = cds;

		return resultat;
	}
}
