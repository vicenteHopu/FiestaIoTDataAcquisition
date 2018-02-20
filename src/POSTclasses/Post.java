package POSTclasses;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Post {
	
	
	private static String tokenParameterName="iPlanetDirectoryPro";
	private static String credentialsParameterName="X-OpenAM-Username";
	private static String credentialsParameterPassword="X-OpenAM-Password";
	private static String credentialsParameterContentType="Content-Type";
	private static String credentialsParameterCacheControl="Cache-Control";
	private static String credentialsParameterAccept="Accept";
	private static String fiestaIoTUser="hopUbiquitous";
	private static String fiestaIoTPassword="Hopu2018#";
	private static String fiestaIoTContentTypeToken="application/json";
	private static String fiestaIoTContentTypeTokenPlain="text/plain";
	private static String fiestaIoTCacheControl="no-cache";
	private static String fiestaIoTAccept="application/json";
	
	
	private Map<String, String> map=null;
	private String body=null;
	private String serverIp=null;
	private URI uri=null;
	
	
	
	
	
	public Post(String serverIp) {
		this.serverIp=serverIp;
		map= new HashMap<String, String>();
	}
	
	public Post(URI uri) {
		this.uri=uri;
		map= new HashMap<String, String>();
	}
	
	
	
	public boolean add_Parameter(String parameterName, String parameterBody) {
		
		try {
		map.put(parameterName, parameterBody);
		return true;
		}
		catch(NullPointerException e) {
			System.out.println("The map hasn't been created");
			return false;
		}
	}
	public boolean set_Fiesta_IoT_Token(String token) {
		return add_Parameter(tokenParameterName, token);
	}
	public boolean set_Fiesta_IoT_User() {
		return add_Parameter(credentialsParameterName, fiestaIoTUser);
	}
	public boolean set_Fiesta_IoT_Password() {
		return add_Parameter(credentialsParameterPassword, fiestaIoTPassword);
	}
	public boolean set_Fiesta_IoT_Content_Tpye() {
		return add_Parameter(credentialsParameterContentType, fiestaIoTContentTypeToken);
	}
	public boolean set_Fiesta_IoT_Content_Tpye_Plain() {
		return add_Parameter(credentialsParameterContentType, fiestaIoTContentTypeTokenPlain);
	}
	
	public boolean set_Fiesta_IoT_Cache_Control() {
		return add_Parameter(credentialsParameterCacheControl, fiestaIoTCacheControl);
	}
	
	public boolean set_Fiesta_IoT_Accept() {
		return add_Parameter(credentialsParameterAccept, fiestaIoTAccept);
	}
	
	
	public String get_Server_Ip(){
		return serverIp;
	}
	
	public URI get_Uri(){
		return uri;
	}
	
	
	public Map<String, String> get_Parameter_Map(){
		return map;
	}
	
	
	public boolean set_Body(String body) {
		this.body=body;
		return true;
	}
	
	
	
	
	@Override
	public String toString() {
		
		String classString= "Server Path: \t"+serverIp;
		classString+= "\n\n";
		
		classString+="Parameters : \n";
		for (Entry<String, String> entry : map.entrySet()) {
		    String key = entry.getKey();
		    classString+= "\t Parameter Name: \t"+key;
		    String value = entry.getValue();
		    classString+= "\t Parameter Value: \t"+value+"\n";
		}
		
		classString+= "\n\n";
		classString+= "Body: \n \t\t"+body;
		
		return classString;
	}
	
	
	
	
	
	

}
