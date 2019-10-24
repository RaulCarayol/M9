

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class M9_UF1_Actividad8 {

	public static void main(String[] args) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableKeyException {

		FileInputStream is = new FileInputStream("asimetrica.jceks");

		KeyStore keystore = KeyStore.getInstance("jceks");
		keystore.load(is, "soyprogramador".toCharArray());

		String alias = "Raul";

		Key key = keystore.getKey(alias, "soyprogramador".toCharArray());
		if (key instanceof PrivateKey) {
			// Get certificate of public key
			Certificate cert = keystore.getCertificate(alias);

			// Get public key
			PublicKey publicKey = cert.getPublicKey();
			System.out.println(cert.toString());

			// Return a key pair
			KeyPair par=new KeyPair(publicKey, (PrivateKey) key);
			imprimirFichero(par);
		}

	}

	// Imprime la clave pública y privada generada en sus respectivos archivos
	public static void imprimirFichero(KeyPair keyPair) throws IOException {

		PrivateKey privateKey = keyPair.getPrivate();
		PublicKey publicKey = keyPair.getPublic();

		// Store Public Key.
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
		FileOutputStream fos = new FileOutputStream("clauPublica");
		fos.write(x509EncodedKeySpec.getEncoded());
		fos.close();

		// Store Private Key.
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
		fos = new FileOutputStream("clauPRIVADA");
		fos.write(pkcs8EncodedKeySpec.getEncoded());
		fos.close();
	}
}
