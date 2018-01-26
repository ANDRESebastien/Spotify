package accesseur;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import structure.Musique;
import table.TableMusique;
import table.TableUtilisateur;

@Stateless
public class AccesseurMusique {

	@PersistenceContext(unitName = "persistenceH2")
	private EntityManager em;

	@EJB
	private AccesseurUtilisateur accesseurUtilisateur;

	public void insert(Musique musique) {
		if (this.rechercher(musique) == null) {
			TableMusique tableMusique = new TableMusique();
			tableMusique.setTitre(musique.getTitre());
			tableMusique.setArtiste(musique.getArtiste());
			this.em.persist(tableMusique);
		}
	}

	public TableMusique select(long idMusique) {
		return this.em.find(TableMusique.class, idMusique);
	}

	public TableMusique rechercher(Musique musique) {
		try {
			TypedQuery<TableMusique> query = this.em
					.createQuery("select a from TableMusique a where a.titre=?1 and a.artiste=?2 ", TableMusique.class);

			query.setParameter(1, musique.getTitre());
			query.setParameter(2, musique.getArtiste());

			return query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public TableMusique update(Musique musique) {
		TableMusique tableMusique = this.select(musique.getIdMusique());

		if (tableMusique != null) {
			tableMusique.setTitre(musique.getTitre());
			tableMusique.setArtiste(musique.getArtiste());
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

	public void ajouterUtilisateur(long idMusique, long idUtilisateur) {
		TableMusique tableMusique = this.select(idMusique);
		TableUtilisateur tableUtilisateur = this.accesseurUtilisateur.select(idUtilisateur);
		if (tableMusique != null && tableUtilisateur != null) {
			tableMusique.getListeUtilisateur().add(tableUtilisateur);
			this.em.persist(tableMusique);
		}
	}

	public void supprimerUtilisateur(long idMusique, long idUtilisateur) {
		TableMusique tableMusique = this.select(idMusique);
		TableUtilisateur tableUtilisateur = this.accesseurUtilisateur.select(idUtilisateur);
		if (tableMusique != null && tableUtilisateur != null) {
			tableMusique.getListeUtilisateur().remove(tableUtilisateur);
			this.em.persist(tableMusique);
		}
	}
}