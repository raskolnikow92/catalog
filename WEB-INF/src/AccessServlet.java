import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serial;


public class AccessServlet extends HttpServlet{
    private UserManager userManager;
    private DatabaseManager db;
    @Serial
    private static final long serialVersionUID =3L;
    @Override
    public void init(){
        db = new DatabaseManager();
        userManager = new UserManager(db.getUsers());
    }
    @Override
    public void destroy(){
        db = null;
        userManager = null;
        super.destroy();
    }

    private void loginAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session = request.getSession(false);
        if(session != null){
            String sessionMail = (String) session.getAttribute("email");
            if(sessionMail == null){
                session.invalidate();
                response.sendRedirect("/catalog/login.html");
                return;
            }
            if(sessionMail.equals(email)){
                response.sendRedirect("/catalog/catalog.html");
                return;
            }
            session.invalidate();
            response.sendRedirect("/catalog/login.html");
            return;
        }
        try{
            userManager.loginUser(new User(email, password));

            session = request.getSession();
            session.setAttribute("email", email);
            response.sendRedirect("/catalog/login.html");
        }catch (Exception e){
            String errorResponse = "Eigene Fehlermeldung: login gescheitert";
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, errorResponse);
        }
    }
    private void registerAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
            String errorResponse = "Eigene Fehlermeldung:You are already signed in. Please try registering again!";
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, errorResponse);
            return;
        }
        try{
            User registeringUser = new User(email, password, firstName, lastName);
            userManager.registerUser(registeringUser);
            response.sendRedirect("/catalog/login.html");
        }catch (Exception e){
            String errorResponse = "Eigene Fehlermeldung:Looks like there was an error with the user you tried to log in. Make sure that all the fields in the form have some value and are not empty.";
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, errorResponse);
        }
    }
    private void logoutAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if(session == null){
            response.sendRedirect("/catalog/login.html");
            return;
        }
        session.invalidate();
        response.sendRedirect("/catalog/login.html");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        if(action == null){
            String errorResponse = "Eigene Fehlermeldung:Looks like there was an error with the user you tried to log in. Make sure that all the fields in the form have some value and are not empty.";
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, errorResponse);
        }else if(action.equals("login")){
            loginAction(request, response);
        }else if(action.equals("register")){
            registerAction(request, response);
        }else if(action.equals("logout")){
            logoutAction(request, response);
        }else{
            String errorResponse = "Eigene Fehlermeldung:Looks like there was an error with the user you tried to log in. Make sure that all the fields in the form have some value and are not empty.";
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, errorResponse);
        }
    }

}
