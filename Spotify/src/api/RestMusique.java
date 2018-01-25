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

import metier.MetierMusique;
import structure.Musique;
import structure.MusiqueParam;

@Path("/musique")
public class RestMusique {

	@EJB
	private MetierMusique metierMusique;

	@GET
	@Path("/{idMusique}")
	@Produces("application/json")
	public Musique recherche(@PathParam("idMusique") long idMusique) {
		return this.metierMusique.recherche(idMusique);
	}

	@GET
	@Path("/")
	@Produces("application/json")
	public List<Musique> listeMusique() {
		return this.metierMusique.liste();
	}

	@POST
	@Path("/{titre}/{artiste}")
	@Produces("text/html")
	public void ajoute(@PathParam("titre") String titre, @PathParam("artiste") String artiste) {
		this.metierMusique.ajouter(titre, artiste);
	}

	@POST
	@Path("/ajoute")
	@Consumes(MediaType.APPLICATION_JSON)
	public void ajoute(Musique musique) {
		this.metierMusique.ajouter(musique.getTitre(), musique.getArtiste());
	}

	@POST
	@Path("/supprime/{idMusique}")
	@Produces("text/html")
	public void supprime(@PathParam("idMusique") long idMusique) {
		this.metierMusique.supprimer(idMusique);
	}

	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(Musique musique) {
		this.metierMusique.modifier(musique.getIdMusique(), musique.getTitre(), musique.getArtiste());
	}

	@PUT
	@Path("/{idMusique}/{titre}/{artiste}")
	@Produces("text/html")
	public Musique modifie(@PathParam("idMusique") long idMusique, @PathParam("titre") String titre,
			@PathParam("artiste") String artiste) {
		Musique musique = this.recherche(idMusique);
		if (musique != null) {
			musique = this.metierMusique.modifier(musique.getIdMusique(), titre, artiste);
		}
		return musique;
	}
}
