import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lector {

	Formato datosObtenidos = new Formato();
	ArrayList<Formato> documentosCSV = new ArrayList<Formato>();
	//expresion regular para RFC
	Pattern p = Pattern.compile("[A-Z]{4}[0-9]{6}[A-Z0-9]{3}");


	public static void main(String[] args) {

		new Lector().inicia();
		
	}


	//metodo para iniciar el proyecto
	public void inicia(){

		try{

			List<String> lineas = Files.readAllLines(Paths.get("tabula-SimpleIndex_Prueba2.csv"));

			for (int i = lineas.size() - 1; i >= 0; i--) {

				String linea = lineas.get(i);
				
				//buscar numero de empleado y rfc, ya que en muchas ocasiones vienen juntos
				if ( linea.contains("numero de empleado") ) {

					this.datosObtenidos.setRfc(obtenRfc(linea));
					this.datosObtenidos.setNumEmpleado(obtenNumEmpleado(linea));
					
				} else if ( linea.contains("C.") || linea.contains("C,") ) {//buscar nombre

					this.datosObtenidos.setNombre(obtenNombre(linea));

				} else if (linea.contains("\"\"")) {//buscar feccha y terminar

					this.datosObtenidos.setFecha(obtenFecha(linea));

					//añadimos a array aparte
					this.documentosCSV.add(this.datosObtenidos);
					//limpiamos el objeto
					this.datosObtenidos.limpia();

				}else{//busca RFC
					
					this.datosObtenidos.setRfc(obtenRfc(linea));

				}
			}

			/*for (Formato obj : this.datosObtenidos) {

				System.out.println(obj.getNombre());
				System.out.println(obj.getFecha());
				System.out.println(obj.getRfc());
				System.out.println(obj.getNumEmpleado());
				System.out.println("\n");
				
			}*/

		}catch(Exception ex){

			System.out.println("Error papu "+ex);

		}

	}


	/*
	 *	Metodos para obtener los fomatos
	*/

	/*
		Descripcion de obtencion de formatos

		Para el RFC:
		quitar espacios para RFC ir tomando numero de letras rfc e ir validando 

		Para numero de empleado:
		numero de empleado a detectar con la misma oracion, quitar espacios y comas, se seguira añadiendo conforme siga siendo numero

		Para nombre completo:
		nombre hacer split de "C." o "C,", luego hacer split de "con" o "c o n", quitar comillas, donde haya comas, poner doble espacio, hacer validacion que detecte que no pueda haber letras solas y si las hay que las junte solo si estan separadas por un espacio y lo mismo para aquellas que empiezan con minuscula, los dobles espacios cambiarlos a espacios sencillos, quitar espacio de un principio y final

		Para la fecha:
		detectar "" de fecha, quitar todas las comillas, sustituir comas por espacios, quitar espacios de un principio y final
	*/

	public String obtenNombre(String linea){

		linea = linea.contains("C.") ? linea.split("C\\.")[1] : linea.split("C,")[1];
		linea = linea.contains("con") ? linea.split("con")[0] : linea.split("c o n")[0];
		linea = linea.replaceAll("\"","");
		linea = linea.replaceAll(",","  ");

		Pattern p2 = Pattern.compile("[a-z]");
		Matcher m = p2.matcher(linea);

		//variable auxiliar para guardar palabra arreglada
		String cambio = "";
		//bandera para que no se guarde la misma letra dos veces en caso de que que se cumpla lo de abajo
		boolean bandera = true;

		if (m.find()) {//acomodo si si hay minusculas 

			//verificamos la cadena completa
			for (int i = 0; i < linea.length()-1; i++) {
				
				Matcher m2 = p2.matcher(""+linea.charAt(i+1));

				//verificamos estar parados sobre un espacio y que el siguiente sea letra minuscula
				//esto para saber que el nombre esta separado
				if ( m2.find() && linea.charAt(i) == ' ') {
					//añadimos solo la letra y descartamos el espacio
					cambio += linea.charAt(i+1);
					//apagamos el switch para que la siguiente vez que se repita el bucle no se agregue
					bandera = false;
				}else{
					//veirifca si es switch esta prendido
					if (bandera) {
						cambio += linea.charAt(i);	
					}else{
						//en caso de estar apagado solo lo prende
						bandera = true;
					}
					
				}

			}
			linea = cambio;
		}else{

			//verificamos la cadena completa
			for (int i = 0; i < linea.length()-2; i++) {
				
				//verificamos estar parados sobre un espacio, que el siguiente sea letra y el siguiente a ese un espacio
				//esto para saber que el nombre esta separado
				if ( linea.charAt(i+2) == ' ' && linea.charAt(i) == ' ' && Character.isLetter(linea.charAt(i+1)) ) {
					//añadimos solo la letra y descartamos el espacio donde estamos parados
					cambio += linea.charAt(i+1);
					//apagamos el switch para que la siguiente vez que se repita el bucle no se agregue
					bandera = false;
				}else{
					//veirifca si es switch esta prendido
					if (bandera) {
						cambio += linea.charAt(i);	
					}else{
						//en caso de estar apagado solo lo prende
						bandera = true;
					}
					
				}

			}

			linea = cambio;
		}	

		linea = linea.trim();
		linea = linea.replaceAll("  "," ");

		return linea;

	}

	public String obtenNumEmpleado(String linea){

		String retorno = "";
		linea = linea.split("numero de empleado")[1];
		linea = linea.replaceAll("\\s+","");
		linea = linea.replaceAll(",","");

		for (int i = 0; i < linea.length(); i++) {
			if (Character.isDigit(linea.charAt(i))) {
				retorno += linea.charAt(i);
			}else{
				break;
			}
		}

		return retorno;

	}

	public String obtenRfc(String linea){

		linea = linea.replaceAll("\\s+","");
		String retorno = "";

		for ( int i = 0; i < linea.length(); i++ ) {

			try{

				String buscadorAux = linea.substring(i, i+13);

				Matcher m = p.matcher(buscadorAux);
				if (m.find()) {
					retorno = buscadorAux;
				}

			}catch(Exception ex){
			}

		}

		return retorno;

	}

	public String obtenFecha(String linea){

		linea = linea.replaceAll("\"","");
		linea = linea.replaceAll(","," ");
		linea = linea.trim();

		return linea;

	}

}