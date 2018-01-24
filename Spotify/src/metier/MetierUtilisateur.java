package metier;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import accesseur.AccesseurUtilisateur;
import page.PageUtilisateur;
import table.TableUtilisateur;

@ManagedBean
@RequestScoped
public class MetierUtilisateur implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private TableUtilisateur utilisateur;
	private PageUtilisateur pageUtilisateur;
	

	@EJB
	private AccesseurUtilisateur accesseurUtilisateur;

	public MetierUtilisateur() {
		this.utilisateur = new TableUtilisateur();
		this.pageUtilisateur = new PageUtilisateur();
	}



	public String delete(long id) {
		this.accesseurUtilisateur.delete(id);
		return "listenom";
	}

	public String aiguillage() {
		String action = "";
		this.utilisateur.setNom(this.utilisateur.getNom());
		this.utilisateur.setMotDePasse(this.utilisateur.getMotDePasse());

		
		TableUtilisateur resultat = this.accesseurUtilisateur.select(this.utilisateur.getIdUtilisateur());
		System.out.println(" resultat = " + resultat);

		if (resultat != null) {
			javax.faces.context.FacesContext.getCurrentInstance().addMessage("administrationForm:nom",
					new FacesMessage(" Le nom utilisateur est déjà présent en base."));
			action = "index";
		} else {
			this.accesseurUtilisateur.insert(this.utilisateur);
			action = "listenom";
		}
		return action;
	}

	
	
	public String connexion() {
		String action = "";
		System.out.println("AdministrationBackingBean:connexion: Bean => nom = " + this.utilisateur.getNom()    + " mdp = " + this.utilisateur.getMotDePasse() );

		TableUtilisateur resultat = this.accesseurUtilisateur.select(this.utilisateur.getIdUtilisateur());

		if (resultat != null &&  this.utilisateur.getNom().equals(resultat.getNom()) &&  this.utilisateur.getMotDePasse().equals(resultat.getMotDePasse())) {
				action = "acceuil";
			} else {
				javax.faces.context.FacesContext.getCurrentInstance().addMessage("administrationForm:global",
						new FacesMessage(" Le nom utilisateur et/ou mot de passe éronné."));
				action = "index";
			}
		return action;
	}
	
	public void changerMotDePasse() {
		this.utilisateur.setNom(this.pageUtilisateur.getNom());
		this.utilisateur.setMotDePasse(this.pageUtilisateur.getMotDePasse());
		
		this.accesseurUtilisateur.update(this.utilisateur);
	}

	public List<TableUtilisateur> lister() {
		return accesseurUtilisateur.liste();
	}



	public TableUtilisateur getUtilisateur() {
		return utilisateur;
	}



	public void setUtilisateur(TableUtilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}



	public PageUtilisateur getPageUtilisateur() {
		return pageUtilisateur;
	}



	public void setPageUtilisateur(PageUtilisateur PageUtilisateur) {
		this.pageUtilisateur = PageUtilisateur;
	}

}