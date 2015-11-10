package util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {

	public static void sendRequest(final String address,final HttpCallBacak callback){
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				HttpURLConnection connection =null; 
				try {
					URL url = new URL(address);
					connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.setReadTimeout(8000);
					connection.setConnectTimeout(8000);
					InputStream in = connection.getInputStream();
					BufferedReader read = new BufferedReader(new InputStreamReader(in));
					StringBuilder response = new StringBuilder();
					String line;
					while((line=read.readLine())!=null)
					{
						response.append(line);
						if(callback!=null)
						{
							callback.finish(response.toString());
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					if(callback!=null)
					{
						callback.onerror(e);
					}
					e.printStackTrace();
				}
				
			}
		}).start();
	}
}
