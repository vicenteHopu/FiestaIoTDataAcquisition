package POSTclasses;


import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class PostSender {
	
	private Post post = null;
	
//	private HttpClient httpclient = HttpClients.createDefault();
//	private HttpPost httppost = new HttpPost("http://www.a-domain.com/foo/");
	private HttpClient httpclient = null;
	private HttpPost httppost = null;
	private String post_Response= null;
	
	
	public PostSender(Post post) {
		this.post=post;
	}
	
	
	
	public  void send_Post_Request() {		
		httpclient = HttpClients.createDefault();
		
		URI uri=post.get_Uri();
		
		if(uri==null)
			httppost = new HttpPost(post.get_Server_Ip());
		else {
			httppost = new HttpPost(uri);
		}
		
		
		List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		for (Entry<String, String> entry : post.get_Parameter_Map().entrySet()) {
			
			String key= entry.getKey(); 
			String value= entry.getValue();
			httppost.setHeader(key, value);
		} 
		
		try {
			if(post.get_Body()!=null && !post.get_Body().isEmpty())
				httppost.setEntity(new StringEntity(post.get_Body()));
			
			
			
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
		HttpResponse response;
		InputStream instream=null;
		try {
			response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
			    	instream =entity.getContent();
			    	StringWriter writer = new StringWriter();
			    	IOUtils.copy(instream, writer, Charset.defaultCharset());
			    	post_Response = writer.toString();
			    	instream.close();
			    	}
		
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public  String retrieve_Post_Response() {
		return post_Response;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
