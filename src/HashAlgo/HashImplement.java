package HashAlgo;

import java.security.MessageDigest;

public class HashImplement {
	
	public static String Hash(String message, String algo) throws Exception {
		MessageDigest md = MessageDigest.getInstance(algo);
		md.update(message.getBytes());
		byte out[] = md.digest();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < out.length; ++i) sb.append(String.format("%02x", out[i]));
		return sb.toString();
//		return DatatypeConverter.printHexBinary(out);
	}
	
	
	
	public static void main(String[] args) throws Exception {
		System.out.println(Hash("vincent-tran", "SHA-256"));
	}
}
