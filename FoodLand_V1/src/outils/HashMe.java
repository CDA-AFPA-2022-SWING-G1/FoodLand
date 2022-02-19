package outils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.*;

import javax.swing.JFrame;
//import javax.xml.bind.*;

public class HashMe {

	private final String sel = "SBSHSESWFBvbcVcXsQZQaIUOLdzaa";
	
	/**
	 * Hashing with SHA1
	 *
	 * @param input String to hash
	 * @return String hashed
	 */
	public String sha1(String input) {
		
	    String sha1 = input + sel;
	    
	    try {
	        MessageDigest msdDigest = MessageDigest.getInstance("SHA-1");
	        				msdDigest = MessageDigest.getInstance("MD5");
	        msdDigest.update(input.getBytes("UTF-8"), 0, input.length());	        
	        		//sha1 = DatatypeConverter.printHexBinary(msdDigest.digest());
	        //sha1 = new String(msdDigest.digest(), StandardCharsets.UTF_8);
	        sha1 = Base64.getEncoder().encodeToString(msdDigest.digest());
	        System.out.println(sha1);
	        
	        
	    } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
	        Logger.getLogger(HashMe.class.getName()).log(Level.SEVERE, null, e);
	    }
	    return sha1;
	}
}
