import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet(name = "postgresServlet", urlPatterns = {"/postgresServlet"})
public class postgresServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet postgresServlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Java version: </h1>" + System.getProperty("java.version"));


        try {
            InitialContext cxt = null;
            try {


                cxt = new InitialContext();


            } catch (NamingException ex) {
                out.println("<h1>NamingException for InitialContext</h1>");
                out.println(ex.getExplanation() + "<br>Remaining: ");
                out.println(ex.getRemainingName() + "<br>Resolved: ");
                out.println(ex.getResolvedName());
            }
            if (cxt == null) {
                out.println("<h1>No context found</h1>");
                return;
            }
            DataSource ds = null;
            try {
                ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/postgres");
            } catch (NamingException ex) {
                out.println("<h1>NamingException for context lookup</h1>");
                out.println(ex.getExplanation() + "<br>Remaining: ");
                out.println(ex.getRemainingName() + "<br>Resolved: ");
                out.println(ex.getResolvedName());
            }


            if (ds == null) {
                out.println("<h1>No datasource</h1>");
                return;
            }
            Connection connection = ds.getConnection();


            PreparedStatement st;


            st = connection.prepareStatement("SELECT * FROM exampleTable");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                out.println("<h2>Column 2 returned " + rs.getString(2) + "</h2>");
            }
            rs.close();
            st.close();
            connection.close();


            out.println("<h1>Servlet postgresServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } catch (SQLException ex) {
            out.println("<h1>SQLexception</h1>");
        } finally {
            out.close();
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}

