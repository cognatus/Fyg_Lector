import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

public class Lector {

	Formato aux = new Formato();
	ArrayList<Formato> documentosCSV = new ArrayList<Formato>();
	//expresion regular para RFC con homoclave opcional incluida con guion
	Pattern p = Pattern.compile("^([A-ZÑ\x26]{3,4}([0-9]{2})(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1]))((-)?([A-Z\d]{3}))?$");


	public static void main(String[] args) {

		new Lector().inicia();
		
	}


	//metodo para iniciar el proyecto
	public void inicia(){

		try{

			List<String> lineas = Files.readAllLines(Paths.get("tabula-SimpleIndex_Prueba2.csv"));
			Matcher m = p.matcher(linea);

			for (int i = lineas.size() - 1; i >= 0; i--) {

				String linea = lineas.get(i);
				
				if ( linea.contains("numero de empleado") ) {//buscar numero de empleado

					this.aux.setNumEmpleado(obtenNumEmpleado(linea));
			
					
				} else if ( linea.contains("C.") || linea.contains("C,") ) {//buscar nombre
					
					this.aux.setNombre(obtenNombre(linea));

				} else if (linea.contains("\"\"")) {//buscar feccha y terminar
					
					this.aux.setFecha(obtenFecha(linea));

					//añadimos a array aparte
					this.documentosCSV.add(this.aux);
					//limpiamos el objeto
					this.aux.limpia();

				}else if (m.find()) {//busca RFC
					
					this.aux.setRfc(obtenRfc(linea));

				}

			}

		}catch(Exception ex){

			System.out.println("Error papu "+ex);

		}

	}


	/*
	 *	Metodos para obtener los fomatos
	*/

	public String obtenNombre(String linea){

		return "";

	}

	public String obtenNumEmpleado(String linea){

		return "";

	}

	public String obtenRfc(String linea){


		linea		
		for (int i = 0; i < linea.length(); i++ ) {

			Matcher m = p.matcher(linea.substring(i, ));

		}

		

		return "";

	}

	public String obtenFecha(String linea){

		return "";

	}

}