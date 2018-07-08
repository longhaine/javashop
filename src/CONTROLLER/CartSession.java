package CONTROLLER;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import DAO.BrandsD;
import DAO.ProductsD;
import DAO.CategoriesD;
/**
 * Servlet implementation class Test
 */
@WebServlet(urlPatterns = { "/addcart", "/removecart"})
public class CartSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartSession() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath().substring(1);// get path
		ProductsD productsdao = new ProductsD();
		String id = request.getParameter("product"); // get parameter product id
		int checkProduct = productsdao.getProductById(id).length(); // check product existence
		HttpSession session = request.getSession();
		if(id!= null && checkProduct == 1) 
		{
			JSONObject product = productsdao.getProductById(id).getJSONObject(0); // get product to add or remove
			JSONArray productList = (JSONArray)session.getAttribute("productList"); //old product list
			if(action.equals("addcart")) // addcart
			{
				if(productList != null)
				{
					productList.put(product);
				}
				else
				{
					productList = new JSONArray();
					productList.put(product);
				}
			}
			if(action.equals("removecart")) { // remove cart
				if(productList != null) 
				{
					int index = -1;
					for(int i = 0,length = productList.length(); i < length ; i++) 
					{
						JSONObject productOfList = productList.getJSONObject(i);
						if(productOfList.getInt("id") == Integer.parseInt(id))
						{
							index = i;
							break;
						}
					}
					if(index >=0)
					{
						productList.remove(index);
					}
				}
			}
			session.setAttribute("productList", productList);
			session.setMaxInactiveInterval(5*60); // 5 minutes
			if(productList.length() == 0) // if session productlist doesn't have 1 product
			{
				session.removeAttribute("productList");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
