

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author nbuser
 */
public class AutoCompleteServlet extends HttpServlet {

    private ServletContext context;
   // private ComposerData compData = new ComposerData();
   // private HashMap composers = compData.getComposers();
     HashMap<String, Product> products = BestDealSerializedDataStore.getProducts();


    @Override
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
    }

    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

	    
	    System.out.println("into autoServlet!!!");
	    
        String action = request.getParameter("action");
        String targetId = request.getParameter("id");
	
	 System.out.println(action);
	  System.out.println(targetId);
	
        StringBuffer sb = new StringBuffer();

        if (targetId != null) {
            targetId = targetId.trim().toLowerCase();
        } else {
            context.getRequestDispatcher("/error.jsp").forward(request, response);
        }

	
	System.out.println("into add");
        boolean namesAdded = false;
          if (action.equals("complete")) {

            // check if user sent empty string
            if (!targetId.equals("")) {
                sb=AjaxUtilities.readData(targetId);
		if(sb!=null ||!sb.equals("")) 
		{
			namesAdded=true;
		}
 
 
		System.out.println("string !!"+"<products>" + sb.toString() + "</products>");
            }

            if (namesAdded) {
                response.setContentType("text/xml");
                response.getWriter().write("<products>" + sb.toString() + "</products>");
            } else {
                //nothing to show
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        }


        if (action.equals("lookup")) {

            // put the target composer in the request scope to display 
            if ((targetId != null) && products.containsKey(targetId.trim())) {
		    System.out.println("into look up id"+targetId);
            request.setAttribute("product",targetId);
	context.getRequestDispatcher("/searchresult").forward(request, response);
	
            }
        }
    }
}
