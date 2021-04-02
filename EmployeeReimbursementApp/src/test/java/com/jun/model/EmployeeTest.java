package com.jun.model;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;

import org.junit.Test;

public class EmployeeTest {

	@Test
	public void testSetEmail() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		final Employee emp = new Employee();
		emp.setEmail("email@email.com");
		final Field field = emp.getClass().getDeclaredField("email");
		field.setAccessible(true);
		assertEquals(field.get(emp), "email@email.com");
	}
	
	@Test
	public void testGetEmail() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		final Employee emp = new Employee();
		final Field field = emp.getClass().getDeclaredField("email");
		field.setAccessible(true);
		field.set(emp, "magic");
		
		final String result = emp.getEmail();
		
		assertEquals(result, "magic");
	}
	
	@Test
	public void testGetLoginId() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		final Employee emp = new Employee();
		final Field field = emp.getClass().getDeclaredField("loginId");
		field.setAccessible(true);
		field.set(emp, 1);
		
		final int result = emp.getLoginId();
		
		assertEquals(result, 1);
	}
	@Test
	public void testSetLoginId() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		final Employee emp = new Employee();
		emp.setLoginId(1);
		final Field field = emp.getClass().getDeclaredField("loginId");
		field.setAccessible(true);
		assertEquals(field.get(emp), 1);
	}
	

	@Test
	public void testGetIsManager() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		final Employee emp = new Employee();
		final Field field = emp.getClass().getDeclaredField("isManager");
		field.setAccessible(true);
		field.set(emp, true);
		
		final boolean result = emp.isManager();
		
		assertEquals(result, true);
	}
	@Test
	public void testSetsIsManager() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		final Employee emp = new Employee();
		emp.setManager(true);
		final Field field = emp.getClass().getDeclaredField("isManager");
		field.setAccessible(true);
		assertEquals(field.get(emp), true);
	}
	
	@Test
	public void testGetFirstName() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		final Employee emp = new Employee();
		final Field field = emp.getClass().getDeclaredField("firstName");
		field.setAccessible(true);
		field.set(emp, "Jun");
		
		final String result = emp.getFirstName();
		
		assertEquals(result, "Jun");
	}
	
	@Test
	public void testSetLastName() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		final Employee emp = new Employee();
		emp.setLastName("Lei");
		final Field field = emp.getClass().getDeclaredField("lastName");
		field.setAccessible(true);
		assertEquals(field.get(emp), "Lei");
	}
	
	@Test
	public void testGetLastName() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		final Employee emp = new Employee();
		final Field field = emp.getClass().getDeclaredField("lastName");
		field.setAccessible(true);
		field.set(emp, "Lei");
		
		final String result = emp.getLastName();
		
		assertEquals(result, "Lei");
	}
	@Test
	public void testSetFirstName() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		final Employee emp = new Employee();
		emp.setFirstName("Jun");
		final Field field = emp.getClass().getDeclaredField("firstName");
		field.setAccessible(true);
		assertEquals(field.get(emp), "Jun");
	}


}
