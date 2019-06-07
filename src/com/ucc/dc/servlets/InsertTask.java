package com.ucc.dc.servlets;

import java.io.IOException;
import java.io.PrintWriter;

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
import com.ucc.dc.models.TaskResponse;
import com.ucc.dc.service.TaskService;

/**
 * Servlet implementation class InsertTask
 */
@WebServlet("/InsertTask")

public class InsertTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int id;

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
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("");
		
		TaskResponse tr = new TaskResponse();
		
		request.setAttribute("taskAddedToQueue","Task Successfully added to Job Queue.... Please wait will analyzer searches for optimal server..!!");
		boolean isValidInput = validateInputs(request);
		// if input is valid only then process request 
		if (isValidInput) {
			processRequest(request, response);
			//forwardRequest(request, response, "/index.jsp");
			TaskService service = new TaskService();
			ArrayList<Task> pTasks = service.processTasks();
			for(Task task : pTasks) {
				if(task.getTaskId() == id) {
					System.out.println("Task:" + task);
					Integer serverId = task.getServerId();
					if (serverId != null) {
						tr.setServerId(serverId);
						tr.setReject(false);
					}
					break;
				}
			}
		}
		
		PrintWriter pw = response.getWriter();
		response.setContentType("application/json");
		pw.print(tr);
		pw.flush();
		
		
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
		Task.taskTypes taskType = Task.taskTypes.valueOf(request.getParameter("type").toUpperCase());
		
		Task task = new Task("Task",Integer.parseInt( request.getParameter("deadline")), taskType);
		TaskDao taskDao = new TaskDao();
		Calendar calendar = Calendar.getInstance();
		Timestamp currentTimestamp = new Timestamp(calendar.getTime().getTime());
		task.setArrivalTime(currentTimestamp);
		task.setProcessed(false);
		id = taskDao.insertTask(task);
		
	}
	
	public boolean validateInputs(HttpServletRequest request) {
		boolean isValidType = false;
		boolean returnValue, isValidDeadline;
		
		// validate type input
		String type = request.getParameter("type");
		for (Task.taskTypes t : Task.taskTypes.values()) {
			if (t.name().equalsIgnoreCase(type)) {
				isValidType = true;
				break;
			}
		}
		
		if (isValidType == false) {
			returnValue = false;
		} else {
			// validate deadline input
			String deadlineStr = request.getParameter("deadline");
			if (isInteger(deadlineStr)) {
				int deadline = Integer.parseInt(deadlineStr);
				if (deadline >= 1000 && deadline <= 900000) {
		        	isValidDeadline = true;
		        } else  {
		        	isValidDeadline = false;
		        }
			} else {
				isValidDeadline = false;
			}
			returnValue = isValidDeadline;
		}
		
		return returnValue;
	}
	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
	
protected void processTask() {
	System.out.println("Processing");
	
}
	protected void forwardRequest(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		request.getRequestDispatcher(path).forward(request, response);
	}

}
