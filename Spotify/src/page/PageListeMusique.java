package page;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import metier.MetierMusique;
import structure.Musique;
import structure.MusiqueParam;

@ManagedBean
@RequestScoped
public class PageListeMusique {
	private Musique musique;
	private List<Musique> listeMusique;

	@EJB
	private MetierMusique metierMusique;

	@PostConstruct
	public void init() {
		this.listeMusique = this.getListeMusique();
		this.liste();
	}

	public String liste() {
		this.listeMusique = this.metierMusique.liste();
		return "listemusique";
	}
	
	public String delete(long id) {
		this.metierMusique.supprimer(id);
		return "listemusique";
	}


	public List<Musique> getListeMusique() {
		if (this.listeMusique == null) {
			this.listeMusique = new ArrayList<>();
		}
		return this.listeMusique;
	}

	public void setListeMusique(List<Musique> listeMusique) {
		this.listeMusique = listeMusique;
	}

	public Musique getMusique() {
		return musique;
	}

	public void setMusique(MusiqueParam musique) {
		this.musique = musique;
	}
	
	public String pageMusique() {
		return "musique";
	}
}