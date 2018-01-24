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
	@ManyToMany(fetch = FetchType.EAGER)
	private List<TableMusique> listeMusique;

	public TableUtilisateur() {
		
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

	public List<TableMusique> getListeMusique() {
		if (this.listeMusique == null) {
			this.listeMusique = new ArrayList<>();
		}
		return listeMusique;
	}

	public void setListeMusique(List<TableMusique> listeMusique) {
		this.listeMusique = listeMusique;
	}
}
