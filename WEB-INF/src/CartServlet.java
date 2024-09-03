import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class CartServlet extends HttpServlet {
    private  CartManager cartManager;
    private DatabaseManager db;
    private static final long serialVersionUID = 4L;

    @Override
    public void init(){
        db = new DatabaseManager();
        cartManager = new CartManager(db.getUserCarts());
    }

    @Override
    public void destroy(){
         cartManager = null;
         db = null;
         super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session == null){
            resp.sendRedirect("/catalog/login.html");
            return;
        }
        String email = (String)session.getAttribute("email");
        if(email == null){
            session.invalidate();
            resp.sendRedirect("/catalog/login.html");
            return;
        }
        Map<CartItem, Integer> cart = cartManager.getUserCart(email);
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        if(cart == null || cart.isEmpty()){
            out.println("<html>");
            out.println("<body>");
            out.println("<p>Your cart is empty!</p>");
            out.println("<a href='/catalog/catalog.html'>Link zum Katalog</a>");
            out.println("</body>");
            out.println("</html>");
            return;
        }
        out.println(CartSummaryHtmlGenerator.getCartSummaryPage(cart));
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session == null){
            resp.sendRedirect("/catalog/login.html");
            return;
        }
        String email = (String) session.getAttribute("email");
        if(email == null){
            session.invalidate();
            resp.sendRedirect("/catalog/login.html");
            return;
        }
        String imgAddress = req.getParameter("imgAddress");
        String itemName = req.getParameter("itemName");
        String itemPrice = req.getParameter("itemPrice");
        CartItem cartItem = new CartItem(imgAddress, itemName, Integer.parseInt(itemPrice));
        cartManager.addToCart(email, cartItem);
        resp.sendRedirect("/catalog/catalog.html");
    }
}
