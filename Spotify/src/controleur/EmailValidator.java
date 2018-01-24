package controleur;

import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator
public class EmailValidator implements Validator {

	public void validate(FacesContext contexte, UIComponent composant, Object oEmail) throws ValidatorException {
		String email = (String) oEmail;

		if (email.length() < 2) {
			throw new ValidatorException(new FacesMessage(" L'email doit contenir au moins deux caractères."));
		} else {
			if(!Pattern.matches("^[a-zA-Z0-9_.-]{2,}@[a-zA-Z0-9_.-]{2,}\\.[a-zA-Z0-9_.-]{2,}$", email)) {
				throw new ValidatorException(new FacesMessage(" L'email n'est pas un email valide."));
			}
		}
	}
}