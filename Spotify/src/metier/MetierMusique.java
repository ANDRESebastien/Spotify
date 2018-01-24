package metier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import accesseur.AccesseurMusique;
import structure.Musique;
import table.TableMusique;

@Stateless
public class MetierMusique implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private AccesseurMusique accesseurMusique;

	public MetierMusique() {
	}

	public String delete(long id) {
		this.accesseurMusique.delete(id);
		return "listemusique";
	}

	public void ajouter(String titre, String artiste) {
		System.out.println("MetierMusique:ajouter: titre="+titre+" artiste="+ artiste);
		this.accesseurMusique.insert(titre, artiste);
	}

	public void supprimer(String titre, String artiste) {
		TableMusique tableMusique = this.accesseurMusique.recherche(titre, artiste);

		if (tableMusique != null) {
			System.out.println("MetierMusique:supprimer(): trouvé id = " + tableMusique.getIdMusique());
			this.accesseurMusique.delete(tableMusique.getIdMusique());
		} else {
			System.out.println("MetierMusique:supprimer(): non trouvé " + tableMusique);
		}
	}

	public void supprimer(long id) {
		TableMusique tableMusique = this.accesseurMusique.select(id);

		if (tableMusique != null) {
			System.out.println("MetierMusique:supprimer(): trouvé id = " + tableMusique.getIdMusique());
			this.accesseurMusique.delete(tableMusique.getIdMusique());
		} else {
			System.out.println("MetierMusique:supprimer(): non trouvé " + tableMusique);
		}
	}

	public void modifier(long id, String titre, String artiste) {
		TableMusique tableMusique = this.accesseurMusique.recherche(titre, artiste);

		if (tableMusique != null) {
			System.out.println("MetierMusique:supprimer(): trouvé id = " + tableMusique.getIdMusique());
			this.accesseurMusique.delete(tableMusique.getIdMusique());
		} else {
			System.out.println("MetierMusique:supprimer(): non trouvé " + tableMusique);
		}
	}

	public List<Musique> liste() {
		System.out.println("MetierMusique:liste()");
		List<TableMusique> listeTableMusique = accesseurMusique.liste();
		List<Musique> listeMusique = new ArrayList<>();
		Musique musique = new Musique();;
		for (TableMusique tableMusique : listeTableMusique) {
			musique.setIdMusique(tableMusique.getIdMusique());
			musique.setTitre(tableMusique.getTitre());
			musique.setArtiste(tableMusique.getArtiste());
			System.out.println("MetierMusique:liste: titre" + musique.getTitre());
			listeMusique.add(musique);
		}
		return listeMusique;
	}

	public Musique prepare(long id) {
		TableMusique tableMusique = this.accesseurMusique.select(id);
		Musique musique = new Musique();
		musique.setIdMusique(tableMusique.getIdMusique());
		musique.setTitre(tableMusique.getTitre());
		musique.setArtiste(tableMusique.getArtiste());
		return musique;
	}
}