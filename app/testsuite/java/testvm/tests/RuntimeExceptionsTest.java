/*
 * RuntimeExceptionsTest.java
 * 
 * Copyright (c) 2008-2010 CSIRO, Delft University of Technology.
 * 
 * This file is part of Darjeeling.
 * 
 * Darjeeling is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Darjeeling is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with Darjeeling.  If not, see <http://www.gnu.org/licenses/>.
 */
 
package testvm.tests;

import javax.ostfriesentee.Ostfriesentee;

import testvm.classes.A;
import testvm.classes.B;

public class RuntimeExceptionsTest
{
	
	public static int recursionDepth = 0;
	
	static void recurse()
	{
		int a,b,c,d,e,f,g,h,i,j,k,l,m,n;
		recursionDepth++;
		recurse();
	}
	
	private static class FinallyTest
	{
		public int finallyCalled = 0;
		
		public void test2()
		{
			try {
				throw new RuntimeException();
			} finally
			{
				finallyCalled++;
				// compiler should re-throw?
			}
		}
		
		public void test()
		{
			try {
				test2();
			} finally
			{
				finallyCalled++;
				// compiler should re-throw?
			}
		}
	}
	
	public static void test(int testBase)
	{
		// class cast exception test
		try {
			A a = new A();
			B b = (B)a;
			Ostfriesentee.assertTrue(testBase + 0, false);
		} catch (ClassCastException ex)
		{
			Ostfriesentee.assertTrue(testBase + 0, true);
		} catch (Exception ex)
		{
			Ostfriesentee.assertTrue(testBase + 0, false);
		
		}
		// finally in method test
		FinallyTest finallyTest = null;
		try {
			finallyTest = new FinallyTest();
			finallyTest.test();
			Ostfriesentee.assertTrue(testBase + 1, false);
		} catch (RuntimeException ex)
		{
			Ostfriesentee.assertTrue(testBase + 1, finallyTest.finallyCalled==2);
		}
		
		// thread state exception test
		try {
			Thread thread = new Thread(
					new Runnable() {
						public void run()
						{
							// do nothing
						}
					}
					);
			thread.start();
			
			// wait for the thread to complete
			Thread.sleep(10);
			
			// try re-starting the thread
			thread.start();
			
			Ostfriesentee.assertTrue(testBase + 2, false);
		} catch (IllegalThreadStateException ex)
		{
			Ostfriesentee.assertTrue(testBase + 2, true);
		} catch (Throwable t)
		{
			Ostfriesentee.assertTrue(testBase + 2, false);
		}
		
		// out of stack space test
		try {
			recurse();
			Ostfriesentee.assertTrue(testBase + 3, false);
		} catch (StackOverflowError err)
		{
			Ostfriesentee.assertTrue(testBase + 3, true);
		} catch (OutOfMemoryError t)
		{
			Ostfriesentee.assertTrue(testBase + 3, true);
		}
		
	}

}
