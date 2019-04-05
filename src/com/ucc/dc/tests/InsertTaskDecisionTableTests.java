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

public class InsertTaskDecisionTableTests extends Mockito {

	/**
	 * Test case for rule 1 (deadline < 1000, type = "WEB") - TC1
	 */
	@Test
	public void testDoPostWithLowDealineWebType() throws IOException, ServletException {
		
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("WEB");
        when(request.getParameter("deadline")).thenReturn("980");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new InsertTask().doPost(request, response);

        verify(request, atLeast(1)).getParameter("type");
        writer.flush();
        
        JSONObject actual = new JSONObject(stringWriter.toString());
        JSONAssert.assertEquals("{isReject:true}", actual, false);
	}
	
	/**
	 * Test case for rule 2 (deadline in [1000, 900000], type = "WEB") - TC2
	 */
	@Test
	public void testDoPostWithValidDeadlineWebType() throws IOException, ServletException {
		
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("WEB");
        when(request.getParameter("deadline")).thenReturn("5500");

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
	 * Test case for rule 3 (deadline > 900000, type = "WEB") - TC3
	 */
	@Test
	public void testDoPostWithHighDeadlineWebType() throws IOException, ServletException {
		//fail("Not yet implemented");
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("WEB");
        when(request.getParameter("deadline")).thenReturn("900001");

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
	 * Test case for rule 4 (deadline = invalid string, type = "WEB") - TC4
	 */
	@Test
	public void testDoPostWithInvalidDeadlineWebType() throws IOException, ServletException {
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
	 * Test case for rule 5 (deadline in [1000, 900000], type = "DATA") - TC5
	 */
	@Test
	public void testDoPostWithValidDeadlineDataType() throws IOException, ServletException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("DATA");
        when(request.getParameter("deadline")).thenReturn("3000");

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
	 * Test case for rule 6 (deadline > 900000, type = "DATA") - TC6
	 */
	@Test
	public void testDoPostWithHighDeadlineDataType() throws IOException, ServletException {

		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("DATA");
        when(request.getParameter("deadline")).thenReturn("900025");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new InsertTask().doPost(request, response);

        verify(request, atLeast(1)).getParameter("type");
        writer.flush();
        
        JSONObject actual = new JSONObject(stringWriter.toString());
        JSONAssert.assertEquals("{isReject:true}", actual, false);
	}
	
	/**
	 * Test case for rule 7 (deadline < 1000, type = invalid_string) - TC7
	 */
	@Test
	public void testDoPostWithLowDeadlineInvalidType() throws IOException, ServletException {
		
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("testType");
        when(request.getParameter("deadline")).thenReturn("500");

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
	 * Test case for rule 8 (deadline in [1000, 900000], type = "invalid_string") - TC8
	 */
	@Test
	public void testDoPostWithValidDeadlineInvalidType() throws IOException, ServletException {
		//fail("Not yet implemented");
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("undefinedType");
        when(request.getParameter("deadline")).thenReturn("4500");

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
	 * Test case for rule 9 (deadline > 900000, type = "network") - TC9
	 */
	@Test
	public void testDoPostWithHighDeadlineNetworkType() throws IOException, ServletException {
		//fail("Not yet implemented");
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("type")).thenReturn("network");
        when(request.getParameter("deadline")).thenReturn("900015");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new InsertTask().doPost(request, response);

        verify(request, atLeast(1)).getParameter("type");
        writer.flush(); // it may not have been flushed yet...
        
        JSONObject actual = new JSONObject(stringWriter.toString());
        JSONAssert.assertEquals("{isReject:true}", actual, false);
	}

}
