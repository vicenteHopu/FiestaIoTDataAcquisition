package Main;

import Controllers.DataController;
import Controllers.TokenController;
import POSTclasses.Post;
import POSTclasses.PostBuilderQuery;
import POSTclasses.PostBuilderToken;

public class FiestaIoTMain {

	public static void main(String[] args) {
		
		
		DataController.get_DataController().perform_Query_Request();

	}

}
