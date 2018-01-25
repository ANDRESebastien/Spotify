package metier;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import accesseur.AccesseurUtilisateur;
import structure.Utilisateur;
import structure.UtilisateurDTO;
import structure.UtilisateurParam;
import table.TableUtilisateur;

@Stateless
public class MetierUtilisateur implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private AccesseurUtilisateur accesseurUtilisateur;

	public void ajoute(Utilisateur utilisateur) {
		this.accesseurUtilisateur.insert(utilisateur);
	}

	public Utilisateur rechercher(long idUtilisateur) {
		TableUtilisateur tableUtilisateur = this.accesseurUtilisateur.select(idUtilisateur);
		Utilisateur utilisateur = new UtilisateurDTO();
		utilisateur.setIdUtilisateur(tableUtilisateur.getIdUtilisateur());
		utilisateur.setNom(tableUtilisateur.getNom());
		utilisateur.setEmail(tableUtilisateur.getEmail());
		utilisateur.setMotDePasse(tableUtilisateur.getMotDePasse());
		utilisateur.setListeMusique(tableUtilisateur.getListeMusique());
		return utilisateur;
	}

	public Utilisateur rechercher(Utilisateur utilisateur) {
		TableUtilisateur tableUtilisateur = this.accesseurUtilisateur.select(utilisateur);
		utilisateur = new UtilisateurDTO();
		utilisateur.setIdUtilisateur(tableUtilisateur.getIdUtilisateur());
		utilisateur.setNom(tableUtilisateur.getNom());
		utilisateur.setEmail(tableUtilisateur.getEmail());
		utilisateur.setMotDePasse(tableUtilisateur.getMotDePasse());
		utilisateur.setListeMusique(tableUtilisateur.getListeMusique());
		return utilisateur;
	}
	
	public void supprimer(Utilisateur utilisateur) {
		TableUtilisateur tableUtilisateur = this.accesseurUtilisateur.select(utilisateur);

		if (tableUtilisateur != null) {
			this.accesseurUtilisateur.delete(tableUtilisateur.getIdUtilisateur());
		}
	}

	public void supprimer(long idUtilisateur) {
		TableUtilisateur tableUtilisateur = this.accesseurUtilisateur.select(idUtilisateur);

		if (tableUtilisateur != null) {
			this.accesseurUtilisateur.delete(tableUtilisateur.getIdUtilisateur());
		}
	}

	public Utilisateur modifier(Utilisateur utilisateur) {
		TableUtilisateur tableUtilisateur = this.accesseurUtilisateur.select(utilisateur);

		if (tableUtilisateur != null) {
			this.accesseurUtilisateur.update(utilisateur);
			utilisateur = new UtilisateurDTO();
			utilisateur.setIdUtilisateur(tableUtilisateur.getIdUtilisateur());
			utilisateur.setNom(tableUtilisateur.getNom());
			utilisateur.setEmail(tableUtilisateur.getEmail());
			utilisateur.setMotDePasse(tableUtilisateur.getMotDePasse());
			utilisateur.setListeMusique(tableUtilisateur.getListeMusique());
			return utilisateur;
		} else {
			return null;
		}
	}

	public Utilisateur modifier(long idUtilisateur, String nom, String email, String motDePasse) {
		TableUtilisateur tableUtilisateur = this.accesseurUtilisateur.select(idUtilisateur);

		if (tableUtilisateur != null) {
			Utilisateur utilisateur = new UtilisateurParam();
			utilisateur.setIdUtilisateur(idUtilisateur);
			utilisateur.setNom(nom);
			utilisateur.setEmail(email);
			utilisateur.setMotDePasse(motDePasse);
			utilisateur.setListeMusique(tableUtilisateur.getListeMusique());
			
			tableUtilisateur = this.accesseurUtilisateur.update(utilisateur);
			
			utilisateur = new UtilisateurDTO();
			utilisateur.setIdUtilisateur(tableUtilisateur.getIdUtilisateur());
			utilisateur.setNom(tableUtilisateur.getNom());
			utilisateur.setEmail(tableUtilisateur.getEmail());
			utilisateur.setMotDePasse(tableUtilisateur.getMotDePasse());
			utilisateur.setListeMusique(tableUtilisateur.getListeMusique());
			return utilisateur;
		} else {
			return null;
		}
	}

	
	public List<Utilisateur> liste() {
		List<TableUtilisateur> listeTableUtilisateur = accesseurUtilisateur.liste();
		List<Utilisateur> listeUtilisateur = new ArrayList<>();
		
		for (TableUtilisateur tableUtilisateur : listeTableUtilisateur) {
			Utilisateur utilisateur = new UtilisateurParam();
			utilisateur.setIdUtilisateur(tableUtilisateur.getIdUtilisateur());
			utilisateur.setNom(tableUtilisateur.getNom());
			utilisateur.setEmail(tableUtilisateur.getEmail());
			utilisateur.setMotDePasse(tableUtilisateur.getMotDePasse());
			utilisateur.setListeMusique(tableUtilisateur.getListeMusique());
			listeUtilisateur.add(utilisateur);
		}
		return listeUtilisateur;
	}

	public void ajouteMusique(long idUtilisateur, long idMusique) {
		accesseurUtilisateur.ajouteMusique( idUtilisateur,  idMusique);
	} 
}