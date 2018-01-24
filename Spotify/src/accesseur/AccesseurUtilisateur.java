package accesseur;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import table.TableUtilisateur;

@Stateless
public class AccesseurUtilisateur {

	@PersistenceContext(unitName = "persistenceH2")
	private EntityManager em;

	public void insert(TableUtilisateur tableUtilisateur) {
		this.em.persist(tableUtilisateur);
	}

	public TableUtilisateur select(long idUtilisateur) {
		return this.em.find(TableUtilisateur.class, idUtilisateur);
	}

	public TableUtilisateur update(TableUtilisateur TableUtilisateur) {
		if (this.select(TableUtilisateur.getIdUtilisateur()) != null) {
			return this.em.merge(TableUtilisateur);
		} else {
			return null;
		}
	}

	public String delete(long idUtilisateur) {
		TableUtilisateur TableUtilisateur = this.select(idUtilisateur);
		if (TableUtilisateur != null) {
			this.em.remove(TableUtilisateur);
			return "idUtilisateur supprimé";
		} else {
			return "idUtilisateur non trouvé";
		}
	}

	public List<TableUtilisateur> liste() {
		return this.em.createQuery("select a from TableUtilisateur a").getResultList();
	}
}