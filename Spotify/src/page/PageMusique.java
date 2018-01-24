package page;

public class PageMusique {
	
	private long idMusique;
	private String titre;
	private String artiste;

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

	public long getIdMusique() {
		return idMusique;
	}

	public void setIdMusique(long idMusique) {
		this.idMusique = idMusique;
	}
}