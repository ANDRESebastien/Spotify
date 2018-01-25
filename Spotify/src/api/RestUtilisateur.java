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
	public Utilisateur recherche(@PathParam("idUtilisateur") long idUtilisateur) {
		return this.metierUtilisateur.rechercher(idUtilisateur);
	}

	@GET
	@Path("/")
	@Produces("application/json")
	public List<Utilisateur> listeUtilisateur() {
		return this.metierUtilisateur.liste();
	}

	@POST
	@Path("/{nom}/{email}/{motDePasse}")
	@Produces("text/html")
	public String ajoute(@PathParam("nom") String nom, @PathParam("email") String email,
			@PathParam("motDePasse") String motDePasse) {
		Utilisateur Utilisateur = new UtilisateurParam();
		Utilisateur.setNom(nom);
		Utilisateur.setEmail(email);
		Utilisateur.setMotDePasse(motDePasse);
		this.metierUtilisateur.ajoute(Utilisateur);
		return "Ok";
	}

	@POST
	@Path("/ajoute")
	@Consumes(MediaType.APPLICATION_JSON)
	public String ajoute(Utilisateur Utilisateur) {
		this.metierUtilisateur.ajoute(Utilisateur);
		return "Ok";
	}

	@POST
	@Path("/supprime/{idUtilisateur}")
	@Produces("text/html")
	public void supprime(@PathParam("idUtilisateur") long idUtilisateur) {
		this.metierUtilisateur.supprimer(idUtilisateur);
	}

	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(Utilisateur utilisateur) {
		this.metierUtilisateur.modifier(utilisateur);
	}

	@PUT
	@Path("/{idUtilisateur}/{nom}/{email}/{motDePasse}")
	@Produces("text/html")
	public Utilisateur modifie(@PathParam("idUtilisateur") long idUtilisateur, @PathParam("nom") String nom,
			@PathParam("email") String email, @PathParam("motDePasse") String motDePasse) {

		System.out.println("RestUtilisateur:modifie:" + idUtilisateur);
		Utilisateur utilisateur = this.recherche(idUtilisateur);
		utilisateur.setNom(nom);
		utilisateur.setEmail(email);
		utilisateur.setMotDePasse(motDePasse);
		return this.metierUtilisateur.modifier(utilisateur);

	}

	@POST
	@Path("/{idUtilisateur}/{idMusique}")
	@Produces("text/html")
	public void ajouteMusique(@PathParam("idUtilisateur") long idUtilisateur, @PathParam("idMusique") long idMusique) {
		this.metierUtilisateur.ajouteMusique(idUtilisateur,idMusique);
	}
}
