package DAO;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;



public class CategoriesD {
	
	public JSONArray getCategories() throws IOException
	{
		String path = "http://localhost:3000/categories";
		URL url = new URL(path);
		Scanner scan = new Scanner(url.openStream());
		String json = "";
		while(scan.hasNext())
		{
			json = json + scan.nextLine();
		}
		scan.close();
		JSONArray arrobject = new JSONArray(json);
		return arrobject;
	} // banner 
}
