package CONTROLLER;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.parser.Parser;

import org.json.JSONArray;

import DAO.BrandsD;
import DAO.ProductsD;
import DAO.CategoriesD;

/**
 * Servlet implementation class Shop
 */
@WebServlet(urlPatterns = { "/men", "/women"})
public class Shop extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Shop() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gender = request.getServletPath().substring(1);
		if(gender.equals("men")||gender.equals("women"))
		{
			doHandle(request, response, gender);
		}
		else
		{
			doGender(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	protected void doHandle(HttpServletRequest request, HttpServletResponse response,String gender) throws ServletException, IOException {
		//load banner
		LoadBanner(request, response);
		//load products
		LoadProductByGender(request, response, gender);
		request.getRequestDispatcher("WEB-INF/shop.jsp").forward(request, response);
	}
	protected void doGender(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Men or Women?");
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
	protected void LoadProductByGender(HttpServletRequest request, HttpServletResponse response,String gender) throws ServletException, IOException {
		ProductsD productsdao = new ProductsD();
		JSONArray products = null;
		String categoryParameter = request.getParameter("category");
		if(categoryParameter == null){
			categoryParameter = "all";
			products = productsdao.getProductsByGender(gender);
		}
		else {
			products = productsdao.getProductByCategory(gender, categoryParameter);
		}
		request.setAttribute("categoryParameter", categoryParameter);
		request.setAttribute("products",products );
		request.setAttribute("gender", gender);
	}
}
