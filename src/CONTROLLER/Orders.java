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
import DAO.CategoriesD;
import DAO.OrdersD;
import DAO.ProductsD;

/**
 * Servlet implementation class Orders
 */
@WebServlet("/orders")
public class Orders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Orders() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoadBanner(request, response);
		HttpSession session = request.getSession();
		JSONObject user = (JSONObject) session.getAttribute("user");
		JSONArray orderList = new JSONArray();
		if(user != null){
			String email = (String) user.get("email");
			orderList = GetOrderUser(request, response, email);
		}
		else { // in case guest
			String sessionId = session.getId();
			orderList = GetOrderGuest(request, response,sessionId);
		}
		session.setAttribute("orderList", orderList);
		request.getRequestDispatcher("WEB-INF/orders.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
	protected JSONArray GetOrderGuest(HttpServletRequest request, HttpServletResponse response,String sessionId) throws ServletException, IOException {
		OrdersD ordersdao = new OrdersD();
		JSONArray orderList = ordersdao.GetOrdersGuest(sessionId);
		return orderList;
	}
	protected JSONArray GetOrderUser(HttpServletRequest request, HttpServletResponse response,String email) throws ServletException, IOException {
		OrdersD ordersdao = new OrdersD();
		JSONArray orderList = ordersdao.GetOrdersUser(email);
		return orderList;
	}
}
