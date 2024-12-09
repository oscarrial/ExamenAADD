package examen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterTest {

	public static void main(String[] args) throws IOException {
		
		//crear archivo
		String nombre_archivo = "test.txt";
		File archivo = new File(nombre_archivo);
		
		//escribir en archivo
		FileWriter escribir = new FileWriter(archivo);
		
		escribir.write("Holas 2");
		
		escribir.close();
		
		//Con buffered
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("archivo.txt"));
		bufferedWriter.write("Hola, mundo!");
		bufferedWriter.write("Hola, mundasdo!");
		bufferedWriter.write("Hola, mudaddo!");
		bufferedWriter.close();

		
		// leer archivo
		FileReader leer = new FileReader("archivo.txt");
		BufferedReader leer2 = new BufferedReader(leer);
		
		String linea;
		
		while((linea = leer2.readLine())!=null) {
			System.out.println(linea);
		}
		
		leer2.close();
	}
}
