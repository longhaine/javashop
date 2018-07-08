package CONTROLLER;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import DAO.AccountsD;
import DAO.BrandsD;
import DAO.CategoriesD;
import DAO.ProductsD;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoadBanner(request, response);
		request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String message = "";
		if(email.length() >= 1 && password.length() >= 1 & name.length() >= 1) 
		{
			AccountsD accountsdao = new AccountsD();
			JSONObject messageObject = accountsdao.Register(email, password, name);
			message = messageObject.getString("message");
			request.setAttribute("message", message);
			doGet(request, response);
		}
		else
		{
			message = "invalid";
			request.setAttribute("message", message);
			doGet(request, response);
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
