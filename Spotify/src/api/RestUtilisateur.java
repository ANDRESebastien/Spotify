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

import metier.MetierUtilisateur;
import structure.Utilisateur;
import structure.UtilisateurParam;

@Path("/utilisateur")
public class RestUtilisateur {

	@EJB
	private MetierUtilisateur metierUtilisateur;

	@GET
	@Path("/{idUtilisateur}")
	@Produces("application/json")
	public Utilisateur rechercher(@PathParam("idUtilisateur") long idUtilisateur) {
		return this.metierUtilisateur.rechercher(idUtilisateur);
	}

	@GET
	@Path("/")
	@Produces("application/json")
	public List<Utilisateur> listerUtilisateur() {
		return this.metierUtilisateur.lister();
	}

	@POST
	@Path("/{nom}/{email}/{motDePasse}")
	public void ajouter(@PathParam("nom") String nom, @PathParam("email") String email,
			@PathParam("motDePasse") String motDePasse) {
		this.metierUtilisateur.ajouter(nom, email, motDePasse);
	}

	@POST
	@Path("/ajoute")
	@Consumes(MediaType.APPLICATION_JSON)
	public String ajouter(Utilisateur Utilisateur) {
		this.metierUtilisateur.ajouter(Utilisateur);
		return "Ok";
	}

	@PUT
	@Path("/{idUtilisateur}/{nom}/{email}/{motDePasse}")
	@Produces("text/html")
	public Utilisateur modifier(@PathParam("idUtilisateur") long idUtilisateur, @PathParam("nom") String nom,
			@PathParam("email") String email, @PathParam("motDePasse") String motDePasse) {
		return this.metierUtilisateur.modifier(idUtilisateur, nom, email, motDePasse);
	}

	@POST
	@Path("/musique/{idUtilisateur}/{idMusique}")
	@Produces("text/html")
	public void ajouterMusique(@PathParam("idUtilisateur") long idUtilisateur, @PathParam("idMusique") long idMusique) {
		this.metierUtilisateur.ajouterMusique(idUtilisateur, idMusique);
	}

	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(Utilisateur utilisateur) {
		this.metierUtilisateur.modifier(utilisateur);
	}

	@DELETE
	@Path("/{idUtilisateur}")
	@Produces("text/html")
	public void supprimer(@PathParam("idUtilisateur") long idUtilisateur) {
		this.metierUtilisateur.supprimer(idUtilisateur);
	}

	@DELETE
	@Path("/musique/{idUtilisateur}/{idMusique}")
	@Produces("text/html")
	public void supprimerMusique(@PathParam("idUtilisateur") long idUtilisateur,
			@PathParam("idMusique") long idMusique) {
		this.metierUtilisateur.supprimerMusique(idUtilisateur, idMusique);
	}
}
