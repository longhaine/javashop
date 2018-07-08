package DAO;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

public class DeleteD {
	public String Table(String table, String id) throws IOException {
		String path ="http://localhost:3000/delete/"+table+"/"+id;
		URL url = new URL(path);
		Scanner scan = new Scanner(url.openStream());
		String json = "";
		while(scan.hasNext())
		{
			json = json + scan.nextLine();
		}
		scan.close();
		JSONObject jsonarr = new JSONObject(json);
		String message = jsonarr.getString("message");
		return message;
	}
}
