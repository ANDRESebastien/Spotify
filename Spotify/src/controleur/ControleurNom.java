package controleur;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator
public class ControleurNom implements Validator {

	public void validate(FacesContext contexte, UIComponent composant, Object oNom) throws ValidatorException {
		String nom = (String) oNom;

		if (nom.length() < 2) {
			throw new ValidatorException(
					new FacesMessage(" Le nom utilisateur doit contenir au moins deux caractères."));
		}
	}
}