/**
 * @author sjeandet
 * Pour la base de donnnées
 */
package modele;
import javax.sql.DataSource;
import org.apache.derby.jdbc.*;


public class CreationDataSource {

	public enum DerbyMode {
		serveur, embarque
	};
	
	private static final DerbyMode MODE = DerbyMode.serveur;

	public static DataSource getDataSource() {
		DataSource resultat;

		if(MODE == DerbyMode.serveur){
                    ClientDataSource cds = new ClientDataSource();
                    cds.setDatabaseName("sample");
                    cds.setUser("app");
                    cds.setPassword("app");
                    cds.setServerName("localhost");
                    cds.setPortNumber(1527);
                    resultat = cds;
                }
                else{
                    EmbeddedDataSource eds = new EmbeddedDataSource();
                    eds.setCreateDatabase("create");
                    eds.setDatabaseName("embedded_sample");
                    resultat = eds;
		}

		return resultat;
	}

}
