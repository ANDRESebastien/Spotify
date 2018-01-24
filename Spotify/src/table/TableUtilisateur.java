package table;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Utilisateur")
public class TableUtilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idUtilisateur;

	@Column
	private String nom;

	@Column
	private String email;

	@Column
	private String motDePasse;

	@Column
	@ManyToMany(fetch = FetchType.LAZY)
	private List<TableMusique> listeMusique;

	public TableUtilisateur() {
		this.listeMusique = new ArrayList<>();
	}

	public long getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(long idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

}
