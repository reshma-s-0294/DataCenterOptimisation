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

import com.ucc.dc.dao.TaskDao;
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
		processRequest(request, response);
		forwardRequest(request, response, "/index.jsp");
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("Processing");
	TaskService taskService = new TaskService();
	ArrayList<Task> processedTasks = taskService.processTasks();
	System.out.println("ProcessTask Length: " + processedTasks.toString());
	request.setAttribute("processingtasklabel","Processing Task");
	request.setAttribute("processedTasks", processedTasks);
	System.out.println(processedTasks);
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
