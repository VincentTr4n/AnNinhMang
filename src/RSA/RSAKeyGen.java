package RSA;

import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class RSAKeyGen {
	
	public static String publicKeyFile = "public.txt";
	public static String privateKeyFile = "private.txt";
	
	public static KeyPair genKeyPair() throws Exception{
		int keySize = 2048;
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(keySize);
		return generator.genKeyPair();
	}
	
	public static void saveFile(KeyPair keyPair) throws Exception {
		FileOutputStream outPublicKey = new FileOutputStream(publicKeyFile);
		FileOutputStream outPrivateKey = new FileOutputStream(privateKeyFile);
		
		outPublicKey.write(keyPair.getPublic().getEncoded());
		outPrivateKey.write(keyPair.getPrivate().getEncoded());
		
		outPrivateKey.close();
		outPublicKey.close();
	}
	
	public static void main(String[] args) throws Exception {
//		KeyPair keyPair = genKeyPair();
//		PublicKey key = keyPair.getPublic();
//		System.out.println(key.toString());
//		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(key.getEncoded());
//		KeyFactory factory = KeyFactory.getInstance("RSA");
//		PublicKey tmp = factory.generatePublic(keySpec);
//		System.out.println(tmp.toString());
	}
}
