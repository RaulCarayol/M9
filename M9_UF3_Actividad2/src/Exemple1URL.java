import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Exemple1URL {

	public static void main (String[] args) throws MalformedURLException {
		URL url = null;
		if(args.length==0){
			url = new URL("http://www.insbaixcamp.cat/moodle/");
		}else if(args.length==1){
			url= new URL(args[0]);
		}else if(args.length==2){
			String strurl =args[0];
			int port = Integer.valueOf(args[1]);

			url = new URL(strurl);
			url = new URL(url.getProtocol(),url.getHost(),port,url.getFile());
		}

		Visualitzar (url);
		Codigo(url);

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


	private static void Codigo(URL url) {
		BufferedReader in;

		try {

			InputStream inputStream = url.openStream();
			in = new BufferedReader(new InputStreamReader(inputStream));
			String inputLine;

			while ((inputLine = in.readLine()) != null)
				System.out.println(inputLine);
			in.close();

		} catch (IOException e) {e.printStackTrace(); }
	}

}