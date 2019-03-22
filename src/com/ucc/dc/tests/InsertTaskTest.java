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
	public void testDoPostWithValidInputDataTypeWeb() throws IOException, ServletException {
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
	 * by Reshma
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithHighBoundaryValidDeadlineValueTypeWeb() throws IOException, ServletException {
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
	 * by Reshma
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithHighInvalidDeadlineTypeWeb() throws IOException, ServletException {
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
	 * Test case with boundary input value (900000) for deadline and valid data for type parameters
	 *
	 * by Reshma
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithHighBoundaryInvalidDeadlineTypeWeb() throws IOException, ServletException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("web");
        when(request.getParameter("deadline")).thenReturn("900000");

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
	 * Test case with invalid input data (value < 1000) for deadline and valid data for type parameters
	 *
	 * by Suvarna Somavanshi
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithLowInvalidDeadlineTypeWeb() throws IOException, ServletException {
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
	 * by Suvarna Somavanshi
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithLowBoundaryInvalidDeadlineTypeData() throws IOException, ServletException {
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
	 * Test case with boundary input value (1000) for deadline and valid data for type parameters
	 *
	 * by Suvarna Somavanshi
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithLowBoundaryValidDeadlineTypeData() throws IOException, ServletException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("data");
        when(request.getParameter("deadline")).thenReturn("1000");

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
	 * Test case with invalid input data (value < 1000) for deadline and valid data for type parameters
	 *
	 * by Suvarna Somavanshi
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithLowInvalidDeadlineTypeData() throws IOException, ServletException {
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
	public void testDoPostWithHighInvalidDeadlineTypeData() throws IOException, ServletException {
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
	 * Test case for invalid input data for deadline (900000) and valid data for server type
	 * 
	 * by
	 *Adarsh Bhat
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithHighBoundaryValueValidDeadlineTypeData() throws IOException, ServletException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("data");
        when(request.getParameter("deadline")).thenReturn("900000");

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
	 * Test case with valid input data for type and deadline parameters
	 *
	 * by Adarsh Bhat
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithValidInputDataTypeData() throws IOException, ServletException {
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
	 * by Adarsh Bhat
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
	 * Freda Pinto
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithHighInvalidDeadlineAndTypeUndefined() throws IOException, ServletException {
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
	 * by 
	 * 	 Freda Pinto
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
	 * by 
	 	 * Freda Pinto
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostValidInputValueTypeUndefined() throws IOException, ServletException {
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
	 * Freda Pinto
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithValidInputDataTypeNetwork() throws IOException, ServletException {
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
	 * Test case with valid low boundary input data for deadline parameters type network
	 *
	 * by Bhargavi 
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithLowBoundaryValidTypeNetwork() throws IOException, ServletException {
		//fail("Not yet implemented");
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("network");
        when(request.getParameter("deadline")).thenReturn("1000");

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
	 * Test case with valid low boundary input data for deadline parameters type network
	 *
	 *  by Bhargavi 
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithHighBoundaryValidInputDataNetwork() throws IOException, ServletException {
		//fail("Not yet implemented");
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("network");
        when(request.getParameter("deadline")).thenReturn("900000");

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
	 * by Bhargavi 
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithLowInValidInputDataNetwork() throws IOException, ServletException {
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
	 * by Bhargavi 
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithUndefinedInputDataNetwork() throws IOException, ServletException {
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
	 * by Bhargavi 
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithHighInValidInputDataNetwork() throws IOException, ServletException {
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
	 * by Pooja
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithBoundaryValidDeadlineComputational() throws IOException, ServletException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("computational");
        when(request.getParameter("deadline")).thenReturn("900000");

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
	 * Test case with invalid input value (undefined) for deadline and valid data for type parameters
	 *
	 * by Pooja
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithUndefinedDeadlineTypeComputational() throws IOException, ServletException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("computational");
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
	 * by Pooja
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithInValidDeadlineTypeComputational() throws IOException, ServletException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("computational");
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
	 * by Pooja
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithBoundaryValidDeadlineTypeComputational() throws IOException, ServletException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("computational");
        when(request.getParameter("deadline")).thenReturn("1000");

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
	 * Test case with boundary input value (1000) for deadline and valid data for type parameters
	 *
	 * by Rohit
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithValidDeadlineTypeNetwork() throws IOException, ServletException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("computational");
        when(request.getParameter("deadline")).thenReturn("3500");

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
	 * Test case for negative input data for deadline (<0) and valid data for server type
	 * 
	 * by
	 * Rohit
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithNegativeDeadlineTypeUndefined() throws IOException, ServletException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("undefined");
        when(request.getParameter("deadline")).thenReturn("-1");

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
	 * Test case for negative input data for deadline (<0) and valid data for server type
	 * 
	 * by
	 * Rohit
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithNegativeDeadlineTypeData() throws IOException, ServletException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("data");
        when(request.getParameter("deadline")).thenReturn("-1");

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
	 * Test case for negative input data for deadline (<0) and valid data for server type
	 * 
	 * by
	 * Reshma 
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithNegativeDeadlineTypeWeb() throws IOException, ServletException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("web");
        when(request.getParameter("deadline")).thenReturn("-1");

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
	 * Test case for negative input data for deadline (<0) and valid data for server type
	 * 
	 * by
	 * Rohit
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithNegativeDeadlineTypeNetwork() throws IOException, ServletException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("network");
        when(request.getParameter("deadline")).thenReturn("-1");

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
	 * Test case for negative input data for deadline (<0) and valid data for server type
	 * 
	 * by
	 * Pooja
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostWithNegativeDeadlineTypComputation() throws IOException, ServletException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("computational");
        when(request.getParameter("deadline")).thenReturn("-1");

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
