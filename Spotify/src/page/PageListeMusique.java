package page;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import metier.MetierMusique;
import structure.Musique;

@ManagedBean
@RequestScoped
public class PageListeMusique {
	private Musique musique;
	private List<Musique> listeMusique;

	@EJB
	private MetierMusique metierMusique;

	@PostConstruct
	public void init() {
		this.listeMusique = getListeMusique();
		this.liste();
	}

	public String liste() {
		this.listeMusique = this.metierMusique.liste();
		return "listemusique";
	}
	
	public String delete(long id) {
		this.metierMusique.delete(id);
		return "listemusique";
	}

	public String prepare(long id) {
		this.musique = this.metierMusique.prepare(id);
		return "musique";
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

	public void setMusique(Musique musique) {
		this.musique = musique;
	}
}