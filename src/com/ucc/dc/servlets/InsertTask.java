package com.ucc.dc.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ucc.dc.dao.ServerStackDao;
import com.ucc.dc.dao.TaskDao;
import com.ucc.dc.models.Hvac;
import com.ucc.dc.models.Task;
import com.ucc.dc.service.TaskService;

/**
 * Servlet implementation class InsertTask
 */
@WebServlet("/InsertTask")

public class InsertTask extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	ArrayList<Task> processedTasks = new ArrayList<Task>();
	public InsertTask() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("");
		
		request.setAttribute("taskAddedToQueue","Task Successfully added to Job Queue.... Please wait will analyzer searches for optimal server..!!");
		processRequest(request, response);
		forwardRequest(request, response, "/index.jsp");
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("Processing");
	TaskService taskService = new TaskService();
	
	
	//request.setAttribute("processedTasks", processedTasks);
	System.out.println(processedTasks);
	if (request.getParameter("getHvacStatus") != null) {
		System.out.println("Inside hvac status");
	   ServerStackDao hvac = new ServerStackDao();
	   ArrayList<Hvac>hvacStatus = hvac.getHvacStatus();
	   request.setAttribute("hvacStatus", hvacStatus);
	   System.out.println("hvacStatus Length: " + hvacStatus.size());
	   System.out.println("ProcessTask Length in HVAC: " + processedTasks.toString());
	   request.setAttribute("processedTasks", processedTasks);
	   //HttpSession session = request.getSession(false);
	  // Object o = session.getAttribute("processedTasks");
	   //request.setAttribute("processedTasks", o);
	  // System.out.println("Session ProcessTask : " + o.toString());
	}
	else if (request.getParameter("processingTask") != null) {
	    // Invoke action 2.
		 request.setAttribute("Processing Task.....", "processingtasklabel");
		 processedTasks = taskService.processTasks();
			System.out.println("ProcessTask Length in Processor: " + processedTasks.toString());
			
		System.out.println("Inside process task");
	}
	
	RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
	dispatcher.forward(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getParameter("name"));
		Task task = new Task(request.getParameter("name"),Integer.parseInt( request.getParameter("deadline")));
		TaskDao taskDao = new TaskDao();
		Calendar calendar = Calendar.getInstance();
		Timestamp currentTimestamp = new Timestamp(calendar.getTime().getTime());
		task.setArrivalTime(currentTimestamp);
		task.setProcessed(false);
		taskDao.insertTask(task);
		
		System.out.println(request.getParameter("deadline"));
	}
protected void processTask() {
	System.out.println("Processing");
	
}
	protected void forwardRequest(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		request.getRequestDispatcher(path).forward(request, response);
	}

}
