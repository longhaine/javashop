package DAO;

import java.io.IOException;
import java.net.MalformedURLException;
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

public class ProductsD {
	public JSONArray getPopularItems() throws IOException {
		String path ="http://localhost:3000/products/popular";
		URL url = new URL(path);
		Scanner scan = new Scanner(url.openStream());
		String json ="";
		while(scan.hasNext())
		{
			json = json + scan.nextLine();
		}
		scan.close();
		JSONArray arrobject = new JSONArray(json);
		return arrobject;
	}
	public JSONArray getProductsByGender(String gender) throws IOException {
		String path ="http://localhost:3000/products/gender/"+gender;
		URL url = new URL(path);
		Scanner scan = new Scanner(url.openStream());
		String json ="";
		while(scan.hasNext())
		{
			json = json + scan.nextLine();
		}
		scan.close();
		JSONArray arrobject = new JSONArray(json);
		return arrobject;
	}
	public JSONArray getProductByCategory(String gender,String category) throws IOException {
		String path ="http://localhost:3000/products/gender/"+gender+"/category/"+category;
		URL url = new URL(path);
		Scanner scan = new Scanner(url.openStream());
		String json ="";
		while(scan.hasNext())
		{
			json = json + scan.nextLine();
		}
		scan.close();
		JSONArray arrobject = new JSONArray(json);
		return arrobject;
	}
	public JSONArray getProductById(String id) throws IOException {
		String path ="http://localhost:3000/products/id/"+id;
		URL url = new URL(path);
		Scanner scan = new Scanner(url.openStream());
		String json ="";
		while(scan.hasNext())
		{
			json = json + scan.nextLine();
		}
		scan.close();
		JSONArray arrobject = new JSONArray(json);
		return arrobject;
	}
	public JSONArray getAll() throws IOException {
		String path ="http://localhost:3000/products";
		URL url = new URL(path);
		Scanner scan = new Scanner(url.openStream());
		String json ="";
		while(scan.hasNext())
		{
			json = json + scan.nextLine();
		}
		scan.close();
		JSONArray arrobject = new JSONArray(json);
		return arrobject;
	}
	public String Add(JSONObject product) throws IOException {
		JSONObject request = new JSONObject();
		request.put("product", product);
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost("http://localhost:3000/products/add");
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
}
