package accesseur;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import structure.Utilisateur;
import table.TableMusique;
import table.TableUtilisateur;

@Stateless
public class AccesseurUtilisateur {

	@PersistenceContext(unitName = "persistenceH2")
	private EntityManager em;

	@EJB
	private AccesseurMusique accesseurMusique;

	public void insert(Utilisateur utilisateur) {
		TableUtilisateur tableUtilisateur = new TableUtilisateur();
		tableUtilisateur.setNom(utilisateur.getNom());
		tableUtilisateur.setEmail(utilisateur.getEmail());
		tableUtilisateur.setMotDePasse(utilisateur.getMotDePasse());
		tableUtilisateur.setListeMusique(utilisateur.getListeMusique());
		this.em.persist(tableUtilisateur);
	}

	public TableUtilisateur select(long idUtilisateur) {
		return this.em.find(TableUtilisateur.class, idUtilisateur);
	}

	public TableUtilisateur select(Utilisateur utilisateur) {
		try {
			TypedQuery<TableUtilisateur> query = this.em.createQuery(
					"select a from TableUtilisateur a where a.nom=?1 and a.email=?2 ", TableUtilisateur.class);

			query.setParameter(1, utilisateur.getNom());
			query.setParameter(2, utilisateur.getEmail());

			return query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public TableUtilisateur update(Utilisateur utilisateur) {
		TableUtilisateur tableUtilisateur = this.select(utilisateur.getIdUtilisateur());
		if (tableUtilisateur != null) {
			tableUtilisateur.setNom(utilisateur.getNom());
			tableUtilisateur.setEmail(utilisateur.getEmail());
			return this.em.merge(tableUtilisateur);
		} else {
			return null;
		}
	}

	public TableUtilisateur updateMotDePasse(Utilisateur utilisateur) {
		TableUtilisateur tableUtilisateur;
		try {
			tableUtilisateur = this.select(utilisateur.getIdUtilisateur());
			if (tableUtilisateur != null) {
				tableUtilisateur.setMotDePasse(utilisateur.getMotDePasse());
				tableUtilisateur = this.em.merge(tableUtilisateur);
			}
		} catch (Exception e) {
			e.printStackTrace();
			tableUtilisateur = null;
		}
		return tableUtilisateur;
	}

	public void delete(long idUtilisateur) {
		TableUtilisateur TableUtilisateur = this.select(idUtilisateur);
		if (TableUtilisateur != null) {
			this.em.remove(TableUtilisateur);
		}
	}

	public List<TableUtilisateur> lister() {
		return this.em.createQuery("select a from TableUtilisateur a").getResultList();
	}

	public void ajouterMusique(long idUtilisateur, long idMusique) {
		TableUtilisateur tableUtilisateur = this.select(idUtilisateur);
		TableMusique tableMusique = this.accesseurMusique.select(idMusique);
		if (tableUtilisateur != null && tableMusique != null) {
			tableUtilisateur.getListeMusique().add(tableMusique);
			this.em.persist(tableUtilisateur);
			
			tableMusique = this.accesseurMusique.select(idMusique);
			tableMusique.getListeUtilisateur().add(tableUtilisateur);
			this.em.persist(tableMusique);
		}
	}

	public void supprimerMusique(long idUtilisateur, long idMusique) {
		TableUtilisateur tableUtilisateur = this.select(idUtilisateur);
		TableMusique tableMusique = this.accesseurMusique.select(idMusique);
		if (tableUtilisateur != null && tableMusique != null) {
			tableUtilisateur.getListeMusique().remove(tableMusique);
			this.em.persist(tableUtilisateur);
			
			tableMusique = this.accesseurMusique.select(idMusique);
			tableMusique.getListeUtilisateur().remove(tableUtilisateur);
			this.em.persist(tableMusique);
		}
	}
}