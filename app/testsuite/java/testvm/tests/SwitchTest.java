/*
 * SwitchTest.java
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

public class SwitchTest
{

	public static void test(int testBase)
	{
		
		// tableswitch
		int testNr = testBase;
		for (int i=-1; i<4; i++)
		{
			switch(i)
			{
				case 0: Ostfriesentee.assertTrue(testNr, i==0); break;
				case 1: Ostfriesentee.assertTrue(testNr, i==1); break;
				case 2: Ostfriesentee.assertTrue(testNr, i==2); break;
				case 3: Ostfriesentee.assertTrue(testNr, i==3); break;
				default: Ostfriesentee.assertTrue(testNr, i<0||i>=8); break;
			}
			testNr++;
		}
		
		testNr = testBase + 10;
		for (int i=-100; i<900; i+=100)
		{
			switch(i)
			{
				case 000: Ostfriesentee.assertTrue(testNr, i==000); break;
				case 100: Ostfriesentee.assertTrue(testNr, i==100); break;
				case 200: Ostfriesentee.assertTrue(testNr, i==200); break;
				case 300: Ostfriesentee.assertTrue(testNr, i==300); break;
				case 400: Ostfriesentee.assertTrue(testNr, i==400); break;
				case 500: Ostfriesentee.assertTrue(testNr, i==500); break;
				case 600: Ostfriesentee.assertTrue(testNr, i==600); break;
				case 700: Ostfriesentee.assertTrue(testNr, i==700); break;
				default: Ostfriesentee.assertTrue(testNr, i<0||i>700); break;
			}
			testNr++;
		}
		
	}

}
