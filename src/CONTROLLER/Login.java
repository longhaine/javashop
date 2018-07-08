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
import DAO.ProductsD;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoadBanner(request, response);
		HttpSession session = request.getSession();
		if(session.getAttribute("email") == null)
		{
			request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
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
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		if(email != null && password != null)
		{
			AccountsD accountsdao = new AccountsD();
			JSONObject account = accountsdao.Login(email, password);
			boolean message = account.getBoolean("message");
			if(message == true)
			{
				HttpSession session = request.getSession();
				JSONObject user = account.getJSONArray("data").getJSONObject(0);
				session.setAttribute("user", user);
				session.setMaxInactiveInterval(5*60);
				// delete guest info
				session.removeAttribute("guest");
				// get path before come to login
				String pathBefore = null;
				for(Cookie c : request.getCookies()) 
				{
					if(c.getName().equals("path"))
					{
						pathBefore = c.getValue();
						c.setMaxAge(0);
						response.addCookie(c);//del cookie = del before path
						break;
					}
				}
				if(pathBefore != null) 
				{
					
					response.sendRedirect("http://localhost:8080/WebShop/"+pathBefore);
				}
				else {
					response.sendRedirect("http://localhost:8080/WebShop/");
				}

			}
			else {
				request.setAttribute("email", email);
				request.setAttribute("message", "invalid email or password");
				doGet(request, response);
			}
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
