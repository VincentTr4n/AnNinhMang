package RSA;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.List;

import javax.crypto.Cipher;

public class RSACrypt {
	
	public PublicKey publicKey;
	public PrivateKey privateKey;
	
	public void LoadPublicKey(String fileName) throws Exception {
		byte tmp[] = Files.readAllBytes(Paths.get(fileName));
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(tmp);
		KeyFactory factory = KeyFactory.getInstance("RSA");
		publicKey = factory.generatePublic(keySpec);
	}
	
	public void LoadPrivateKey(String fileName) throws Exception {
		byte tmp[] = Files.readAllBytes(Paths.get(fileName));
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(tmp);
		KeyFactory factory = KeyFactory.getInstance("RSA");
		privateKey = factory.generatePrivate(keySpec);
	}
	
	public String encrypt(String data) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
	}
	
	public String decrypt(String data) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		byte out[] = cipher.doFinal(Base64.getDecoder().decode(data.getBytes()));
		return new String(out);
	}
	
	public void SaveEncrypt(String data, String fileName) throws Exception {
		String encryted = encrypt(data);
		String hashCode = HashAlgo.HashImplement.Hash(data, "MD5");
		PrintWriter print = new PrintWriter(fileName);
		print.println(encryted);
		print.println(hashCode);
		print.close();
	}
	
	public String ReadAndDecryt(String fileName) throws Exception {
		String res = "";
		List<String> lines = Files.readAllLines(Paths.get(fileName));
		String encryted = lines.get(0);
		String hash = lines.get(1);
		String decryted = decrypt(encryted);
		String tmp = HashAlgo.HashImplement.Hash(decryted, "MD5");
		if(hash.equals(tmp)) return decryted;
		return res;
	}
	
	public static void main(String[] args) throws Exception {
		RSACrypt crypt = new RSACrypt();
		crypt.LoadPublicKey(RSAKeyGen.publicKeyFile);
		crypt.LoadPrivateKey(RSAKeyGen.privateKeyFile);
		
		String fileName = "data.txt";
		String data = "vincent-tran";
//		crypt.SaveEncrypt(data, fileName);
		
		String out = crypt.ReadAndDecryt(fileName);
		System.out.println(out);
	}
}
