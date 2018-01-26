package api;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import metier.MetierMusique;
import structure.Musique;

@Path("/musique")
public class RestMusique {

	@EJB
	private MetierMusique metierMusique;

	@GET
	@Path("/{idMusique}")
	@Produces("application/json")
	public Musique rechercher(@PathParam("idMusique") long idMusique) {
		return this.metierMusique.rechercher(idMusique);
	}

	@GET
	@Path("/{titre}/{artiste}")
	@Produces("application/json")
	public Musique rechercher(@PathParam("titre") String titre, @PathParam("artiste") String artiste) {
		return this.metierMusique.rechercher(titre, artiste);
	}

	@GET
	@Path("/")
	@Produces("application/json")
	public List<Musique> listerMusique() {
		return this.metierMusique.lister();
	}

	@POST
	@Path("/{titre}/{artiste}")
	public void ajouter(@PathParam("titre") String titre, @PathParam("artiste") String artiste) {
		this.metierMusique.ajouter(titre, artiste);
	}

	@POST
	@Path("/ajouter")
	@Consumes(MediaType.APPLICATION_JSON)
	public void ajouter(Musique musique) {
		this.metierMusique.ajouter(musique.getTitre(), musique.getArtiste());
	}

	@POST
	@Path("/utilisateur/{idMusique}/{idUtilisateur}")
	@Produces("application/json")
	public void ajouterutilisateur(@PathParam("idMusique") long idMusique,
			@PathParam("idUtilisateur") long idUtilisateur) {
		this.metierMusique.ajouterUtilisateur(idMusique, idUtilisateur);
	}

	@PUT
	@Path("/{idMusique}/{titre}/{artiste}")
	@Produces("application/json")
	public Musique modifier(@PathParam("idMusique") long idMusique, @PathParam("titre") String titre,
			@PathParam("artiste") String artiste) {
		return this.metierMusique.modifier(idMusique, titre, artiste);
	}

	@DELETE
	@Path("/{idMusique}")
	public void supprimer(@PathParam("idMusique") long idMusique) {
		this.metierMusique.supprimer(idMusique);
	}

	@DELETE
	@Path("/{titre}/{artiste}")
	public void supprimer(@PathParam("titre") String titre, @PathParam("artiste") String artiste) {
		this.metierMusique.supprimer(titre, artiste);
	}

	@DELETE
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Musique update(Musique musique) {
		return this.metierMusique.modifier(musique.getIdMusique(), musique.getTitre(), musique.getArtiste());
	}

	@DELETE
	@Path("/utilisateur/{idMusique}/{idUtilisateur}")
	public void supprimerutilisateur(@PathParam("idMusique") long idMusique,
			@PathParam("idUtilisateur") long idUtilisateur) {
		this.metierMusique.supprimerUtilisateur(idMusique, idUtilisateur);
	}
}
