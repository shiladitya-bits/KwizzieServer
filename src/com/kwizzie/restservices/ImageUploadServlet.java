package com.kwizzie.restservices;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;

import com.kwizzie.dao.PrivateQuizRoomDAO;
import com.kwizzie.dao.PublicQuizRoomDAO;
import com.kwizzie.dao.QuestionCategoryDAO;
import com.kwizzie.model.AnswerType;
import com.kwizzie.model.AudioQuestion;
import com.kwizzie.model.MCQAnswerType;
import com.kwizzie.model.PictureQuestion;
import com.kwizzie.model.QRAnswerType;
import com.kwizzie.model.Question;
import com.kwizzie.model.QuestionCategory;
import com.kwizzie.model.QuestionLocation;
import com.kwizzie.model.TextAnswerType;
import com.kwizzie.model.VideoQuestion;

@WebServlet
@MultipartConfig
public class ImageUploadServlet extends HttpServlet{

	@Autowired
    PrivateQuizRoomDAO privateQuizRoomDAO;

	@Autowired
	PublicQuizRoomDAO publicQuizRoomDAO;
	
	@Autowired
	QuestionCategoryDAO questionCategoryDAO;
	
	private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/plain;charset=UTF-8");

    	String pathInfo = request.getPathInfo(); 
    	String[] pathParts = pathInfo.split("/");
    	String roomType = pathParts[1];
    	String roomID = pathParts[2]; 
    	String questionType = pathParts[3];
//    	final String path = "/home/shiladitya/javaee-workspace/KwizzieServer/WebContent/kwizzieMedia";
        final String path = request.getSession().getServletContext().getRealPath("/kwizzieMedia");
        final Part filePart = request.getPart("picture");
        final String fileName = getFileName(filePart);
        OutputStream out = null;
        InputStream filecontent = null;
        final PrintWriter writer = response.getWriter();

        try {
            File f = new File(path+File.separator+fileName);
        	//File f = new File(path + File.separator+fileName);
            //f.mkdirs();
            
        	out = new FileOutputStream(f);
            
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            writer.println("New file " + fileName + " created at " + path);
            if(roomType.equals("private")){
                addQuestionToPrivateRoom(roomID, path, fileName, questionType, request);
            } else {
            	addQuestionToCategory(roomID, path, fileName, questionType, request);
            }
        } catch (FileNotFoundException fne) {
            writer.println("You either did not specify a file to upload or are "
                    + "trying to upload a file to a protected or nonexistent "
                    + "location.");
            writer.println("<br/> ERROR: " + fne.getMessage());

        } finally {
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
            if (writer != null) {
                writer.close();
            }
        }
    }
    
    private void addQuestionToCategory(String roomID, String path,
			String fileName, String questionType, HttpServletRequest request) {
		
    	QuestionCategory category = questionCategoryDAO.get(roomID);
    	if(category == null){
    		return;
    	}
    	Question q = null;
    	String questionTitle = request.getParameter("questionTitle");
    	
    	//location
    	String latitude = request.getParameter("latitude");
    	String longitude = request.getParameter("longitude");
    	String locationName = request.getParameter("destinationInfo");
    	QuestionLocation location = null;
    	if(checkLocationInput(latitude, longitude, locationName)){
    		location = new QuestionLocation(Double.parseDouble(latitude), Double.parseDouble(longitude), 1000, locationName, "");
    	}
    	
    	
    	//answer type
    	AnswerType answerType =null;
    	String answerTypeString = request.getParameter("answerType");
    	if("mcq".equals(answerTypeString)){
    		String option1 = request.getParameter("optiona");
    		String option2 = request.getParameter("optionb");
    		String option3 = request.getParameter("optionc");
    		String option4 = request.getParameter("optiond");
    		String correctOption = request.getParameter("options");
    		answerType = new MCQAnswerType(getAnswerList(option1, option2, option3, option4), Integer.parseInt(correctOption));
    	} else if("text".equals(answerTypeString)){
    		String correctAns = request.getParameter("correctAns");
    		if(questionType.equals("text")){
    			answerType = new TextAnswerType(correctAns);
    		} else {
    			answerType = new QRAnswerType(correctAns);
    		}
    		
    	}
    	if(questionType.equals("picture")){
    		q = new PictureQuestion(path+"/"+fileName,location, questionTitle, answerType, false);
    	} else if(questionType.equals("audio")){
    		q = new AudioQuestion(path+"/"+fileName,location, questionTitle, answerType, false);
    	} else if(questionType.equals("video")){
    		q = new VideoQuestion(path+"/"+fileName,location, questionTitle, answerType, false);
    	} 
    	if(q!=null){
    		publicQuizRoomDAO.addQuestion(roomID, q);	
    	}
    }
    
    private List<String> getAnswerList(String option1, String option2,
			String option3, String option4) {
    	List<String> options = new ArrayList<String>();
    	if(option1!=null && option1.trim()!=""){
    		options.add(option1);
    	}
     	if(option2!=null && option2.trim()!=""){
    		options.add(option2);
    	}
     	if(option3!=null && option3.trim()!=""){
    		options.add(option3);
    	}
     	if(option4!=null && option4.trim()!=""){
    		options.add(option4);
    	}
    	
		return options;
	}

	private boolean checkLocationInput(String latitude, String longitude, String name) {
    	if(latitude==null || longitude==null || name==null){
    		return false;
    	}
    	if(latitude.trim()=="" || longitude.trim()=="" || name.trim() ==""){
    		return false;
    	}
    	return true;
	}

	private void addQuestionToPrivateRoom(String roomID, String path, String fileName,
			String questionType, HttpServletRequest request) {
    	Question q = null;
    	String questionTitle = request.getParameter("questionTitle");

    	//location
    	String latitude = request.getParameter("latitude");
    	String longitude = request.getParameter("longitude");
    	String locationName = request.getParameter("destinationInfo");
    	QuestionLocation location = null;
    	if(checkLocationInput(latitude, longitude, locationName)){
    		location = new QuestionLocation(Double.parseDouble(latitude), Double.parseDouble(longitude), 1000, locationName, "");
    	}
    	
    	//answer type
    	AnswerType answerType =null;
    	String answerTypeString = request.getParameter("answerType");
    	if("mcq".equals(answerTypeString)){
    		String option1 = request.getParameter("optiona");
    		String option2 = request.getParameter("optionb");
    		String option3 = request.getParameter("optionc");
    		String option4 = request.getParameter("optiond");
    		String correctOption = request.getParameter("options");
    		answerType = new MCQAnswerType(getAnswerList(option1, option2, option3, option4), Integer.parseInt(correctOption));
    	} else if("text".equals(answerTypeString)){
    		String correctAns = request.getParameter("correctAns");
    		if(questionType.equals("text")){
    			answerType = new TextAnswerType(correctAns);
    		} else {
    			answerType = new QRAnswerType(correctAns);
    		}
    		
    	}
    	if(questionType.equals("picture")){
    		q = new PictureQuestion(path+"/"+fileName,location, questionTitle, answerType, false);
    	} else if(questionType.equals("audio")){
    		q = new AudioQuestion(path+"/"+fileName,location, questionTitle, answerType, false);
    	} else if(questionType.equals("video")){
    		q = new VideoQuestion(path+"/"+fileName,location, questionTitle, answerType, false);
    	}
    	
    	if(q != null){
    		privateQuizRoomDAO.addQuestionToRoom(roomID, q);
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
