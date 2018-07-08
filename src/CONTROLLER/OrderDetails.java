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

import DAO.OrdersD;
import DAO.Orders_DetailsD;
import DAO.ProductsD;

/**
 * Servlet implementation class OrderList
 */
@WebServlet("/orderdetails")
public class OrderDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductsD productsdao = new ProductsD();
		HttpSession session = request.getSession();
		int id = Integer.parseInt(request.getParameter("id"));
		boolean message = false;
		JSONArray productOrder = new JSONArray();
		JSONArray productList = new JSONArray();
		JSONObject user = (JSONObject) session.getAttribute("user");
		if(user!=null){
			OrdersD ordersdao = new OrdersD();
			message = ordersdao.CheckOrderUser(user.getString("email"), id);
			if(message)
			{
				Orders_DetailsD orders_detailsdao = new Orders_DetailsD();
				productOrder = orders_detailsdao.getOrderDetails(id);
			}
		}
		else{
			OrdersD ordersdao = new OrdersD();
			message = ordersdao.CheckOrderGuest(session.getId(), id);
			if(message)
			{
				Orders_DetailsD orders_detailsdao = new Orders_DetailsD();
				productOrder = orders_detailsdao.getOrderDetails(id);
			}
		}
		if(message)
		{
			for(int i = 0,length = productOrder.length(); i < length ; i++)
			{
				JSONObject product = productOrder.getJSONObject(i);
				String idProduct = String.valueOf(product.getInt("id_products"));
				JSONObject productReturn = productsdao.getProductById(idProduct).getJSONObject(0);
				productList.put(productReturn);
			}
			request.setAttribute("productList",productList );
			request.getRequestDispatcher("WEB-INF/orderdetails.jsp").forward(request, response);
		}
		else {
			response.getWriter().append("");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
