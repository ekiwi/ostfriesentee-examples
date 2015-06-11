/*
 * InvokeVirtualTest.java
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

import testvm.classes.*;

public class InvokeVirtualTest
{
	
	public static void constructorTest(int testBase)
	{
		A a = new A();
		Ostfriesentee.assertTrue(testBase +  0, a.getX()==2);
		Ostfriesentee.assertTrue(testBase +  1, a.getY()==3);
		Ostfriesentee.assertTrue(testBase +  2, a.getSquaredLength()==2*2+3*3);

		a = new A(5,-2);
		Ostfriesentee.assertTrue(testBase +  3, a.getX()==5);
		Ostfriesentee.assertTrue(testBase +  4, a.getY()==-2);
		Ostfriesentee.assertTrue(testBase +  5, a.getSquaredLength()==5*5+-2*-2);
	}

	public static void inheritanceTest(int testBase)
	{
		A a, b, c, d;
		E e;
		
		a = new A();
		b = new B();
		c = new C();
		d = new D();
		e = new E();
		
		Ostfriesentee.assertTrue(testBase +  0, a.virtualMethod()==0);
		Ostfriesentee.assertTrue(testBase +  1, b.virtualMethod()==1);
		Ostfriesentee.assertTrue(testBase +  2, c.virtualMethod()==2);
		Ostfriesentee.assertTrue(testBase +  3, d.virtualMethod()==3);

		Ostfriesentee.assertTrue(testBase +  4, a.AInterfaceMethod()==0);
		Ostfriesentee.assertTrue(testBase +  5, b.AInterfaceMethod()==0);
		Ostfriesentee.assertTrue(testBase +  6, c.AInterfaceMethod()==0);
		Ostfriesentee.assertTrue(testBase +  7, d.AInterfaceMethod()==0);
		Ostfriesentee.assertTrue(testBase +  8, e.AInterfaceMethod()==1);
		
		AInterface aa, bb, cc, dd, ee;
		aa = a; bb = b; cc = c;	dd = d;	ee = e;
		Ostfriesentee.assertTrue(testBase +  9, aa.AInterfaceMethod()==0);
		Ostfriesentee.assertTrue(testBase + 10, bb.AInterfaceMethod()==0);
		Ostfriesentee.assertTrue(testBase + 11, cc.AInterfaceMethod()==0);
		Ostfriesentee.assertTrue(testBase + 12, dd.AInterfaceMethod()==0);
		Ostfriesentee.assertTrue(testBase + 13, ee.AInterfaceMethod()==1);
	}
	
	public static void test(int testBase)
	{
		
		constructorTest(testBase + 00);
		inheritanceTest(testBase + 20);
		
	}
	

}
