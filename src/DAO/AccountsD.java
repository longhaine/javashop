package DAO;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

public class AccountsD {
	public JSONObject Login(String email, String password) throws IOException {
		JSONObject request = new JSONObject();
		request.put("email", email);
		request.put("password", password);
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost("http://localhost:3000/accounts/login");
		StringEntity params = new StringEntity(request.toString());
		post.addHeader("content-type", "application/json");
		post.setEntity(params);
		HttpResponse response = httpClient.execute(post);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody = responseHandler.handleResponse(response);
		httpClient.close();
		JSONObject jsoObject = new JSONObject(responseBody);
		return jsoObject;
	}
	public JSONObject Register(String email, String password, String name) throws IOException {
		JSONObject request = new JSONObject();
		request.put("email", email);
		request.put("password", password);
		request.put("name", name);
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost("http://localhost:3000/accounts/register");
		StringEntity params = new StringEntity(request.toString());
		post.addHeader("content-type", "application/json");
		post.setEntity(params);
		HttpResponse response = httpClient.execute(post);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody = responseHandler.handleResponse(response);
		httpClient.close();
		JSONObject jsoObject = new JSONObject(responseBody);
		return jsoObject;
	}
	public JSONObject Update(String email, String name, String address,String phone) throws IOException {
		JSONObject request = new JSONObject();
		request.put("email", email);
		request.put("name", name);
		request.put("address", address);
		request.put("phone", phone);
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost("http://localhost:3000/accounts/update");
		StringEntity params = new StringEntity(request.toString());
		post.addHeader("content-type", "application/json");
		post.setEntity(params);
		HttpResponse response = httpClient.execute(post);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody = responseHandler.handleResponse(response);
		httpClient.close();
		JSONObject jsoObject = new JSONObject(responseBody);
		return jsoObject;
	}
	public JSONObject ChangePassword(String email, String password) throws IOException {
		JSONObject request = new JSONObject();
		request.put("email", email);
		request.put("password", password);
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost("http://localhost:3000/accounts/changepassword");
		StringEntity params = new StringEntity(request.toString());
		post.addHeader("content-type", "application/json");
		post.setEntity(params);
		HttpResponse response = httpClient.execute(post);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody = responseHandler.handleResponse(response);
		httpClient.close();
		JSONObject jsoObject = new JSONObject(responseBody);
		return jsoObject;
	}
}
