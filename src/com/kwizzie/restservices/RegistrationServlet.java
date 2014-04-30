package com.kwizzie.restservices;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.kwizzie.dao.PlayerDAO;
import com.kwizzie.model.Player;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet
@MultipartConfig
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 2L;
    
	@Autowired
	PlayerDAO playerDAO;

	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/plain;charset=UTF-8");
        final String path = request.getSession().getServletContext().getRealPath("/kwizzieMedia");
        final Part filePart = request.getPart("image");
        final String fileName = getFileName(filePart);
        OutputStream out = null;
        InputStream filecontent = null;
        final PrintWriter writer = response.getWriter();

        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String emailID = request.getParameter("emailID");
        
       	
        try {
            File f = new File(path+File.separator+"user_"+username+"_"+fileName);
        	//File f = new File(path + File.separator+fileName);
            //f.mkdirs();
            
        	out = new FileOutputStream(f);
            
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            String photoURL = request.getServerName()+":"+request.getServerPort()+"/KwizzieServer/kwizzieMedia/"+"user_"+username+"_"+fileName;
           	Player player = new Player(username, password, photoURL, name, emailID);
           	playerDAO.save(player);
            writer.print("1");
        } catch(Exception e){
        	e.printStackTrace();
        }
            
	}
    	private String getFileName(final Part part) {
            final String partHeader = part.getHeader("content-disposition");
            for (String content : part.getHeader("content-disposition").split(";")) {
                if (content.trim().startsWith("filename")) {
                    return content.substring(
                            content.indexOf('=') + 1).trim().replace("\"", "");
                }
            }
            return null;
        }
}
