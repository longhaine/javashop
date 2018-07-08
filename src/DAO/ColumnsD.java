package DAO;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;

public class ColumnsD {
	public JSONArray Columns(String table) throws IOException {
		String path ="https://serverjavashop.herokuapp.com/columns/"+table;
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
}
