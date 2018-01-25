package structure;

import java.util.ArrayList;
import java.util.List;

import table.TableUtilisateur;

abstract public class Musique {
	private long idMusique;
	private String titre;
	private String artiste;
	private List<TableUtilisateur> listeUtilisateur;

	public String getTitre() {
		return this.titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getArtiste() {
		return this.artiste;
	}

	public void setArtiste(String artiste) {
		this.artiste = artiste;
	}

	public long getIdMusique() {
		return this.idMusique;
	}

	public void setIdMusique(long idMusique) {
		this.idMusique = idMusique;
	}
	
	public List<TableUtilisateur> getListeUtilisateur() {
		if (this.listeUtilisateur == null) {
			this.listeUtilisateur = new ArrayList<>();
		}
		return this.listeUtilisateur;
	}

	public void setListeUtilisateur(List<TableUtilisateur> listeUtilisateur) {
		this.listeUtilisateur = listeUtilisateur;
	}
}
