package CONTROLLER;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import DAO.AccountsD;
import DAO.BrandsD;
import DAO.CategoriesD;
import DAO.OrdersD;
import DAO.Orders_DetailsD;
import DAO.ProductsD;

/**
 * Servlet implementation class Checkout
 */
@WebServlet("/check-out")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Checkout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
		HttpSession session = request.getSession();
		JSONArray productList = (JSONArray) session.getAttribute("productList");
		JSONObject user = (JSONObject) session.getAttribute("user");
		String userAddress ="";
		if(user != null) {
			userAddress = user.getString("address");
		}
		if(productList != null && user != null && userAddress != "")// okay
		{
			LoadBanner(request, response);
			request.getRequestDispatcher("WEB-INF/checkout.jsp").forward(request, response);
		}
		else if(productList !=null && user != null && userAddress.equals(""))// in case user doesn't have address
		{
			Cookie path = new Cookie("path",request.getServletPath().substring(1));
			path.setMaxAge(5*60);
			response.addCookie(path);
			request.setAttribute("message","pls add your address before checkout");
			request.getRequestDispatcher("your-info").forward(request, response);
		}
		else if(productList !=null && user == null) // in case guest
		{
			LoadBanner(request, response);
			request.getRequestDispatcher("WEB-INF/checkout.jsp").forward(request, response);
		}
		else
		{
			response.sendRedirect("http://localhost:8080/WebShop/");
		}
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		JSONArray productList = (JSONArray) session.getAttribute("productList");
		JSONObject user = (JSONObject)session.getAttribute("user");
		String email = "unknow";
		String userName ="";
		String userAddress ="";
		String userPhone ="";
		if(user != null)
		{
			email = user.getString("email");
			userAddress = user.getString("address");
			userName = user.getString("name");
			userPhone = user.getString("phone");
		}
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		if(productList != null && user != null && userAddress.equals("")) // in case email doesn't have address
		{
			Cookie path = new Cookie("path",request.getServletPath().substring(1));
			path.setMaxAge(5*60);
			response.addCookie(path);
			request.setAttribute("message","pls add your address before checkout");
			request.getRequestDispatcher("your-info").forward(request, response);
		}
		else if(productList !=null && user != null && userAddress != "" ) // everything's okay
		{
				float price = 0;
				for(int i = 0,length = productList.length() ; i < length ; i++) // counting total price
				{
					price = price + productList.getJSONObject(i).getFloat("price");
				}
				OrdersD ordersdao = new OrdersD();
				Orders_DetailsD orders_detailsdao = new Orders_DetailsD();
				JSONObject order = new JSONObject();
				order.put("email", email);
				order.put("name", userName);
				order.put("address", userAddress);
				order.put("phone", userPhone);
				order.put("sessionId", "");
				int idOrder = ordersdao.Order(order,price);// get idOrder just insert
				String message = orders_detailsdao.Order_Details(idOrder, productList); // check 
				if(message.equals("ok")) // everything's okay!
				{
					session.removeAttribute("productList");// delete productList just placed order
					session.setAttribute("message", "Place order successfully!!! The products will be delivered to your address tomorrow!");
				}
				response.sendRedirect("http://localhost:8080/WebShop/orders");
		}
		else if(productList !=null && user == null) // in case guest
		{
			if(name.length() >=1 && address.length() >= 1 && phone.length() >= 1) // in case doesn't have name, address, phone
			{
				float price = 0;
				for(int i = 0,length = productList.length() ; i < length ; i++) // counting total price
				{
					price = price + productList.getJSONObject(i).getFloat("price");
				}
				String sessionId = session.getId();
				OrdersD ordersdao = new OrdersD();
				Orders_DetailsD orders_detailsdao = new Orders_DetailsD();
				JSONObject order = new JSONObject();
				order.put("email", email);
				order.put("name", name);
				order.put("address", address);
				order.put("phone", phone);
				order.put("sessionId", sessionId);
				int idOrder = ordersdao.Order(order,price);// get idOrder just insert
				String message = orders_detailsdao.Order_Details(idOrder, productList); // check 
				if(message.equals("ok")) // everything's okay!
				{
					session.removeAttribute("productList");// delete productList just place order
					session.setAttribute("guest", order);// generate guest info
					session.setAttribute("message", "Place order successfully!!! The products will be delivered to your address tomorrow!");
				}
				response.sendRedirect("http://localhost:8080/WebShop/orders");
			}
			else {
				request.setAttribute("message", "invalid");
				doGet(request, response);
			}
		}
		else // something's wrong 
		{
			response.sendRedirect("http://localhost:8080/WebShop/");
		}
		
	}
	protected void LoadBanner(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CategoriesD categoriesdao = new CategoriesD();
		BrandsD brandsdao = new BrandsD();
		ProductsD productsdao = new ProductsD();
		JSONArray categories = categoriesdao.getCategories();
		request.setAttribute("categories",categories); // specifics
		JSONArray brands = brandsdao.getBrands();
		request.setAttribute("brands", brands);
	}
}
