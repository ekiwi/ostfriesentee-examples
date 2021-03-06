/*
 * CompareTest.java
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

public class CompareTest
{
	
	public static void test(int testBase)
	{
		int a = 1, b = 2, c = 1;
		// test equal
		if (a==c) Ostfriesentee.assertTrue(testBase + 0, true); else 
			Ostfriesentee.assertTrue(testBase + 0, false);
		if (a==b) Ostfriesentee.assertTrue(testBase + 1, false); else 
			Ostfriesentee.assertTrue(testBase + 1, true);

		// test not equal
		if (a!=b) Ostfriesentee.assertTrue(testBase + 2, true); else 
			Ostfriesentee.assertTrue(testBase + 2, false);
		if (a!=c) Ostfriesentee.assertTrue(testBase + 3, false); else 
			Ostfriesentee.assertTrue(testBase + 3, true);
		
		// null test
		Object o = null;
		Ostfriesentee.assertTrue(testBase + 4, o==null);
		o = new Object();
		Ostfriesentee.assertTrue(testBase + 5, o!=null);

	}	

}
