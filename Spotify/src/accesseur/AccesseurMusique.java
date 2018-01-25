package accesseur;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import structure.Musique;
import table.TableMusique;

@Stateless
public class AccesseurMusique {

	@PersistenceContext(unitName = "persistenceH2")
	private EntityManager em;

	public void insert(String titre, String artiste) {
		System.out.println("AccesseurMusique:insert: titre="+titre+" artiste="+ artiste);
		TableMusique tableMusique = new TableMusique();
		tableMusique.setTitre(titre);
		tableMusique.setArtiste(artiste);
		this.em.persist(tableMusique);
	}
	
	public void insert(Musique musique) {
		TableMusique tableMusique = new TableMusique();
		tableMusique.setTitre(musique.getTitre());
		tableMusique.setArtiste(musique.getArtiste());
		this.em.persist(tableMusique);
	}

	public TableMusique select(long idMusique) {
		return this.em.find(TableMusique.class, idMusique);
	}

	public TableMusique recherche(String titre, String artiste) {
		try {
			TypedQuery<TableMusique> query = this.em
					.createQuery("select a from TableMusique a where a.titre=?1 and a.artiste=?2 ", TableMusique.class);

			query.setParameter(1, titre);
			query.setParameter(2, artiste);

			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
	public TableMusique update(long idMusique, String titre, String artiste) {
		if (this.select(idMusique) != null) {
			TableMusique tableMusique = new TableMusique();
			tableMusique.setIdMusique(idMusique);
			tableMusique.setTitre(titre);
			tableMusique.setArtiste(artiste);
			return this.em.merge(tableMusique);
		} else {
			return null;
		}
	}
	
	public TableMusique update(Musique musique) {
		if (this.select(musique.getIdMusique()) != null) {
			TableMusique tableMusique = new TableMusique();
			tableMusique.setIdMusique(musique.getIdMusique());
			tableMusique.setTitre(musique.getTitre());
			tableMusique.setArtiste(musique.getArtiste());
			tableMusique.setListeUtilisateur(musique.getListeUtilisateur());
			return this.em.merge(tableMusique);
		} else {
			return null;
		}
	}

	public void delete(long idMusique) {
		TableMusique TableMusique = this.select(idMusique);
		if (TableMusique != null) {
			this.em.remove(TableMusique);
		}
	}

	public List<TableMusique> liste() {
		return this.em.createQuery("select a from TableMusique a").getResultList();
	}
}