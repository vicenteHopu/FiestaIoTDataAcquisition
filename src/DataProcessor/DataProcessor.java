package DataProcessor;

import com.sun.jna.platform.unix.X11.XClientMessageEvent.Data;

public class DataProcessor {

	
	private static DataProcessor instance=null;
	
	
	
	private DataProcessor() {
	}
	
	
	
	public static DataProcessor getInstance() {
		if(instance==null) {
			try {
			instance=new DataProcessor();
			}
			catch (NullPointerException e) {
				instance=null;
				System.out.println("Couldn't create DataProcessor");
			}	
		}
		
		return instance;
	}
	
	
	
	
	public  String process_Data_Response(String string, boolean lastQuery) {
		
		
		if(string.length()>200) {
			String string_Processed="";
			string_Processed= string.substring(120, string.length()-7);
			if(!lastQuery)
				string_Processed+=",\n";
			
			return string_Processed;
		}
		
		return string;
		
	}
	
}
