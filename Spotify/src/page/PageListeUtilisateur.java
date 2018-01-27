package page;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import metier.MetierUtilisateur;
import structure.Utilisateur;

@ManagedBean
@RequestScoped
public class PageListeUtilisateur {
	private Utilisateur utilisateur;
	private List<Utilisateur> listeUtilisateur;

	@EJB
	private MetierUtilisateur metierUtilisateur;

	@PostConstruct
	public void init() {
		this.listeUtilisateur = this.getListeUtilisateur();
		this.lister();
	}

	public String lister() {
		this.listeUtilisateur = this.metierUtilisateur.lister();
		return "listeutilisateur";
	}

	public String supprimer(long idUtilisateur) {
		this.metierUtilisateur.supprimer(idUtilisateur);
		return "listeutilisateur";
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public List<Utilisateur> getListeUtilisateur() {
		if (this.listeUtilisateur == null) {
			this.listeUtilisateur = new ArrayList<>();
		}
		return this.listeUtilisateur;
	}

	public void setListeUtilisateur(List<Utilisateur> listeUtilisateur) {
		this.listeUtilisateur = listeUtilisateur;
	}
	
	public String pageUtilisateur() {
		return "utilisateur";
	}
}
