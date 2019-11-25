package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ReadWrite {
	
	private static final String FILE_PATH = "C:/Users/Artur/git/BD/BD/src/txts/";
	
	public static List<String> readFile(String file_name) throws IOException{
		File file = new File(FILE_PATH+file_name+".txt");
		
		if(file.exists()){
			List<String> lista = new ArrayList<>();
			
			try(BufferedReader reader = new BufferedReader(new FileReader(file))){
				String line;
				
				while((line = reader.readLine()) != null){
					line = line.replace("<", "&#60;");
					lista.add(line);
				}
				return lista;
			}
		}
		else
			return null;
	}
	
	public static void writeFile (String comando, String file_name){
		File file = new File(FILE_PATH+file_name+".txt");
		
		if(file.exists()){
			
			try(BufferedWriter writer = new BufferedWriter(new FileWriter(file,true))){
				writer.write(comando);
				writer.newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void deleteData (String file_name){
		
		File file = new File(FILE_PATH+file_name+".txt");
		
		try(PrintWriter writer = new PrintWriter(new FileWriter(file))){
			//writer.print("");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void copyData (String file_name_origin, String file_name_target) throws IOException{
		File file_origin = new File(FILE_PATH+file_name_origin+".txt");
		//File file_target = new File(FILE_PATH+file_name_target+".txt");
		
		if(file_origin.exists()){
			List<String> lista = new ArrayList<>();
			
			try(BufferedReader reader = new BufferedReader(new FileReader(file_origin))){
				String line;
				
				while((line = reader.readLine()) != null){
					ReadWrite.writeFile(line,file_name_target);
				}
			}
		}
	}
}
