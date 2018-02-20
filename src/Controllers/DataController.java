package Controllers;

import java.net.URLEncoder;

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
	
	public boolean perform_Query_Request() {
		PostSender postSender= new PostSender(PostBuilderQuery.build_Post("201711121433","201711121550"));
		postSender.send_Post_Request();
		System.out.println(postSender.retrieve_Post_Response());
		
		return true;
	}
	
	

}
