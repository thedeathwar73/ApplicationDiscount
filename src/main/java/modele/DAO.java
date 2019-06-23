/**
 * @author sjeandet
 * Classe DAO
 */

package modele;

import java.util.LinkedList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javax.sql.DataSource;
import java.sql.SQLException;




public class DAO {

	private final DataSource _source;

	public DAO(DataSource source) {
		this._source = source;
	}

	/*Pour contenir la table*/
	public List<CodeDiscount> tousLesCodesDiscount() throws SQLException {
		List<CodeDiscount> listCodeDiscount = new LinkedList<>();
		String sql = "SELECT * FROM DISCOUNT_CODE ORDER BY DISCOUNT_CODE";
		try (Connection connection = _source.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
			ResultSet result = stmt.executeQuery();
			while (result.next()) {
				String id = result.getString("DISCOUNT_CODE");
				float taux = result.getFloat("RATE");
				CodeDiscount code = new CodeDiscount(id, taux);
				listCodeDiscount.add(code);
			}
		}
		return listCodeDiscount;
	}

	/*Pour ajouter un enregistrement*/
	public int ajouterCodeDiscount(String code, float rate) throws SQLException {
		int resultat = 0;
		String sql = "INSERT INTO DISCOUNT_CODE VALUES (?, ?)";
		try (Connection connection = _source.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, code);
			stmt.setFloat(2, rate);
			resultat = stmt.executeUpdate();
		}
		return resultat;
	}
		
	/*Pour supprimer un enregistrement*/
	public int supprimerCodeDiscount(String codeDiscount) throws SQLException {
		int resultat = 0;
		String sql = "DELETE FROM DISCOUNT_CODE WHERE DISCOUNT_CODE = ?";
		try (Connection connection = _source.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, codeDiscount);
			resultat = stmt.executeUpdate();
		}
		return resultat;
	}
}
