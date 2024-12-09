package examen;

import java.io.File;
import java.io.FileWriter;

public class practica {
	
	public static void escribirArchivo(String archivo){
		File archivoW = new File(archivo);
		String mensaje = "Hola a todos";
		String archivoE = archivoW.getAbsolutePath();
		
		try {
			FileWriter escribir = new FileWriter(archivoW);
			
			for (int i=0; i<mensaje.length();i++) {
				escribir.write(mensaje.charAt(i));
			}
			escribir.close();
		}catch(Exception e) {
			System.out.println("No se ha encontrado el archivo");
		}
	}
	
	public static void crearCarpeta(String carpeta) {
		File carpetaC = new File(carpeta);
		carpetaC.mkdir();
	}
	
	public static void crearArchivo(String archivo){
		File archivoC = new File(archivo);
		try {
			archivoC.createNewFile();
		}catch(Exception e){
			System.out.println("No se ha podido crear el archivo");
		}
				
	}
	
	public static void main(String[] args) {
		
		String carpeta = "YoutubeHack";
		String archivo = "YoutubeHack/inicio.txt";
		crearCarpeta(carpeta);
		crearArchivo(archivo);
		escribirArchivo(archivo);
	}
}
