package page;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import metier.MetierMusique;
import structure.Musique;

@ManagedBean
@RequestScoped
public class PageMusique implements Serializable {

	private static final long serialVersionUID = 1L;

	private Musique musique;
	
	@EJB
	private MetierMusique metierMusique;

	public PageMusique() {
		this.musique = new Musique();
	}

	public String delete(long id) {
		this.metierMusique.delete(id);
		return "listemusique";
	}

	public void ajouter() {
		System.out.println("PageMusique:ajouter: titre="+this.musique.getTitre()+" artiste="+ this.musique.getArtiste());
		this.metierMusique.ajouter(this.musique.getTitre(), this.musique.getArtiste());
	}

	public void supprimer() {
		this.metierMusique.supprimer(this.musique.getTitre(), this.musique.getArtiste());
	}
	
	public void modifier() {
		this.metierMusique.modifier(this.musique.getIdMusique(),this.musique.getTitre(), this.musique.getArtiste());
	}
	
	public String prepare() {
		this.musique = this.metierMusique.prepare(this.musique.getIdMusique());
		return "musique";
	}

	public Musique getMusique() {
		return musique;
	}

	public void setMusique(Musique musique) {
		this.musique = musique;
	}
}