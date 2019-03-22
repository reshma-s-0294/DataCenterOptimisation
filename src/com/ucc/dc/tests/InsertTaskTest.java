package com.ucc.dc.tests;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;

import com.ucc.dc.service.TaskService;
import com.ucc.dc.servlets.InsertTask;


public class InsertTaskTest extends Mockito{

	@Before
	public void setUp() throws Exception {
		// resetting utilization and capacity data in db to ensure test cases pass.
		// Ideally these parameters would be updated based on constant monitoring of the servers
		TaskService service = new TaskService();
		service.resetUtilizationAndCapacity();
	}
	
	/**
	 * Test case with valid input data for type and deadline parameters
	 *
	 * by Reshma Surendran
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithValidInputData() throws IOException, ServletException {
		//fail("Not yet implemented");

		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("web");
        when(request.getParameter("deadline")).thenReturn("2000");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new InsertTask().doPost(request, response);

        verify(request, atLeast(1)).getParameter("type");
        writer.flush(); 
        
        JSONObject actual = new JSONObject(stringWriter.toString());
        JSONAssert.assertEquals("{isReject:false}", actual, false);
	}
	/**
	 * Test case with valid input data (value < 1000) for deadline and valid data for type parameters
	 *
	 * by Pooja
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithHighBoundaryValidDeadlineValue() throws IOException, ServletException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("web");
        when(request.getParameter("deadline")).thenReturn("899999");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new InsertTask().doPost(request, response);

        verify(request, atLeast(1)).getParameter("type");
        writer.flush();
        
        JSONObject actual = new JSONObject(stringWriter.toString());
        JSONAssert.assertEquals("{isReject:false}", actual, false);
	}
	/**
	 * Test case with boundary input value (900001) for deadline and valid data for type parameters
	 *
	 * by Pooja
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithHighBoundaryInvalidDeadline() throws IOException, ServletException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("web");
        when(request.getParameter("deadline")).thenReturn("900001");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new InsertTask().doPost(request, response);

        verify(request, atLeast(1)).getParameter("type");
        writer.flush();
        
        JSONObject actual = new JSONObject(stringWriter.toString());
        JSONAssert.assertEquals("{isReject:true,serverId:-1}", actual, false);
	}
	/**
	 * Test case with invalid input data (value < 1000) for deadline and valid data for type parameters
	 *
	 * by Suvarna Somavanshi
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithLowInvalidDeadline_2() throws IOException, ServletException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("web");
        when(request.getParameter("deadline")).thenReturn("200");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new InsertTask().doPost(request, response);

        verify(request, atLeast(1)).getParameter("type");
        writer.flush();
        
        JSONObject actual = new JSONObject(stringWriter.toString());
        JSONAssert.assertEquals("{isReject:true,serverId:-1}", actual, false);
	}
	/**
	 * Test case with boundary input value (999) for deadline and valid data for type parameters
	 *
	 * by Reshma Surendran
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithLowBoundaryInvalidDeadline() throws IOException, ServletException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("data");
        when(request.getParameter("deadline")).thenReturn("999");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new InsertTask().doPost(request, response);

        verify(request, atLeast(1)).getParameter("type");
        writer.flush();
        
        JSONObject actual = new JSONObject(stringWriter.toString());
        JSONAssert.assertEquals("{isReject:true,serverId:-1}", actual, false);
	}
	/**
	 * Test case with invalid input data (value < 1000) for deadline and valid data for type parameters
	 *
	 * by Reshma Surendran
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithLowInvalidDeadline() throws IOException, ServletException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("data");
        when(request.getParameter("deadline")).thenReturn("1");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new InsertTask().doPost(request, response);

        verify(request, atLeast(1)).getParameter("type");
        writer.flush();
        
        JSONObject actual = new JSONObject(stringWriter.toString());
        JSONAssert.assertEquals("{isReject:true,serverId:-1}", actual, false);
	}
	/**
	 * Test case for invalid input data for deadline (>900000) and valid data for server type
	 * 
	 * by
	 * Adarsh Bhat
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithHighInvalidDeadline() throws IOException, ServletException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("data");
        when(request.getParameter("deadline")).thenReturn("910001");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new InsertTask().doPost(request, response);

        verify(request, atLeast(1)).getParameter("type");
        writer.flush();
        
        JSONObject actual = new JSONObject(stringWriter.toString());
        JSONAssert.assertEquals("{isReject:true,serverId:-1}", actual, false);
	}
	
	/**
	 * Test case with valid input data for type and deadline parameters
	 *
	 * by Pooja
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithValidInputData_2() throws IOException, ServletException {
		//fail("Not yet implemented");
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("data");
        when(request.getParameter("deadline")).thenReturn("1100");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new InsertTask().doPost(request, response);

        verify(request, atLeast(1)).getParameter("type");
        writer.flush(); // it may not have been flushed yet...
        
        JSONObject actual = new JSONObject(stringWriter.toString());
        JSONAssert.assertEquals("{isReject:false}", actual, false);
	}
	/**
	 * Test case with invalid input data (undefined enum) for type and valid data for deadline parameters
	 *
	 * by Reshma Surendran
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithUndefinedTypeValue() throws IOException, ServletException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("test");
        when(request.getParameter("deadline")).thenReturn("2000");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new InsertTask().doPost(request, response);

        verify(request, atLeast(1)).getParameter("type");
        writer.flush();
        
        JSONObject actual = new JSONObject(stringWriter.toString());
        JSONAssert.assertEquals("{isReject:true,serverId:-1}", actual, false);
	}

	

	
	
	/**
	 * Test case for invalid input data for deadline (>900000) and invalid type for server type
	 * 
	 * by
	 * Adarsh Bhat
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithHighInvalidDeadlineAndType() throws IOException, ServletException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("undefined");
        when(request.getParameter("deadline")).thenReturn("900001");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new InsertTask().doPost(request, response);

        //verify(request, atLeast(1)).getParameter("type");
        writer.flush();
        
        JSONObject actual = new JSONObject(stringWriter.toString());
        JSONAssert.assertEquals("{isReject:true,serverId:-1}", actual, false);
	}
	
	

	/**
	 * Test case with invalid input data (undefined enum) for type and deadline parameters
	 *
	 * by Pooja
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithUndefinedTypeAndDeadlineValue() throws IOException, ServletException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("temp");
        when(request.getParameter("deadline")).thenReturn("test");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new InsertTask().doPost(request, response);

        verify(request, atLeast(1)).getParameter("type");
        writer.flush();
        
        JSONObject actual = new JSONObject(stringWriter.toString());
        JSONAssert.assertEquals("{isReject:true,serverId:-1}", actual, false);
	}


	/**
	 * Test case with invalid input data (undefined enum) for type and valid data for deadline parameters
	 *
	 * by Suvarna Somavanshi
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithUndefinedTypeValue_2() throws IOException, ServletException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("other");
        when(request.getParameter("deadline")).thenReturn("2020");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new InsertTask().doPost(request, response);

        verify(request, atLeast(1)).getParameter("type");
        writer.flush();
        
        JSONObject actual = new JSONObject(stringWriter.toString());
        JSONAssert.assertEquals("{isReject:true,serverId:-1}", actual, false);
	}
	/**
	 * Test case with valid input data for type and deadline parameters
	 *
	 * by Suvarna Somavanshi
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithValidInputData_3() throws IOException, ServletException {
		//fail("Not yet implemented");
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("network");
        when(request.getParameter("deadline")).thenReturn("3070");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new InsertTask().doPost(request, response);

        verify(request, atLeast(1)).getParameter("type");
        writer.flush(); // it may not have been flushed yet...
        
        JSONObject actual = new JSONObject(stringWriter.toString());
        JSONAssert.assertEquals("{isReject:false}", actual, false);
	}
	/**
	 * Test case with Invalid input data for type and deadline parameters
	 *
	 * by Rohit 
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithInValidInputData_3() throws IOException, ServletException {
		//fail("Not yet implemented");
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("network");
        when(request.getParameter("deadline")).thenReturn("0");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new InsertTask().doPost(request, response);

        verify(request, atLeast(1)).getParameter("type");
        writer.flush(); // it may not have been flushed yet...
        
        JSONObject actual = new JSONObject(stringWriter.toString());
        JSONAssert.assertEquals("{isReject:true}", actual, false);
	}
	/**
	 * Test case with Invalid input data for type and deadline parameters
	 *
	 * by Rohit 
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithInValidInputData_4() throws IOException, ServletException {
		//fail("Not yet implemented");
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("network");
        when(request.getParameter("deadline")).thenReturn("undefined");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new InsertTask().doPost(request, response);

        verify(request, atLeast(1)).getParameter("type");
        writer.flush(); // it may not have been flushed yet...
        
        JSONObject actual = new JSONObject(stringWriter.toString());
        JSONAssert.assertEquals("{isReject:true}", actual, false);
	}
	/**
	 * Test case with Invalid input data for type and deadline parameters
	 *
	 * by Rohit 
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithInValidInputData_5() throws IOException, ServletException {
		//fail("Not yet implemented");
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("network");
        when(request.getParameter("deadline")).thenReturn("900500");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new InsertTask().doPost(request, response);

        verify(request, atLeast(1)).getParameter("type");
        writer.flush(); // it may not have been flushed yet...
        
        JSONObject actual = new JSONObject(stringWriter.toString());
        JSONAssert.assertEquals("{isReject:true}", actual, false);
	}

	/**
	 * Test case with boundary input value (900000) for deadline and valid data for type parameters
	 *
	 * by Suvarna Somavanshi
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithBoundaryValidDeadline_1() throws IOException, ServletException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("computaional");
        when(request.getParameter("deadline")).thenReturn("900000");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new InsertTask().doPost(request, response);

        verify(request, atLeast(1)).getParameter("type");
        writer.flush();
        
        JSONObject actual = new JSONObject(stringWriter.toString());
        JSONAssert.assertEquals("{isReject:true,serverId:-1}", actual, false);
	}
	/**
	 * Test case with invalid input value (undefined) for deadline and valid data for type parameters
	 *
	 * by Rohit
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithInValidDeadline_2() throws IOException, ServletException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("computaional");
        when(request.getParameter("deadline")).thenReturn("undefined");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new InsertTask().doPost(request, response);

        verify(request, atLeast(1)).getParameter("type");
        writer.flush();
        
        JSONObject actual = new JSONObject(stringWriter.toString());
        JSONAssert.assertEquals("{isReject:true,serverId:-1}", actual, false);
	}
	/**
	 * Test case with invalid input value (0) for deadline and valid data for type parameters
	 *
	 * by Rohit 
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithInValidDeadline_3() throws IOException, ServletException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("computaional");
        when(request.getParameter("deadline")).thenReturn("0");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new InsertTask().doPost(request, response);

        verify(request, atLeast(1)).getParameter("type");
        writer.flush();
        
        JSONObject actual = new JSONObject(stringWriter.toString());
        JSONAssert.assertEquals("{isReject:true,serverId:-1}", actual, false);
	}
	/**
	 * Test case with boundary input value (1000) for deadline and valid data for type parameters
	 *
	 * by Rohit
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithBoundaryValidDeadline_4() throws IOException, ServletException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("computaional");
        when(request.getParameter("deadline")).thenReturn("1000");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new InsertTask().doPost(request, response);

        verify(request, atLeast(1)).getParameter("type");
        writer.flush();
        
        JSONObject actual = new JSONObject(stringWriter.toString());
        JSONAssert.assertEquals("{isReject:true,serverId:-1}", actual, false);
	}

	
	
}
