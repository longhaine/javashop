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

public class Orders_DetailsD {
	public String Order_Details(int idOrder ,JSONArray productList) throws IOException {
		JSONObject request = new JSONObject();
		request.put("idOrder", idOrder);
		request.put("productList", productList);
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost("http://localhost:3000/orders_details");
		StringEntity params = new StringEntity(request.toString());
		post.addHeader("content-type", "application/json");
		post.setEntity(params);
		HttpResponse response = httpClient.execute(post);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody = responseHandler.handleResponse(response);
		httpClient.close();
		JSONObject jsoObject = new JSONObject(responseBody);
		String message = jsoObject.getString("message");
		return message;
	}
	public JSONArray getOrderDetails(int id) throws IOException {
		String path ="http://localhost:3000/orders_details/get/"+id;
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
}
