package com.ucc.dc.tests;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.mockito.Mockito;

import com.ucc.dc.servlets.InsertTask;

public class InsertTaskAllDefsTest  extends Mockito {

	/**
	 * Test validateInputs function without type parameter set
	 * Test for All defs path (1, 4, request)
	 */
	@Test
	public void testValidateInputsWithoutPassingType() {

        boolean expected = false;
        
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("deadline")).thenReturn("2000");
        
        boolean response = new InsertTask().validateInputs(request);
        
		assertTrue(expected == response);
	}

	/**
	 * Test validateInputs function with invalid type provided
	 * Test for All defs path (2, (11,t), isValidType)
	 */
	@Test
	public void testValidateInputsWithInvalidType() {

        boolean expected = false;
        
        HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getParameter("type")).thenReturn("undefined");
        when(request.getParameter("deadline")).thenReturn("2000");
        
        boolean response = new InsertTask().validateInputs(request);
        
		assertTrue(expected == response);
	}

	/**
	 * Test validateInputs with valid type provided
	 * Test for All defs path (7, (11,t), isValidType)
	 */
	@Test
	public void testValidateInputsWithValidType() {

        boolean expected = true;
        
        HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getParameter("type")).thenReturn("Web");
        when(request.getParameter("deadline")).thenReturn("2000");
        
        boolean response = new InsertTask().validateInputs(request);
        
		assertTrue(expected == response);
	}

	/**
	 * Test validateInputs with deadline as a valid value in the range given
	 * Test for All defs path (16, (17,t), deadline)
	 */
	@Test
	public void testValidateInputsWithValidDeadline() {

        boolean expected = true;
        
        HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getParameter("type")).thenReturn("Data");
        when(request.getParameter("deadline")).thenReturn("35000");
        
        boolean response = new InsertTask().validateInputs(request);
        
		assertTrue(expected == response);
	}
	
	/**
	 * Test validateInputs with deadline as integer outside the range of values checked for
	 * Test for All defs path (16, (17,f), deadline)
	 */
	@Test
	public void testValidateInputsWithInvalidIntegerAsDeadline() {

        boolean expected = false;
        
        HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getParameter("type")).thenReturn("Computational");
        when(request.getParameter("deadline")).thenReturn("20");
        
        boolean response = new InsertTask().validateInputs(request);
        
		assertTrue(expected == response);
	}

	/**
	 * Test validateInputs with deadline as an invalid string
	 * Test for All defs path (14, (15,f), deadlineStr)
	 */
	@Test
	public void testValidateInputsWithInvalidStringAsDeadline() {

        boolean expected = false;
        
        HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getParameter("type")).thenReturn("Computational");
		String deadline = "string";
        when(request.getParameter("deadline")).thenReturn(deadline);
        
        boolean response = new InsertTask().validateInputs(request);
        
		assertTrue(expected == response);
		assertTrue(InsertTask.isInteger(deadline) == response);
	}

	/**
	 * Test validateInputs with deadline as a negative integer.
	 * Test for All defs path (20, 25, isValidDeadline)
	 */
	@Test
	public void testValidateInputsWithNegativeIntegerAsDeadline() {

        boolean expected = false;
        
        HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getParameter("type")).thenReturn("Computational");
		String deadline = "-1";
        when(request.getParameter("deadline")).thenReturn(deadline);
        
        boolean response = new InsertTask().validateInputs(request);
        assertTrue(InsertTask.isInteger(deadline) == true);
		assertTrue(expected == response);
		
	}
	
}
