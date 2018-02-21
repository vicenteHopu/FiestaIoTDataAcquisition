package POSTclasses;

public class PostBuilderToken{
	
	private static String serverPath="https://platform.fiesta-iot.eu/openam/json/authenticate";
	
	public static Post build_Post() {
		
		Post post= new Post(serverPath);
		post.set_Fiesta_IoT_User();
		post.set_Fiesta_IoT_Password();
		post.set_Fiesta_IoT_Content_Tpye();
		post.set_Fiesta_IoT_Cache_Control();

	
		return post;
	}
	
	
	

}
