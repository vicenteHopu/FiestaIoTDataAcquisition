package Main;

import Controllers.DataController;
import Controllers.TokenController;
import POSTclasses.Post;
import POSTclasses.PostBuilderQuery;
import POSTclasses.PostBuilderToken;

public class FiestaIoTMain {

	public static void main(String[] args) {
		DataController.get_DataController().perform_Query_Request("201711081400", "201711311400", "2017NovemberData.json");
		
		DataController.get_DataController().perform_Query_Request("201801201400", "201801211400","2018NewYearData.json");
	}

}
