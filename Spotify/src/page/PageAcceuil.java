package page;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import metier.MetierUtilisateur;
import structure.Utilisateur;
import structure.UtilisateurParam;

@ManagedBean
@RequestScoped
public class PageAcceuil implements Serializable {

	private static final long serialVersionUID = 1L;

	private Utilisateur utilisateur;

	@EJB
	private MetierUtilisateur metierUtilisateur;

	public PageAcceuil() {
		this.utilisateur = new UtilisateurParam();
	}

	public String connexion(Utilisateur utilisateur) {
		String action = "";

		this.utilisateur = utilisateur;
		Utilisateur utilisateurBDD = this.metierUtilisateur.rechercher(this.utilisateur);

		if (utilisateurBDD != null && this.utilisateur.getNom().equals(utilisateurBDD.getNom())
				&& this.utilisateur.getEmail().equals(utilisateurBDD.getEmail())
				&& this.utilisateur.getMotDePasse().equals(utilisateurBDD.getMotDePasse())) {
			this.utilisateur = utilisateurBDD;
			action = "acceuil";
		} else {
			javax.faces.context.FacesContext.getCurrentInstance().addMessage("utilisateurForm:global",
					new FacesMessage(" Le nom utilisateur et/ou mot de passe éronné."));
			action = "utilisateur";
		}
		return action;
	}

	public String changerMotDePasse() {
		Utilisateur utilisateur = this.metierUtilisateur.modifierMotDePasse(this.utilisateur);
		this.utilisateur = utilisateur;
		return "acceuil";
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public String pageUtilisateur() {
		return "utilisateur";
	}
}