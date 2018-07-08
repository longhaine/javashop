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

public class OrdersD {
	public int Order(JSONObject order ,float price) throws IOException {
		JSONObject request = new JSONObject();
		request.put("order", order);
		request.put("price", price);
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost("http://localhost:3000/orders");
		StringEntity params = new StringEntity(request.toString());
		post.addHeader("content-type", "application/json");
		post.setEntity(params);
		HttpResponse response = httpClient.execute(post);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody = responseHandler.handleResponse(response);
		httpClient.close();
		JSONObject jsoObject = new JSONObject(responseBody);
		int idOrder = jsoObject.getInt("idOrder");
		return idOrder;
	}
	public JSONArray GetOrdersGuest(String sessionId) throws IOException {
		String path ="http://localhost:3000/orders/guest/"+sessionId;
		URL url = new URL(path);
		Scanner scan = new Scanner(url.openStream());
		String json = "";
		while(scan.hasNext())
		{
			json = json + scan.nextLine();
		}
		scan.close();
		JSONArray jsonarr = new JSONArray(json);
		return jsonarr;
	}
	public JSONArray GetOrdersUser(String email) throws IOException {
		String path ="http://localhost:3000/orders/email/"+email;
		URL url = new URL(path);
		Scanner scan = new Scanner(url.openStream());
		String json = "";
		while(scan.hasNext())
		{
			json = json + scan.nextLine();
		}
		scan.close();
		JSONArray jsonarr = new JSONArray(json);
		return jsonarr;
	}
	public boolean CheckOrderGuest(String sessionId,int id) throws IOException {
		String path ="http://localhost:3000/orders/checkguest/"+sessionId+"/"+id;
		URL url = new URL(path);
		Scanner scan = new Scanner(url.openStream());
		String json = "";
		while(scan.hasNext())
		{
			json = json + scan.nextLine();
		}
		scan.close();
		JSONObject jsonobject = new JSONObject(json);
		return jsonobject.getBoolean("message");
	}
	public boolean CheckOrderUser(String email,int id) throws IOException {
		String path ="http://localhost:3000/orders/checkuser/"+email+"/"+id;
		URL url = new URL(path);
		Scanner scan = new Scanner(url.openStream());
		String json = "";
		while(scan.hasNext())
		{
			json = json + scan.nextLine();
		}
		scan.close();
		JSONObject jsonobject = new JSONObject(json);
		return jsonobject.getBoolean("message");
	}
}
