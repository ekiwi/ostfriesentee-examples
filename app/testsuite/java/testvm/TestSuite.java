/*
 * TestSuite.java
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
 
package testvm;

import javax.ostfriesentee.Ostfriesentee;

import testvm.tests.AlignmentTest;
import testvm.tests.ArithmeticTest;
import testvm.tests.ArrayTest;
import testvm.tests.BitManipulationTest;
import testvm.tests.ClassHierarchyTest;
import testvm.tests.CompareTest;
import testvm.tests.ExceptionsTest;
import testvm.tests.FieldTest;
import testvm.tests.GarbageCollectionTest;
import testvm.tests.InheritanceTest;
import testvm.tests.InitialiserTest;
import testvm.tests.InvokeVirtualTest;
import testvm.tests.MD5Test;
import testvm.tests.MethodOverloadingTest;
import testvm.tests.RuntimeExceptionsTest;
import testvm.tests.StaticFieldsTest;
import testvm.tests.SwitchTest;
import testvm.tests.ThreadTest;

public class TestSuite
{
	
	public static void test()
	{
		try {
			Ostfriesentee.print("Running ArithmeticTest...\n");
			ArithmeticTest.test(000);
			Ostfriesentee.print("Running ArrayTest...\n");
			ArrayTest.test(100);
			Ostfriesentee.print("Running CompareTest...\n");
			CompareTest.test(200);
			Ostfriesentee.print("Running ClassHierarchyTest...\n");
			ClassHierarchyTest.test(300);
			Ostfriesentee.print("Running StaticFieldsTest...\n");
			StaticFieldsTest.test(400);
			Ostfriesentee.print("Running FieldTest...\n");
			FieldTest.test(500);
			Ostfriesentee.print("Running InitialiserTest...\n");
			InitialiserTest.test(600);
			Ostfriesentee.print("Running InvokeVirtualTest...\n");
			InvokeVirtualTest.test(700);
			Ostfriesentee.print("Running GarbageCollectionTest...\n");
			GarbageCollectionTest.test(800);
			Ostfriesentee.print("Running SwitchTest...\n");
			SwitchTest.test(900);
			Ostfriesentee.print("Running ThreadTest...\n");
			ThreadTest.test(1000);
			Ostfriesentee.print("Running InheritanceTest...\n");
			InheritanceTest.test(1100);
			Ostfriesentee.print("Running ExceptionsTest...\n");
			ExceptionsTest.test(1200);
			Ostfriesentee.print("Running MD5Test...\n");
	 		MD5Test.test(1300);
			Ostfriesentee.print("Running MethodOverloadingTest...\n");
			MethodOverloadingTest.test(1400);
			Ostfriesentee.print("Running RuntimeExceptionsTest...\n");
			RuntimeExceptionsTest.test(1500);
			Ostfriesentee.print("Running BitManipulationTest...\n");
			BitManipulationTest.test(1700);
			Ostfriesentee.print("Running AlignmentTest...\n");
			AlignmentTest.test(1800);
//			TryCatchTest.test(1900);
//			InfusionTest.test(2000);
			Ostfriesentee.print("Done...\n");
		} catch (Throwable t)
		{
			Ostfriesentee.print("Uncaught throwable: " + t.getMessage());
		}
		Ostfriesentee.printTestResults();
		//Ostfriesentee.print("All testsuites have passed succsessfuly.\n");
	}

	public static void main(String[] args)
	{
		try {
			test();
		} catch (Throwable t)
		{
			Ostfriesentee.print(t.toString());
		}
	}
}
