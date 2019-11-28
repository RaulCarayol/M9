import java.net.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class M9_UF3_Actividad3 {

	@SuppressWarnings("rawtypes")
	public static void main (String[] args) throws MalformedURLException {
		URL url= new URL(args[0]);
		int numCapsalera = Integer.parseInt(args[1]);
		String patron = args[2];

		try {
			String cadena;
			URLConnection connexio = url.openConnection();
			System.out.println("===============================================================");
			System.out.println("ADREÇA, DARA I CONTINGUT");
			System.out.println("Adreça [getURL]: " + connexio.getURL());

			Date data = new Date(connexio.getLastModified());
			System.out.println("Data última modificació [getLastModified()]: " + data);
			System.out.println("Tipus de Contingut [getContentType()]" + connexio.getContentType());

			System.out.println("===============================================================");
			System.out.println("TOTS ELS CAMPS DE CAPÇALERA AMB getHeaderFields(): ");

			//Fem servir una estructura Map per a recuperar capçaleres
			Map campsCapçalera = connexio.getHeaderFields();
			Iterator it = campsCapçalera.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry map = (Map.Entry) it.next();
				System.out.println(map.getKey() + ":" + map.getValue());
			}
			System.out.println("===============================================================");
			System.out.println("Camps de 0 a "+numCapsalera+" de Capçalera");
			for (int i = 0; i < numCapsalera; i++) {
				System.out.println("getHeaderField("+i+")=> " + connexio.getHeaderField(i));
			}
			System.out.println("===============================================================");
			System.out.println("Contingut de [url.getFile()]: " + url.getFile());
			BufferedReader pagina = new BufferedReader(new InputStreamReader(url.openStream()));

			while ((cadena = pagina.readLine()) != null) {
					if(cadena.contains(patron)){
					System.out.println(cadena);
					}	
			}
		}
		catch (MalformedURLException e) { e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
	}





}