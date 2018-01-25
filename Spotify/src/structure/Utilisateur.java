package structure;

import java.util.ArrayList;
import java.util.List;

import table.TableMusique;

abstract public class Utilisateur {

	private long idUtilisateur;

	private String nom;

	private String email;

	private String motDePasse;

	private List<TableMusique> listeMusique;

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
