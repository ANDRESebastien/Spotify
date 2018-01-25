package metier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import accesseur.AccesseurMusique;
import structure.Musique;
import structure.MusiqueParam;
import structure.MusiqueDTO;
import table.TableMusique;

@Stateless
public class MetierMusique implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private AccesseurMusique accesseurMusique;

	public MetierMusique() {
	}

	public void ajouter(String titre, String artiste) {
		this.accesseurMusique.insert(titre, artiste);
	}

	public Musique recherche(long idMusique) {
		TableMusique tableMusique = this.accesseurMusique.select(idMusique);
		Musique musique = new MusiqueDTO();
		musique.setIdMusique(tableMusique.getIdMusique());
		musique.setTitre(tableMusique.getTitre());
		musique.setArtiste(tableMusique.getArtiste());
		musique.setListeUtilisateur(tableMusique.getListeUtilisateur());
		return musique;
	}

	public Musique recherche(String titre, String artiste) {
		TableMusique tableMusique = this.accesseurMusique.recherche(titre, artiste);
		Musique musique = new MusiqueDTO();
		musique.setIdMusique(tableMusique.getIdMusique());
		musique.setTitre(tableMusique.getTitre());
		musique.setArtiste(tableMusique.getArtiste());
		musique.setListeUtilisateur(tableMusique.getListeUtilisateur());
		return musique;
	}

	public void supprimer(String titre, String artiste) {
		TableMusique tableMusique = this.accesseurMusique.recherche(titre, artiste);

		if (tableMusique != null) {
			this.accesseurMusique.delete(tableMusique.getIdMusique());
		}
	}

	public void supprimer(long id) {
		TableMusique tableMusique = this.accesseurMusique.select(id);

		if (tableMusique != null) {
			this.accesseurMusique.delete(tableMusique.getIdMusique());
		}
	}

	public Musique modifier(String titre, String artiste) {
		TableMusique tableMusique = this.accesseurMusique.recherche(titre, artiste);

		if (tableMusique != null) {
			this.accesseurMusique.update(tableMusique.getIdMusique(), titre, artiste);
			Musique musique = new MusiqueDTO();
			musique.setIdMusique(tableMusique.getIdMusique());
			musique.setTitre(tableMusique.getTitre());
			musique.setArtiste(tableMusique.getArtiste());
			musique.setListeUtilisateur(tableMusique.getListeUtilisateur());
			return musique;
		} else {
			return null;
		}
	}

	public Musique modifier(long id, String titre, String artiste) {
		TableMusique tableMusique = this.accesseurMusique.select(id);

		if (tableMusique != null) {
			tableMusique = this.accesseurMusique.update(id, titre, artiste);
			Musique musique = new MusiqueDTO();
			musique.setIdMusique(tableMusique.getIdMusique());
			musique.setTitre(tableMusique.getTitre());
			musique.setArtiste(tableMusique.getArtiste());
			musique.setListeUtilisateur(tableMusique.getListeUtilisateur());
			return musique;
		} else {
			return null;
		}
	}

	public Musique modifier(Musique musique) {
		TableMusique tableMusique = this.accesseurMusique.select(musique.getIdMusique());

		if (tableMusique != null) {
			tableMusique = this.accesseurMusique.update(musique);
			musique = new MusiqueDTO();
			musique.setIdMusique(tableMusique.getIdMusique());
			musique.setTitre(tableMusique.getTitre());
			musique.setArtiste(tableMusique.getArtiste());
			musique.setListeUtilisateur(tableMusique.getListeUtilisateur());
			return musique;
		} else {
			return null;
		}
	}

	public List<Musique> liste() {
		List<TableMusique> listeTableMusique = accesseurMusique.liste();
		List<Musique> listeMusique = new ArrayList<>();

		for (TableMusique tableMusique : listeTableMusique) {
			Musique musique = new MusiqueParam();
			musique.setIdMusique(tableMusique.getIdMusique());
			musique.setTitre(tableMusique.getTitre());
			musique.setArtiste(tableMusique.getArtiste());
			musique.setListeUtilisateur(tableMusique.getListeUtilisateur());
			listeMusique.add(musique);
		}
		return listeMusique;
	}

	public Musique prepareEdition(long id) {
		TableMusique tableMusique = this.accesseurMusique.select(id);
		if (tableMusique != null) {
			Musique musique = new MusiqueDTO();
			musique.setIdMusique(tableMusique.getIdMusique());
			musique.setTitre(tableMusique.getTitre());
			musique.setArtiste(tableMusique.getArtiste());
			musique.setListeUtilisateur(tableMusique.getListeUtilisateur());
			return musique;
		} else {
			return null;
		}
	}
}