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
import DAO.ColumnsD;
import DAO.ProductsD;

/**
 * Servlet implementation class Login
 */
@WebServlet("/admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			JSONObject user = (JSONObject)session.getAttribute("user");
			if(user != null && user.getInt("role")==1) {
			ColumnsD columnsdao = new ColumnsD();
			ProductsD productsdao = new ProductsD();
			CategoriesD categoriesdao = new CategoriesD();
			BrandsD brandsdao = new BrandsD();
			JSONArray columns = columnsdao.Columns("products");
			JSONArray productList = productsdao.getAll();
			JSONArray categories = categoriesdao.getCategories();
			JSONArray brands = brandsdao.getBrands();
			request.setAttribute("brands", brands);
			request.setAttribute("categories", categories);
			request.setAttribute("columns", columns);
			request.setAttribute("productList", productList);
			request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
			}
			else {
				response.sendRedirect("http://localhost:8080/WebShop/");
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
