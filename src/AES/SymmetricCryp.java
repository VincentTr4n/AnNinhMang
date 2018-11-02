package AES;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public class SymmetricCryp {
	
	public static String s = "abg236qac926jcd1";
	public static Cipher cipherEn;
	public static Cipher cipherDe;
	
	public static void init() throws Exception {
		Key key = generateKey();
		cipherEn = Cipher.getInstance("AES");
		cipherEn.init(Cipher.ENCRYPT_MODE, key);
		cipherDe = Cipher.getInstance("AES");
		cipherDe.init(Cipher.DECRYPT_MODE, key);
	}
	
	public static void init(String data) throws Exception {
		Key key = generateKey(data);
		cipherEn = Cipher.getInstance("AES");
		cipherEn.init(Cipher.ENCRYPT_MODE, key);
		cipherDe = Cipher.getInstance("AES");
		cipherDe.init(Cipher.DECRYPT_MODE, key);
	}
	
	public static String encrypt(String data) throws Exception {
		byte[] value = cipherEn.doFinal(data.getBytes("UTF8"));
		return Base64.getEncoder().encodeToString(value);
	}
	
	public static Key generateKey() throws Exception {
		Key key = KeyGenerator.getInstance("AES").generateKey();
		return key;
	}
	
	public static Key generateKey(String s) throws Exception {
		Key key = new SecretKeySpec(s.getBytes("UTF8"), "AES");
		return key;
	}
	
	public static String decrypt(String data) throws Exception {
		byte[] value = Base64.getDecoder().decode(data);
		value = cipherDe.doFinal(value);
		return new String(value, "UTF8");
	}
	
	public static void main(String[] args) throws Exception {
		String data = "vincent-tran";
		init(s);
		String en = encrypt(data);
		System.out.println(en);
		String de = decrypt(en);
		System.out.println(de);
	}
}
