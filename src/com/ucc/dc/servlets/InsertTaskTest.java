package com.ucc.dc.servlets;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class InsertTaskTest extends Mockito{

	@Test
	public void testDoPostHttpServletRequestHttpServletResponse() throws IOException, ServletException {
		//fail("Not yet implemented");
		HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        when(request.getParameter("name")).thenReturn("task99");
        when(request.getParameter("deadline")).thenReturn("1");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new InsertTask().doPost(request, response);

        verify(request, atLeast(1)).getParameter("name"); // only if you want to verify username was called...
        writer.flush(); // it may not have been flushed yet...
        assertTrue(stringWriter.toString().contains("serverId"));
	}

}
