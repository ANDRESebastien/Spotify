package structure;

public class Musique {
	private long idMusique;
	private String titre;
	private String artiste;

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
}
