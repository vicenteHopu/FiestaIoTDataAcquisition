package Controllers;

import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;

import DataProcessor.DataProcessor;
import DataProcessor.DateCalculator;
import DataProcessor.ResponseWriter;
import POSTclasses.PostBuilderQuery;
import POSTclasses.PostBuilderToken;
import POSTclasses.PostSender;

public class DataController {
	
	
	
	private static DataController instance=null;
	
	
	private DataController() {
	};
	
	public static DataController get_DataController() {
		if(instance==null) {
			try {
				instance= new DataController();
			}
			catch (NullPointerException e) {
				instance=null;
				System.out.println("Couldn't create TokenController");
			}		
		}
		return instance;
	}
	
	public boolean perform_Query_Request(String start, String finish, String fileName) {
		
		
		String from=start;
		String to=start;
		String response="";
		
		ResponseWriter.getInstance().reset_Response_Writer(fileName);
		
		
		while((to=DateCalculator.nextDate(to)).compareTo(finish)!=0) {
			
			response= perform_Single_Query_Request(from, to);
			//Process the response
			String responseProcessed= DataProcessor.getInstance().process_Data_Response(response,false);
			//Write the processed response
			ResponseWriter.getInstance().write_Response_To_File(responseProcessed, false);
			from=to;
		}
			
		
		response= perform_Single_Query_Request(from, to);
		//Process the response
		String responseProcessed= DataProcessor.getInstance().process_Data_Response(response,true);
		//Write the processed response
		ResponseWriter.getInstance().write_Response_To_File(responseProcessed, true);
		

		return true;
	}
	
	
	
	private String perform_Single_Query_Request(String from, String to){
		
		System.out.println("Getting data from: "+from+ " to: "+to );
		
		
		String response="";
		int triesNumber=0;
		while((response.isEmpty() || response.length()<1000000) && triesNumber<5) {
			PostSender postSender= new PostSender(PostBuilderQuery.build_Post(from,to));
			postSender.send_Post_Request();
			response=postSender.retrieve_Post_Response();
			triesNumber++;
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				System.out.println("Couldn't sleep the process");
			}
			
		}
		
		return response;
		
	}

}
