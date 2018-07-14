package CONTROLLER;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import DAO.BrandsD;
import DAO.CategoriesD;
import DAO.ProductsD;

/**
 * Servlet implementation class Search
 */
@WebServlet("/search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoadBanner(request, response);
		String q = request.getParameter("q");
		LoadProductByQuery(request, response, q);
		request.getRequestDispatcher("WEB-INF/search.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
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
	protected void LoadProductByQuery(HttpServletRequest request, HttpServletResponse response,String q) throws ServletException, IOException {
		ProductsD productsdao = new ProductsD();
		JSONArray products = null;
		String categoryParameter = request.getParameter("category");
		if(categoryParameter == null){
			categoryParameter = "all";
			products = productsdao.Search(q);
		}
		else {
			products = productsdao.getProductBySearchCategory(q, categoryParameter);
		}
		request.setAttribute("categoryParameter", categoryParameter);
		request.setAttribute("products",products );
//		request.setAttribute("gender", gender);
	}
}
