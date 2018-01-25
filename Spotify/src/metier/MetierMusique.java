package metier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import accesseur.AccesseurMusique;
import structure.Musique;
import structure.MusiqueDTO;
import structure.MusiqueParam;
import table.TableMusique;

@Stateless
public class MetierMusique implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private AccesseurMusique accesseurMusique;

	public void ajouter(String titre, String artiste) {
		this.accesseurMusique.insert(transcodeTableEnParam(titre, artiste));
	}

	public Musique rechercher(long idMusique) {
		TableMusique tableMusique = this.accesseurMusique.select(idMusique);
		return transcodeTableEnDTO(tableMusique);
	}

	public Musique recherche(String titre, String artiste) {
		TableMusique tableMusique = this.accesseurMusique.recherche(transcodeTableEnParam(titre, artiste));
		return transcodeTableEnDTO(tableMusique);
	}

	public void supprimer(String titre, String artiste) {
		TableMusique tableMusique = this.accesseurMusique.recherche(transcodeTableEnParam(titre, artiste));
		if (tableMusique != null) {
			this.accesseurMusique.delete(tableMusique.getIdMusique());
		}
	}
	public void supprimer(long idMusique) {
		this.accesseurMusique.delete(idMusique);
	}
	
	public Musique modifier(long idMusique, String titre, String artiste) {
		Musique musique = transcodeTableEnParam(idMusique, titre, artiste);
		return this.modifier(musique);
	}
	public Musique modifier(Musique musique) {
		TableMusique tableMusique = this.accesseurMusique.update(musique);
		return transcodeTableEnDTO(tableMusique);
	}

	public List<Musique> lister() {
		List<TableMusique> listeTableMusique = accesseurMusique.liste();
		List<Musique> listeMusique = new ArrayList<>();

		for (TableMusique tableMusique : listeTableMusique) {
			Musique musique = transcodeTableEnDTO(tableMusique);
			listeMusique.add(musique);
		}
		return listeMusique;
	}

	public Musique prepareEdition(long idMusique) {
		TableMusique tableMusique = this.accesseurMusique.select(idMusique);
		if (tableMusique != null) {
			return transcodeTableEnDTO(tableMusique);
		} else {
			return null;
		}
	}

	public Musique transcodeTableEnDTO(TableMusique tableMusique) {
		Musique musique = new MusiqueDTO();
		musique.setIdMusique(tableMusique.getIdMusique());
		musique.setTitre(tableMusique.getTitre());
		musique.setArtiste(tableMusique.getArtiste());
		musique.setListeUtilisateur(tableMusique.getListeUtilisateur());
		return musique;
	}

	public Musique transcodeTableEnParam(long idMusique, String titre, String artiste) {
		Musique musique = transcodeTableEnParam(titre, artiste);
		musique.setIdMusique(idMusique);
		return musique;
	}

	public Musique transcodeTableEnParam(String titre, String artiste) {
		Musique musique = new MusiqueParam();
		musique.setTitre(titre);
		musique.setArtiste(artiste);
		musique.setListeUtilisateur(new ArrayList<>());
		return musique;
	}

}