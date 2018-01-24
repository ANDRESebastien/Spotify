package metier;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import accesseur.AccesseurMusique;
import page.PageListeMusique;
import page.PageMusique;
import table.TableMusique;

@ManagedBean
@RequestScoped
public class MetierMusique implements Serializable {

	private static final long serialVersionUID = 1L;

	private TableMusique musique;
	private PageMusique pageMusique;
	private PageListeMusique pageListeMusique;

	@EJB
	private AccesseurMusique accesseurMusique;

	public MetierMusique() {
		this.musique = new TableMusique();
		this.pageMusique = new PageMusique();
		this.pageListeMusique = new PageListeMusique();
	}

	public String delete(long id) {
		this.accesseurMusique.delete(id);
		return "listemusique";
	}

	public void ajouter() {
		this.musique.setTitre(this.pageMusique.getTitre());
		this.musique.setArtiste(this.pageMusique.getArtiste());
		this.accesseurMusique.insert(this.musique);
	}

	public void supprimer() {
		this.musique.setTitre(this.pageMusique.getTitre());
		this.musique.setArtiste(this.pageMusique.getArtiste());

		TableMusique tableMusique = this.accesseurMusique.recherche(this.musique.getTitre(), this.musique.getArtiste());

		if (tableMusique != null) {
			System.out.println("MetierMusique:supprimer(): trouvé id = " + tableMusique.getIdMusique());
			this.accesseurMusique.delete(tableMusique.getIdMusique());
		} else {
			System.out.println("MetierMusique:supprimer(): non trouvé " + tableMusique);
		}
	}
	
	public void modifier() {
		this.musique.setIdMusique(this.pageMusique.getIdMusique());
		
		
		this.musique.setTitre(this.pageMusique.getTitre());
		this.musique.setArtiste(this.pageMusique.getArtiste());

		TableMusique tableMusique = this.accesseurMusique.recherche(this.musique.getTitre(), this.musique.getArtiste());

		if (tableMusique != null) {
			System.out.println("MetierMusique:supprimer(): trouvé id = " + tableMusique.getIdMusique());
			this.accesseurMusique.delete(tableMusique.getIdMusique());
		} else {
			System.out.println("MetierMusique:supprimer(): non trouvé " + tableMusique);
		}
	}

	public List<TableMusique> liste() {
		return accesseurMusique.liste();
	}
	
	public String prepare(long id) {
		this.musique = this.accesseurMusique.select(id);
		this.pageMusique.setIdMusique(this.musique.getIdMusique());
		System.out.println();
		this.pageMusique.setTitre(this.musique.getTitre());
		this.pageMusique.setArtiste(this.musique.getArtiste());
		return "musique";
	}

	public TableMusique getMusique() {
		return musique;
	}

	public void setMusique(TableMusique musique) {
		this.musique = musique;
	}

	public PageMusique getPageMusique() {
		return pageMusique;
	}

	public void setPageMusique(PageMusique pageMusique) {
		this.pageMusique = pageMusique;
	}

	public PageListeMusique getPageListeMusique() {
		return pageListeMusique;
	}

	public void setPageListeMusique(PageListeMusique pageListeMusique) {
		this.pageListeMusique = pageListeMusique;
	}
}