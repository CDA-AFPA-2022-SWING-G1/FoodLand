package outils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationPatternsRegex {

	private String message;
	private final Pattern pat_email = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"); 
	private final Pattern pat_siret = Pattern.compile("");
	private final Pattern pat_password = Pattern.compile("");
	
	public ValidationPatternsRegex() {}
	
	public boolean matchEmail(String s) {
		
        Matcher matcher = pat_email.matcher(s);
        if (!matcher.matches()) {
        	message = "format email incorrect";
        	setMessage(message);
            // retourne un message
        }
        return matcher.matches();
	}
	
	public boolean matchSiret(String s) {
		
        Matcher matcher = pat_siret.matcher(s);
        if (!matcher.matches()) {
        	message = "format siret incorrect";
        	setMessage(message);
            // retourne un message
        }
        return matcher.matches();
	}
	
	public boolean matchPassword(String s) {
		
        Matcher matcher = pat_siret.matcher(s);
        if (!matcher.matches()) {
        	message = "format siret incorrect";
            // retourne un message
        }
        return matcher.matches();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}

