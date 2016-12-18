import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

public class Lector {

	List<String> lineas = Files.readAllLines(Paths.get("tabula-SimpleIndex_Prueba2.csv"));
	Formato aux = new Formato();
	ArrayList<String> documentosCSV = new ArrayList<String>();

	public static void main(String[] args) {

		try{

			


			for (int i = lineas.size - 1; i >= 0; i--) {

				linea = lineas.get(i);
				
				if ( linea.contains("numero de empleado") ) {//buscar numero de empleado

					aux.setNumEmpleado(obtenNumEmpleado(linea));
					
				}else if ( linea.contains("C.") || linea.contains("C,") ) {//buscar nombre
					
					aux.setRfc(obtenNumEmpleado(linea));

				} else{//buscar rfc



				}

				

				

				//buscar fecha
				if (lineas.get(i).charAt(0) == '"' && lineas.get(i).charAt(1) == '"') {

					documentosCSV.add(aux);
					System.out.println(aux);
					aux = "";
				}else {
					aux += lineas.get(i)+"\n";
				}
			}

			for (int i=0; i < lineas.size(); i++) {

				

				//separa los distintos documentosCSV
				if (lineas.get(i).charAt(0) == '"' && lineas.get(i).charAt(1) == '"') {

					documentosCSV.add(aux);
					System.out.println(aux);
					aux = "";
				}else {
					aux += lineas.get(i)+"\n";
				}

				

			}

			/*int aux2 = 0;
			for (String documento : lineas) {

				//System.out.println(documento);

				//ArrayList<String> auxArray = ()documento.split("\n");

				//System.out.println(documento);

				for (String linea : documento.split("\n")) {
					System.out.println(linea);
					aux2++;
				}

				System.out.println(aux2);
			}*/

		}catch(Exception ex){

			System.out.println("Error papu "+ex);

		}
	}

	public String obtenNumEmpleado(String linea){

		return "";

	}

}