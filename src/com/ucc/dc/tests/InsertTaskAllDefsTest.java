package com.ucc.dc.tests;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.mockito.Mockito;

import com.ucc.dc.servlets.InsertTask;

public class InsertTaskAllDefsTest  extends Mockito {

	/**
	 * Test for All defs path (2, (11,t), isValidType)
	 */
	@Test
	public void testValidateInputsForPath2() {

        boolean expected = false;
        
        HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getParameter("type")).thenReturn("undefined");
        when(request.getParameter("deadline")).thenReturn("2000");
        
        boolean response = new InsertTask().validateInputs(request);
        
		assertTrue(expected == response);
	}

}
