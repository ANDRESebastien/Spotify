package page;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import metier.MetierMusique;
import structure.Musique;
import structure.MusiqueParam;

@ManagedBean
@RequestScoped
public class PageMusique implements Serializable {

	private static final long serialVersionUID = 1L;

	private Musique musique;

	@ManagedProperty("#{param.musiqueId}")
	private long musiqueId;

	@EJB
	private MetierMusique metierMusique;

	public PageMusique() {
		this.musique = new MusiqueParam();
	}

	public void ajouter() {
		this.metierMusique.ajouter(this.musique.getTitre(), this.musique.getArtiste());
	}

	public void supprimer() {
		this.metierMusique.supprimer(this.musique.getTitre(), this.musique.getArtiste());
	}

	public void modifier() {
		System.out.println("PageMusique:modifier(): id=" + this.musique.getIdMusique());
		
		if (this.musique.getIdMusique() > 0) {
			this.metierMusique.modifier(this.musique.getIdMusique(), this.musique.getTitre(), this.musique.getArtiste());
		} else {
			javax.faces.context.FacesContext.getCurrentInstance().addMessage("musiqueForm:global",
					new FacesMessage(" Pour modifier, merci de séléctionner une musique via la liste."));
		}
	}

	public Musique getMusique() {
		return musique;
	}

	public void setMusique(Musique musique) {
		this.musique = musique;
	}

	public String preparerEdition() {
		this.musique = this.metierMusique.preparerEdition(this.musiqueId);
		if (this.musique != null) {
			return "musique";
		}
		return null;
	}

	public long getMusiqueId() {
		return musiqueId;
	}

	public void setMusiqueId(long musiqueId) {
		this.musiqueId = musiqueId;
	}

	public String pageListemusique() {
		return "listemusique";
	}

}