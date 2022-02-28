package outils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationPatternsRegex {

	private String message;
	private final Pattern pat_email = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"); 
	private final Pattern pat_siret = Pattern.compile("");
	private final Pattern pat_password = Pattern.compile("");
	private final Pattern pat_onlyNumeric = Pattern.compile("([0-9])\\w+"); //  ^[0-9]*$    ([0-9])\w+
	
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
		
        Matcher matcher = pat_password.matcher(s);
        if (!matcher.matches()) {
        	message = "format password incorrect";
            // retourne un message
        }
        return matcher.matches();
	}

	public boolean matchNumeric(HashMap<Integer, Integer> listeNums, int length) {
		
		boolean b = false;
		
		for(Integer num :  listeNums.values()) {
        Matcher matcher = pat_onlyNumeric.matcher(String.valueOf(num));
        if (!matcher.matches()) {
        	message = "format numérique incorrect, trop long ou trop court";
            //retourne un message
        	b = matcher.matches();
        }  
		}
        return b;	
	}
	
	public boolean matchNumeric(String num, int length) {
		boolean b = false;
        Matcher matcher = pat_onlyNumeric.matcher(String.valueOf(num));
        if (!matcher.matches() && num.length() != length) {
        	message = "format numérique incorrect, trop long ou trop court";
            //retourne un message
        	b = matcher.matches();
		}else {
			b = true;
		}
        return b;	
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}

