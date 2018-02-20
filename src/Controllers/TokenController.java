package Controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import POSTclasses.PostBuilderToken;
import POSTclasses.PostSender;

public class TokenController {
	
	private static TokenController instance=null;
	
	private String temporal_Token="";
	private boolean valid_Temporal_Token=false;
	
	
	
	private TokenController() {
	};
	
	public static TokenController get_TokenController() {
		if(instance==null) {
			try {
				instance= new TokenController();
			}
			catch (NullPointerException e) {
				instance=null;
				System.out.println("Couldn't create TokenController");
			}		
		}
		return instance;
	}
	
	
	
	
	public boolean perform_Token_Request() {
		
		PostSender postSender= new PostSender(PostBuilderToken.build_Post());
		postSender.send_Post_Request();
		temporal_Token=parse_Token_Request(postSender.retrieve_Post_Response());
		return true;
		
	}
	
	
	
	public String get_Token() {
		if(!valid_Temporal_Token && perform_Token_Request()) {
			valid_Temporal_Token=true;
		}
		else if(!valid_Temporal_Token){
			set_Token_To_Expired();
		}
		return temporal_Token;	
	}
	
	public void set_Token_To_Expired() {
		valid_Temporal_Token=false;
		temporal_Token="";
	}
	
	
	
	private String parse_Token_Request(String postResponse) {
		
		
		Pattern patternToken = Pattern.compile("\"[^\"]*\"");
		Matcher matcherToken = patternToken.matcher(postResponse);
		String token_Parsed="";
		
		if(matcherToken.find())
			token_Parsed=matcherToken.group();
		if(matcherToken.find())
			token_Parsed=matcherToken.group();
		
		if(!token_Parsed.isEmpty()) {
			token_Parsed=token_Parsed.substring(1, token_Parsed.length()-1);
		}
		
		return token_Parsed;
	}
	
	
	
	
	
	
	
	
	
	

}
