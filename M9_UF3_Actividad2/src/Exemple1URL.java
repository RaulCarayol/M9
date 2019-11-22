import java.net.*;

public class Exemple1URL {
	
	public static void main (String[] args) {
		URL url;
		
		try {
			
			System.out.println("Constructor simple per a un URL: ");
			url = new URL("http://docs.oracle.com/");
			Visualitzar (url);
			
			System.out.println("Altra constructor simple per a un URL: ");
			url = new URL("http://localhost/moodle/");
			Visualitzar (url);
			
			System.out.println("Constructor per a protocol + URL + directori: ");
			url = new URL("http", "doc.oracle.com", "/javase/7");
			Visualitzar (url);
			
			System.out.println("Constructor per a protocol + URL + port + directori: ");
			url = new URL("http", "doc.oracle.com", 80, "/javase/7");
			Visualitzar (url);
			
			System.out.println("Constructor per a un objecte URL i un directori: ");
			URL urlBase = new URL("http://docs.oracle.com/");
			url = new URL(urlBase, "/javase/7/docs/api/java/net/URL.html");
			Visualitzar (url);
			
		} catch (MalformedURLException e) { System.out.println(e); }
		
	}
	
	private static void Visualitzar(URL url) {
		
		System.out.println("\tURL complerta: "+url.toString());
		System.out.println("\tgetProtocol: "+url.getProtocol());
		System.out.println("\tgetHost: "+url.getHost());
		System.out.println("\tgetPort: "+url.getPort());
		System.out.println("\tgetFile: "+url.getFile());
		System.out.println("\tgetUserInfo: "+url.getUserInfo());
		System.out.println("\tgetPath: "+url.getPath());
		System.out.println("\tgetAuthority: "+url.getAuthority());
		System.out.println("\tgetQuery: "+url.getQuery());
		System.out.println("=====================================================");
	}

}