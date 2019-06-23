/**
 * @author sjeandet
 * Pour les enregistement
 */

package modele;

public class CodeDiscount {

	private String _codeDiscount;
	private float _taux;

	public CodeDiscount(String codeDiscount, float taux) {
		this._codeDiscount = codeDiscount;
		this._taux = taux;
	}

	public String getcodeDiscount() {
		return _codeDiscount;
	}

	public float getTaux() {
		return _taux;
	}

}
