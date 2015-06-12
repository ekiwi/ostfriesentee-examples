/*
 * ArithmeticTest.java
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

public class ArithmeticTest
{
	
	private static int rotate_left(int x, int n)
	{
		return (x << n) | (x >>> (32 - n));
	}
	
	private static void testByte(int testBase)
	{
		byte a,b,c;
		
		
		a = 64;
		b = 2;
		
		// exercise standard arithmetic
		Ostfriesentee.assertTrue(testBase+ 0, (c=(byte)(a+b))==66);
		Ostfriesentee.assertTrue(testBase+ 1, a-b==62);
		Ostfriesentee.assertTrue(testBase+ 2, a/b==32);
		Ostfriesentee.assertTrue(testBase+ 3, a*b==128);

		Ostfriesentee.assertTrue(testBase+ 4, -a==-64);
		Ostfriesentee.assertTrue(testBase+ 5, a%b==0);

		// shr
		Ostfriesentee.assertTrue(testBase+ 6, a>>1==32);
		Ostfriesentee.assertTrue(testBase+ 7, a>>3==8);
		Ostfriesentee.assertTrue(testBase+ 8, a>>5==b);

		// shl
		Ostfriesentee.assertTrue(testBase+ 9, a<<1==128);
		Ostfriesentee.assertTrue(testBase+10, b<<3==16);
		Ostfriesentee.assertTrue(testBase+11, b<<7==256);
		
		// iinc
		a++;
		Ostfriesentee.assertTrue(testBase+12, a==65);
		a--;
		Ostfriesentee.assertTrue(testBase+13, a==64);
		a+=64;
		Ostfriesentee.assertTrue(testBase+14, a==-128);
		a-=64;

		// exercise boolean logic
		Ostfriesentee.assertTrue(testBase+15, (a & b)==0);
		Ostfriesentee.assertTrue(testBase+16, (a | b)==66);
		Ostfriesentee.assertTrue(testBase+17, (a ^ b)==66);
		
		// overflow tests
		a = 0x7f;
		b = 1;
		c = 2;
		Ostfriesentee.assertTrue(testBase + 18, a + b == 128);
		
		Ostfriesentee.assertTrue(testBase + 19, (byte)(a+b) == -128);
		Ostfriesentee.assertTrue(testBase + 20, (byte)(a + b + b) == -127);
		
		Ostfriesentee.assertTrue(testBase + 21, (a + b) / c == 64);
		Ostfriesentee.assertTrue(testBase + 22, (byte)((a+b) / c) == 64);
		
	}

	private static void testShort(int testBase)
	{
		short a,b,c;
		
		a = 64;
		b = 2;
		
		// exercise standard arithmetic
		Ostfriesentee.assertTrue(testBase+ 0, a+b==66);
		Ostfriesentee.assertTrue(testBase+ 1, a-b==62);
		Ostfriesentee.assertTrue(testBase+ 2, a/b==32);
		Ostfriesentee.assertTrue(testBase+ 3, a*b==128);

		Ostfriesentee.assertTrue(testBase+ 4, -a==-64);
		Ostfriesentee.assertTrue(testBase+ 5, a%b==0);

		// shr
		Ostfriesentee.assertTrue(testBase+ 6, a>>1==32);
		Ostfriesentee.assertTrue(testBase+ 7, a>>3==8);
		Ostfriesentee.assertTrue(testBase+ 8, a>>5==b);

		// shl
		Ostfriesentee.assertTrue(testBase+ 9, a<<1==128);
		Ostfriesentee.assertTrue(testBase+10, b<<3==16);
		Ostfriesentee.assertTrue(testBase+11, b<<7==256);
		
		// iinc
		a++;
		Ostfriesentee.assertTrue(testBase+12, a==65);
		a--;
		Ostfriesentee.assertTrue(testBase+13, a==64);
		a+=1000;
		Ostfriesentee.assertTrue(testBase+14, a==1064);
		a-=1000;

		// exercise boolean logic
		Ostfriesentee.assertTrue(testBase+15, (a & b)==0);
		Ostfriesentee.assertTrue(testBase+16, (a | b)==66);
		Ostfriesentee.assertTrue(testBase+17, (a ^ b)==66);
		
		// overflow tests
		a = 0x00007fff;
		b = 1;
		c = 2;
		Ostfriesentee.assertTrue(testBase + 18, a + b == 32768);
		
		Ostfriesentee.assertTrue(testBase + 19, (short)(a+b) == -32768);
		Ostfriesentee.assertTrue(testBase + 20, (short)(a + b + b) == -32767);
		
		Ostfriesentee.assertTrue(testBase + 21, (a + b) / c == 16384);
		Ostfriesentee.assertTrue(testBase + 22, (short)((a+b) / c) == 16384);
		
		// Test ArithmeticException from division by zero
		try
		{
			a = 1;
			b = 0;
			short d = (short)(a / b);
			Ostfriesentee.assertTrue(testBase + 23, false);
		} catch (ArithmeticException ex)
		{
			Ostfriesentee.assertTrue(testBase + 23, true);
		} catch (Throwable t)
		{
			Ostfriesentee.assertTrue(testBase + 23, false);
		}
	}
	
	private static void testInt(int testBase)
	{
		int a,b;
		
		a = 64;
		b = 2;
		
		// exercise standard arithmetic
		Ostfriesentee.assertTrue(testBase+ 0, a+b==66);
		Ostfriesentee.assertTrue(testBase+ 1, a-b==62);
		Ostfriesentee.assertTrue(testBase+ 2, a/b==32);
		Ostfriesentee.assertTrue(testBase+ 3, a*b==128);

		Ostfriesentee.assertTrue(testBase+ 4, -a==-64);
		Ostfriesentee.assertTrue(testBase+ 5, a%b==0);

		// shr
		Ostfriesentee.assertTrue(testBase+ 6, a>>1==32);
		Ostfriesentee.assertTrue(testBase+ 7, a>>3==8);
		Ostfriesentee.assertTrue(testBase+ 8, a>>5==b);

		// shl
		Ostfriesentee.assertTrue(testBase+ 9, a<<1==128);
		Ostfriesentee.assertTrue(testBase+10, b<<3==16);
		Ostfriesentee.assertTrue(testBase+11, b<<7==256);
		
		// iinc
		a++;
		Ostfriesentee.assertTrue(testBase+12, a==65);
		a--;
		Ostfriesentee.assertTrue(testBase+13, a==64);
		a+=1000;
		Ostfriesentee.assertTrue(testBase+14, a==1064);
		a-=1000;

		// exercise boolean logic
		Ostfriesentee.assertTrue(testBase+15, (a & b)==0);
		Ostfriesentee.assertTrue(testBase+16, (a | b)==66);
		Ostfriesentee.assertTrue(testBase+17, (a ^ b)==66);
		
		// exercise LDC
		a = 123456;
		Ostfriesentee.assertTrue(testBase+18, (a >>> 16) == 1);
		Ostfriesentee.assertTrue(testBase+19, (a >>> 8) == 482);
		Ostfriesentee.assertTrue(testBase+20, (-a >>> 8) == 16776733);

		a = 0x0000ffff;
		Ostfriesentee.assertTrue(testBase+21, (a >>> 8) == 255);
		a = 0x00ffffff;
		Ostfriesentee.assertTrue(testBase+22, (a >>> 16) == 255);
		a = 0xffffffff;
		Ostfriesentee.assertTrue(testBase+23, (a >>> 24) == 255);
		
		Ostfriesentee.assertTrue(testBase+24, rotate_left(1, 1) == 2);   
		Ostfriesentee.assertTrue(testBase+25, rotate_left(-1, 1) == -1);   
		Ostfriesentee.assertTrue(testBase+26, rotate_left(-2, 1) == -3);
		Ostfriesentee.assertTrue(testBase+27, rotate_left(-2, 16) == -65537);   

		Ostfriesentee.assertTrue(testBase+28, 0 - 1 == -1);
		
		// this loop will stall if IINC isn't handled properly
		for (int i=0; i<33000; i++)
		{
		}
		Ostfriesentee.assertTrue(testBase + 29, true);
		
		// Test ArithmeticException from division by zero
		try
		{
			a = 1;
			b = 0;
			int d = a / b;
			Ostfriesentee.assertTrue(testBase + 30, false);
		} catch (ArithmeticException ex)
		{
			Ostfriesentee.assertTrue(testBase + 30, true);
		} catch (Throwable t)
		{
			Ostfriesentee.assertTrue(testBase + 30, false);
		}
		
	}
	
	private static void testLong(int testBase)
	{
		long a,b;
		a = 64;
		b = 2;
		
		// exercise standard arithmetic
		Ostfriesentee.assertTrue(testBase+ 0, a+b==66);
		Ostfriesentee.assertTrue(testBase+ 1, a-b==62);
		Ostfriesentee.assertTrue(testBase+ 2, a/b==32);
		//Darjeeling.printf("Result is: "+a/b);
		Ostfriesentee.assertTrue(testBase+ 3, a*b==128);

		Ostfriesentee.assertTrue(testBase+ 4, -a==-64);
		Ostfriesentee.assertTrue(testBase+ 5, a%b==0);

		// shr
		Ostfriesentee.assertTrue(testBase+ 6, a>>1==32);
		Ostfriesentee.assertTrue(testBase+ 7, a>>3==8);
		Ostfriesentee.assertTrue(testBase+ 8, a>>5==b);

		// shl
		Ostfriesentee.assertTrue(testBase+ 9, a<<1==128);
		Ostfriesentee.assertTrue(testBase+10, b<<3==16);
		Ostfriesentee.assertTrue(testBase+11, b<<7==256);
		
		// iinc
		a++;
		Ostfriesentee.assertTrue(testBase+12, a==65);
		a--;
		Ostfriesentee.assertTrue(testBase+13, a==64);
		a+=1000;
		Ostfriesentee.assertTrue(testBase+14, a==1064);
		a-=1000;

		// exercise boolean logic
		Ostfriesentee.assertTrue(testBase+15, (a & b)==0);
		Ostfriesentee.assertTrue(testBase+16, (a | b)==66);
		Ostfriesentee.assertTrue(testBase+17, (a ^ b)==66);
		
		// exercise LDC
		a = 123456;
		Ostfriesentee.assertTrue(testBase+18, (a >>> 16) == 1);
		Ostfriesentee.assertTrue(testBase+19, (a >>> 8) == 482);
		Ostfriesentee.assertTrue(testBase+20, (-a >>> 8) == 72057594037927453L);

		a = 0x0000ffff;
		Ostfriesentee.assertTrue(testBase+21, (a >>> 8) == 255);
		a = 0x00ffffff;
		Ostfriesentee.assertTrue(testBase+22, (a >>> 16) == 255);
		a = 0xffffffff00000000L;
		Ostfriesentee.assertTrue(testBase+23, (a >>> 24) == 0x000000ffffffff00L);
		
		// Test ArithmeticException from division by zero
		try
		{
			a = 1;
			b = 0;
			long d = a / b;
			Ostfriesentee.assertTrue(testBase + 24, false);
		} catch (ArithmeticException ex)
		{
			Ostfriesentee.assertTrue(testBase + 24, true);
		} catch (Throwable t)
		{
			Ostfriesentee.assertTrue(testBase + 24, false);
		}
		
	}	

	public static void test(int testBase)
	{
		Ostfriesentee.print("\ttestByte...\n");
		testByte(testBase);
		Ostfriesentee.print("\ttestShort...\n");
		testShort(testBase+25);
		Ostfriesentee.print("\ttestInt...\n");
		testInt(testBase+50);
		Ostfriesentee.print("\ttestLong...\n");
		testLong(testBase+80);
	}

}
