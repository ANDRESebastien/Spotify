package table;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Musique")
public class TableMusique {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idMusique;

	@Column
	private String titre;

	@Column
	private String artiste;

	@Column
	@ManyToMany(fetch = FetchType.EAGER)
	private List<TableUtilisateur> listeUtilisateur;

	public TableMusique() {
	}

	public long getIdMusique() {
		return idMusique;
	}

	public void setIdMusique(long idMusique) {
		this.idMusique = idMusique;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getArtiste() {
		return artiste;
	}

	public void setArtiste(String artiste) {
		this.artiste = artiste;
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