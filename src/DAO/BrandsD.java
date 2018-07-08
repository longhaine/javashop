package DAO;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;

public class BrandsD {
	public JSONArray getBrands() throws IOException
	{
		String path ="http://localhost:3000/brands";
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
