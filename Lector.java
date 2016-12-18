import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lector {

	Formato aux = new Formato();
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

					this.aux.setRfc(obtenRfc(linea));
					this.aux.setNumEmpleado(obtenNumEmpleado(linea));
					
				} else if ( linea.contains("C.") || linea.contains("C,") ) {//buscar nombre

					this.aux.setNombre(obtenNombre(linea));

				} else if (linea.contains("\"\"")) {//buscar feccha y terminar

					this.aux.setFecha(obtenFecha(linea));

					//añadimos a array aparte
					this.documentosCSV.add(this.aux);
					//limpiamos el objeto
					this.aux.limpia();

				}else{//busca RFC
					
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

		return "";

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