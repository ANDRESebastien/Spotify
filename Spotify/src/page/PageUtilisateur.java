package page;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import metier.MetierUtilisateur;
import structure.Utilisateur;
import structure.UtilisateurParam;

@ManagedBean
@RequestScoped
public class PageUtilisateur implements Serializable {

	private static final long serialVersionUID = 1L;

	private Utilisateur utilisateur;

	@EJB
	private MetierUtilisateur metierUtilisateur;

	public PageUtilisateur() {
		this.utilisateur = new UtilisateurParam();
	}

	public String supprimer(long id) {
		this.metierUtilisateur.supprimer(id);
		return "listenom";
	}

	public String valider() {
		String action = "";

		Utilisateur resultat = this.metierUtilisateur.rechercher(this.utilisateur);
		if (resultat != null) {
			javax.faces.context.FacesContext.getCurrentInstance().addMessage("Form:nom",
					new FacesMessage(" Le nom utilisateur est déjà présent en base."));
			action = "utilisateur";
		} else {
			this.metierUtilisateur.ajoute(this.utilisateur);
			action = "listeutilisateur";
		}
		return action;
	}

	public String connexion() {
		String action = "";
		System.out.println("AdministrationBackingBean:connexion: Bean => nom = " + this.utilisateur.getNom() + " mdp = "
				+ this.utilisateur.getMotDePasse());

		Utilisateur resultat = this.metierUtilisateur.rechercher(this.utilisateur.getIdUtilisateur());

		if (resultat != null && this.utilisateur.getNom().equals(resultat.getNom())
				&& this.utilisateur.getMotDePasse().equals(resultat.getMotDePasse())) {
			action = "acceuil";
		} else {
			javax.faces.context.FacesContext.getCurrentInstance().addMessage("administrationForm:global",
					new FacesMessage(" Le nom utilisateur et/ou mot de passe éronné."));
			action = "index";
		}
		return action;
	}

	public List<Utilisateur> liste() {
		return metierUtilisateur.liste();
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
}