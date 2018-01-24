package api;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import accesseur.AccesseurMusique;
import table.TableMusique;

@Path("/musique")
public class RestMusique {

	@EJB
	private AccesseurMusique accesseurMusique;

	@GET
	@Path("/{idMusique}")
	@Produces("application/json")
	public TableMusique recherche(@PathParam("idMusique") long idMusique) {
		return this.accesseurMusique.select(idMusique);
	}

	@GET
	@Path("/")
	@Produces("application/json")
	public List<TableMusique> listeMusique() {
		return this.accesseurMusique.liste();
	}

	@POST
	@Path("/{titre}/{artiste}")
	@Produces("text/html")
	public String ajoute(@PathParam("titre") String titre, @PathParam("artiste") String artiste) {
		this.accesseurMusique.insert(titre, artiste);
		return "Ok";
	}

	@POST
	@Path("/ajoute")
	@Consumes(MediaType.APPLICATION_JSON)
	public String ajoute(TableMusique tableMusique) {
		this.accesseurMusique.insert(tableMusique.getTitre(),tableMusique.getArtiste());
		return "Ok";
	}

	@POST
	@Path("/supprime/{idMusique}")
	@Produces("text/html")
	public String supprime(@PathParam("idMusique") long idMusique) {
		return this.accesseurMusique.delete(idMusique);
	}

	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public String update(TableMusique TableMusique) {
		TableMusique resultatUpdate = this.accesseurMusique.update(TableMusique);

		if (resultatUpdate != null) {
			return "Ok";
		} else {
			return "Ko";
		}
	}

	@PUT
	@Path("/{idMusique}/{titre}/{artiste}")
	@Produces("text/html")
	public String modifie(@PathParam("idMusique") long idMusique, @PathParam("titre") String titre,
			@PathParam("artiste") String artiste) {

		System.out.println("RestMusique:modifie:" + idMusique);
		TableMusique tableMusique = this.recherche(idMusique);
		tableMusique.setTitre(titre);
		tableMusique.setArtiste(artiste);
		TableMusique resultatUpdate = this.accesseurMusique.update(tableMusique);

		if (resultatUpdate != null) {
			return "Ok";
		} else {
			return "Ko";
		}
	}

}
