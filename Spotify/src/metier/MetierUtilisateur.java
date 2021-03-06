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
import table.TableMusique;
import table.TableUtilisateur;

@Stateless
public class MetierUtilisateur implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private AccesseurUtilisateur accesseurUtilisateur;

	public void ajouter(String nom, String email, String motDePasse) {
		this.ajouter(transcodeTableEnParam(nom, email, motDePasse));
	}

	public void ajouter(Utilisateur utilisateur) {
		this.accesseurUtilisateur.insert(utilisateur);
	}

	public Utilisateur rechercher(long idUtilisateur) {
		TableUtilisateur tableUtilisateur = this.accesseurUtilisateur.select(idUtilisateur);
		return transcodeTableEnDTO(tableUtilisateur);
	}

	public Utilisateur rechercher(String nom, String email) {
		Utilisateur utilisateur = transcodeTableEnParam(nom, email, null);
		return this.rechercher(utilisateur);
	}

	public Utilisateur rechercher(Utilisateur utilisateur) {
		TableUtilisateur tableUtilisateur = this.accesseurUtilisateur.select(utilisateur);
		return transcodeTableEnDTO(tableUtilisateur);
	}

	// Recherche de l'utilisateur puis suppression
	public void supprimer(Utilisateur utilisateur) {
		TableUtilisateur tableUtilisateur = this.accesseurUtilisateur.select(utilisateur);

		if (tableUtilisateur != null) {
			this.supprimer(tableUtilisateur.getIdUtilisateur());
		}
	}

	public void supprimer(long idUtilisateur) {
		this.accesseurUtilisateur.delete(idUtilisateur);
	}

	public Utilisateur modifier(long idUtilisateur, String nom, String email) {
		Utilisateur utilisateur = transcodeTableEnParam(idUtilisateur, nom, email, null);
		utilisateur = this.modifier(utilisateur);
		return utilisateur;
	}

	public Utilisateur modifier(Utilisateur utilisateur) {
		TableUtilisateur tableUtilisateur = this.accesseurUtilisateur.update(utilisateur);
		return transcodeTableEnDTO(tableUtilisateur);
	}

	public Utilisateur modifierMotDePasse(Utilisateur utilisateur) {
		TableUtilisateur tableUtilisateur = this.accesseurUtilisateur.updateMotDePasse(utilisateur);
		return transcodeTableEnDTO(tableUtilisateur);
	}

	public List<Utilisateur> lister() {
		List<TableUtilisateur> listeTableUtilisateur = accesseurUtilisateur.lister();
		List<Utilisateur> listeUtilisateur = new ArrayList<>();

		for (TableUtilisateur tableUtilisateur : listeTableUtilisateur) {
			Utilisateur utilisateur = transcodeTableEnDTO(tableUtilisateur);
			listeUtilisateur.add(utilisateur);
		}
		return listeUtilisateur;
	}

	public void ajouterMusique(long idUtilisateur, long idMusique) {
		this.accesseurUtilisateur.ajouterMusique(idUtilisateur, idMusique);
	}

	public void supprimerMusique(long idUtilisateur, long idMusique) {
		this.accesseurUtilisateur.supprimerMusique(idUtilisateur, idMusique);
	}

	public Utilisateur preparerEdition(long idUtilisateur) {
		TableUtilisateur tableUtilisateur = this.accesseurUtilisateur.select(idUtilisateur);
		if (tableUtilisateur != null) {
			return transcodeTableEnDTO(tableUtilisateur);
		} else {
			return null;
		}
	}

	public Utilisateur transcodeTableEnDTO(TableUtilisateur tableUtilisateur) {
		if (tableUtilisateur != null) {
			Utilisateur utilisateur = new UtilisateurDTO();
			utilisateur.setIdUtilisateur(tableUtilisateur.getIdUtilisateur());
			utilisateur.setNom(tableUtilisateur.getNom());
			utilisateur.setEmail(tableUtilisateur.getEmail());
			utilisateur.setMotDePasse(tableUtilisateur.getMotDePasse());

			for (TableMusique tableMusique : tableUtilisateur.getListeMusique()) {
				tableMusique.setListeUtilisateur(null);
				utilisateur.getListeMusique().add(tableMusique);
			}
			return utilisateur;
		} else {
			return null;
		}
	}

	public Utilisateur transcodeTableEnParam(long idUtilisateur, String nom, String email, String motDePasse) {
		Utilisateur utilisateur = transcodeTableEnParam(nom, email, motDePasse);
		utilisateur.setIdUtilisateur(idUtilisateur);
		return utilisateur;
	}

	public Utilisateur transcodeTableEnParam(String nom, String email, String motDePasse) {
		Utilisateur utilisateur = new UtilisateurParam();
		utilisateur.setNom(nom);
		utilisateur.setEmail(email);
		utilisateur.setMotDePasse(motDePasse);
		utilisateur.setListeMusique(new ArrayList<>());
		return utilisateur;
	}
}