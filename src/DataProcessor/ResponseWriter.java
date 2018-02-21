package DataProcessor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ResponseWriter {
	
	
	
	private static ResponseWriter instance=null;
	private static String file_Name="data.json";
	private static boolean is_Created=false;
	private static boolean is_Open=false;
	private static File	file=null;
	private static FileWriter fileWriter=null;
	private static BufferedWriter buffered_Writer_Internal=null;
	private static boolean firstLine=true;
	
	
	
	private ResponseWriter() {
	}
	
	
	public void reset_Response_Writer(String newFileName) {
		file_Name=newFileName;
		is_Created=false;
		is_Open=false;
		file=null;
		fileWriter=null;
		buffered_Writer_Internal=null;
		firstLine=true;
	}
	
	
	
	public static ResponseWriter getInstance() {
		if(instance==null) {
			try {
			instance=new ResponseWriter();
			}
			catch (NullPointerException e) {
				instance=null;
				System.out.println("Couldn't create ResponseWriter");
			}	
		}
		
		return instance;
	}

	
	
	
	
	public FileWriter create_File() {

		//Create the file
		file= new File(file_Name);
		try {
			fileWriter= new FileWriter(file,true);
		} catch (IOException e) {
			System.out.println("Couldn't create the file");
			return null;
		}
		
		if(fileWriter!=null) {
			is_Created=true;
		}
		return fileWriter;
	}
	
	private BufferedWriter get_Buffered_Writer() {
		
		if(is_Open)
			return buffered_Writer_Internal;
		
		if(is_Created) {
			buffered_Writer_Internal=new BufferedWriter(fileWriter);
			if(buffered_Writer_Internal!=null)
				is_Open=true;
			return buffered_Writer_Internal;
		}
		
		FileWriter fileWriter= create_File();
		if(fileWriter!=null)
			return new BufferedWriter(fileWriter);
		
		return null;
	}
	
	
	
	
	public boolean write_Response_To_File(String response, boolean lastLine) {
		if(response==null || response.isEmpty())
			return false;
		
		BufferedWriter buffered_Writer=null;
		if(!is_Open)
			buffered_Writer=get_Buffered_Writer();
		
		if(buffered_Writer==null)
			return false;
		
		try {
			if(firstLine) {
				buffered_Writer.write("[\n");
				firstLine=false;
			}
			buffered_Writer.write(response);
			if(lastLine)
				buffered_Writer.write("]\n");
		} catch (IOException e) {
			System.out.println("Couldn't write inside the buffered_Writer");
			return false;
		}
		return save_And_Close_File(buffered_Writer);
	}
	
	
	
	public boolean save_And_Close_File(BufferedWriter buffered_Writer) {
		
		if(buffered_Writer!=null)
			try {
				buffered_Writer.close();
				is_Open=false;
			} catch (IOException e) {
				System.out.println("Couldn't close the buffered_Writer");
				return false;
			}
		
		if(fileWriter!=null)
			try {
				fileWriter.close();
				is_Created=false;
			} catch (IOException e) {
				System.out.println("Couldn't close the fileWriter");
				return false;
			}
			
		return true;
	}
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
