package com.ucc.dc.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.junit.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;

import com.ucc.dc.servlets.InsertTask;


public class InsertTaskTest extends Mockito{

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
	 * Test case for invalid input data for deadline (>=900000) and valid data for server type
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
	 * Test case for invalid input data for deadline (>=900000) and invalid type for server type
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
}
