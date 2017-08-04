package com.guestbook.views;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.amazonaws.services.simpleworkflow.flow.worker.SynchronousActivityTaskPoller;
import com.guestbook.controllers.DynamoImplementation;
import com.guestbook.controllers.RDSImplementation;
import com.guestbook.controllers.S3Implementation;

import com.guestbook.models.MessageModel;

/**
 * Servlet implementation class MessageDemo
 */
//
public class MessageDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//declaration of the objects
	private MessageModel msgObject;
	private DynamoImplementation dbobject;
	private  S3Implementation s3object;
	private RDSImplementation rdsobject;
	
	private static final int THRESHOLD_SIZE 	= 1024 * 1024 * 3; 	// 3MB 
	private static final int MAX_FILE_SIZE 		= 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE 	= 1024 * 1024 * 50; // 50MB
	File storeFile=null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageDemo() {
        super();
        
        //Initialization of objects
        msgObject = new MessageModel();
        s3object = new S3Implementation();
		
        //UnComment the below line, if you are performing Dynamo DB Lab
         dbobject = new DynamoImplementation();
        
        //UnComment the below line, if you are performing RDS Lab
		//rdsobject = new RDSImplementation();
	
       
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sender=null,message=null;
		
		// TODO Auto-generated method stub
		//code for uploading file into uploads directory 
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(THRESHOLD_SIZE);
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(MAX_FILE_SIZE);
		upload.setSizeMax(MAX_REQUEST_SIZE);
		
		ArrayList <String> array = 	 new ArrayList<String>();
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart)
 
		try {
		
			// parses the request's content to extract file data
			List formItems = upload.parseRequest(request);
			Iterator iter = formItems.iterator();
		
			
			// iterates over form's fields
			while (iter.hasNext()) 
			{
				
					FileItem item = (FileItem) iter.next();
					// processes only fields that are not form fields
					if (!item.isFormField()) 
					{
						String fileName = new File(item.getName()).getName();
						
						//Here, we are setting Filepath to the uploads directory inside our tomcat's webapps directory
						String catalina_dir = System.getProperty("catalina.home");
						File file = new File(catalina_dir+"/webapps/uploads/");
						file.mkdir();
						String filePath=  file.getPath()+"/"+fileName;
						
						
						
						 storeFile = new File(filePath);
						 
						
						// saves the file on disk
						item.write(storeFile);
						msgObject.setImagefile(storeFile);
						
					}
					
					if (item.isFormField()) //your code for getting form fields
	                {
						
	                    String name = item.getFieldName();
	                    String value = item.getString();
	                    array.add(value);
	                    
	
	                }
				
		
				}
			


			System.out.println("file uploaded into uploads folder");
		} catch (Exception ex) {
			System.out.println("File unable to upload into Uploads Folder."+ex.getMessage());
		}
		

		msgObject.setImagefileFileName(msgObject.getImagefile().getName());


		 	msgObject.setSender(array.get(0));
            msgObject.setMessage(array.get(1));

		
		String objurl,result=null;
		
		try {
		
			objurl = s3object.uploadImage(msgObject.getImagefileFileName(), msgObject.getImagefile());
			msgObject.setObjecturl(objurl);
		
		
		//UnComment the below line, if you are performing RDS Lab
		// result = rdsobject.InsertMessages(msgObject);
			
		//UnComment the below line, if you are performing Dynamo DB Lab
		result =  dbobject.InsertMessages(msgObject);
		
		RequestDispatcher dis = getServletContext().getRequestDispatcher("/index.jsp");
		
		//UnComment the below line, if you are performing RDS Lab
		//request.setAttribute("msgList", rdsobject.retriveMessages());
		
		//UnComment the below line, if you are performing Dynamo DB Lab
		request.setAttribute("msgList", dbobject.retriveMessages());
		
		dis.forward(request,response);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
