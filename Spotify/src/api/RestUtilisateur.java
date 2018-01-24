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

import accesseur.AccesseurUtilisateur;
import table.TableUtilisateur;

@Path("/utilisateur")
public class RestUtilisateur {

	@EJB
	private AccesseurUtilisateur accesseurUtilisateur;

	@GET
	@Path("/{idUtilisateur}")
	@Produces("application/json")
	public TableUtilisateur recherche(@PathParam("idUtilisateur") long idUtilisateur) {
		return this.accesseurUtilisateur.select(idUtilisateur);
	}

	@GET
	@Path("/")
	@Produces("application/json")
	public List<TableUtilisateur> listeUtilisateur() {
		return this.accesseurUtilisateur.liste();
	}

	@POST
	@Path("/{nom}/{email}/{motDePasse}")
	@Produces("text/html")
	public String ajoute(@PathParam("nom") String nom, @PathParam("email") String email,
			@PathParam("motDePasse") String motDePasse) {
		TableUtilisateur tableUtilisateur = new TableUtilisateur();
		tableUtilisateur.setNom(nom);
		tableUtilisateur.setEmail(email);
		tableUtilisateur.setMotDePasse(motDePasse);
		this.accesseurUtilisateur.insert(tableUtilisateur);
		return "Ok";
	}

	@POST
	@Path("/ajoute")
	@Consumes(MediaType.APPLICATION_JSON)
	public String ajoute(TableUtilisateur tableUtilisateur) {
		this.accesseurUtilisateur.insert(tableUtilisateur);
		return "Ok";
	}

	@POST
	@Path("/supprime/{idUtilisateur}")
	@Produces("text/html")
	public String supprime(@PathParam("idUtilisateur") long idUtilisateur) {
		return this.accesseurUtilisateur.delete(idUtilisateur);
	}

	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public String update(TableUtilisateur TableUtilisateur) {
		TableUtilisateur resultatUpdate = this.accesseurUtilisateur.update(TableUtilisateur);

		if (resultatUpdate != null) {
			return "Ok";
		} else {
			return "Ko";
		}
	}

	@PUT
	@Path("/{idUtilisateur}/{nom}/{email}/{motDePasse}")
	@Produces("text/html")
	public String modifie(@PathParam("idUtilisateur") long idUtilisateur, @PathParam("nom") String nom,
			@PathParam("email") String email, @PathParam("motDePasse") String motDePasse) {

		System.out.println("RestUtilisateur:modifie:" + idUtilisateur);
		TableUtilisateur tableUtilisateur = this.recherche(idUtilisateur);
		tableUtilisateur.setNom(nom);
		tableUtilisateur.setEmail(email);
		tableUtilisateur.setMotDePasse(motDePasse);
		TableUtilisateur resultatUpdate = this.accesseurUtilisateur.update(tableUtilisateur);

		if (resultatUpdate != null) {
			return "Ok";
		} else {
			return "Ko";
		}
	}

}
