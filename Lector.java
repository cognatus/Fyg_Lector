import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

public class Lector {

	public static void main(String[] args) {

		try{

			List<String> lineas = Files.readAllLines(Paths.get("tabula-SimpleIndex_Prueba2.csv"));
			ArrayList<String> documentosCSV = new ArrayList<String>();
			String aux = "";

			for (int i=0; i < lineas.size(); i++) {

				//separa los distintos documentosCSV
				if (lineas.get(i).charAt(0) == '"' && lineas.get(i).charAt(1) == '"' && i != 0) {
					
					documentosCSV.add(aux);
					aux = "";
				}

				aux += lineas.get(i);

			}

			for (String line : documentosCSV) {
				System.out.println(line);
			}

		}catch(Exception ex){

			System.out.println("Error papu "+ex);

		}
	}

}