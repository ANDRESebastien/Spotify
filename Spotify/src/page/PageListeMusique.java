package page;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import metier.MetierMusique;
import table.TableMusique;

public class PageListeMusique {
	
	private List<TableMusique> listeMusique;
	private MetierMusique metierMusique;
	
	public String delete(long id) {
		this.metierMusique.delete(id);
		return "listemusique";
	}
	
	@PostConstruct
	public void init() {
		this.listeMusique = getListeMusique();
	}
	
	public String prepare(long id) {
		this.metierMusique.delete(id);
		return "musique";
	}
	
	public List<TableMusique> getListeMusique() {
		if (this.listeMusique == null) {
			this.listeMusique = new ArrayList<>();
		}
		return listeMusique;
	}

	public void setListeMusique(List<TableMusique> listeMusique) {
		this.listeMusique = listeMusique;
	}

}