package co.yeadam.project.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {
	public String encrypt(String text) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(text.getBytes());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bytesToHex(md.digest());
	}

	private String bytesToHex(byte[] digest) {
		StringBuilder builder = new StringBuilder();
		for(byte b : digest) {
			builder.append(String.format("%2x", b));
		}
		return builder.toString();
	}
}
