import java.net.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class M9_UF3_Actividad3 {
	@SuppressWarnings("rawtypes")
	public static void main (String[] args) throws MalformedURLException {
		//El primer argumento es la url
		URL url= new URL(args[0]);
		//el segundo argumento es el numero de cap�alera
		int numCapsalera = Integer.parseInt(args[1]);
		//el tercer y ultimo argumento es el patr�n
		String patron = args[2];

		try {
			String cadena;
			URLConnection connexio = url.openConnection();
			System.out.println("===============================================================");
			System.out.println("ADRE�A, DARA I CONTINGUT");
			System.out.println("Adre�a [getURL]: " + connexio.getURL());

			Date data = new Date(connexio.getLastModified());
			System.out.println("Data �ltima modificaci� [getLastModified()]: " + data);
			System.out.println("Tipus de Contingut [getContentType()]" + connexio.getContentType());

			System.out.println("===============================================================");
			System.out.println("TOTS ELS CAMPS DE CAP�ALERA AMB getHeaderFields(): ");

			//Fem servir una estructura Map per a recuperar cap�aleres
			Map campsCap�alera = connexio.getHeaderFields();
			Iterator it = campsCap�alera.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry map = (Map.Entry) it.next();
				System.out.println(map.getKey() + ":" + map.getValue());
			}
			System.out.println("===============================================================");
			System.out.println("Camps de 0 a "+numCapsalera+" de Cap�alera");
			for (int i = 0; i < numCapsalera; i++) {
				System.out.println("getHeaderField("+i+")=> " + connexio.getHeaderField(i));
			}
			System.out.println("===============================================================");
			System.out.println("Contingut de [url.getFile()]: " + url.getFile());
			BufferedReader pagina = new BufferedReader(new InputStreamReader(url.openStream()));
			//lee el codigo mietras no sea null
			while ((cadena = pagina.readLine()) != null) {
				//mira si la linia contiene el patron	
				if(cadena.contains(patron)){
					//imprime por pantalla la linia
					System.out.println(cadena);
				}	
			}
		}
		catch (MalformedURLException e) { e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
	}





}