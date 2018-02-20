package POSTclasses;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;

import Controllers.TokenController;

public class PostBuilderQuery{


	private static String serverPath="https://platform.fiesta-iot.eu/iot-registry/api/queries/execute/observations?";
	private static String queryFile="QueryFile";


	public PostBuilderQuery() {
	}

	public static Post build_Post(String from, String to) {
		
		
//		URI uri=null;
//		URIBuilder builder = new URIBuilder();
//		builder.setScheme("https").setHost("platform.fiesta-iot.eu").setPath("/iot-registry/api/queries/execute/observations")
//	    .setParameter("from", from)
//	    .setParameter("to", to);
//		try {
//			uri = builder.build();
//		} catch (URISyntaxException e) {
//			e.printStackTrace();
//		}
//		if(uri==null)
//			return null;
		
		
		String newServerPath=serverPath+"from="+from+"&to="+to;
		
		
		
		Post post= new Post(newServerPath);
		String token=TokenController.get_TokenController().get_Token();
		System.out.println("Token is: "+token);
		post.set_Fiesta_IoT_Token(token);
		post.set_Fiesta_IoT_Content_Tpye_Plain();
		post.set_Fiesta_IoT_Cache_Control();
		post.set_Fiesta_IoT_Accept();
		


		String query=get_Query_From_File();
		if(query==null || query.isEmpty())
			return null;

		post.set_Body(query);

		System.out.println("Query post:\n "+ post.toString());


		return post;
	}

	private static String get_Query_From_File() {

		String queryString="";
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = 
					new FileReader(queryFile);
			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = 
					new BufferedReader(fileReader);
			String line="";
			while((line = bufferedReader.readLine()) != null) {
				queryString+=line+"\n";
			}   
			// Always close files.
			bufferedReader.close();         
		}
		catch(FileNotFoundException ex) {
			System.out.println("Unable to open file '" + queryFile + "'");                
		}
		catch(IOException ex) {
			System.out.println("Error reading file '"+ queryFile + "'");                  
		}
		
		return queryString;


	}




}







