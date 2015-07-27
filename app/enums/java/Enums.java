/*
 * Enums.java
 *
 * Copyright (c) 2015 Kevin Laeufer <kevin.laeufer@rwth-aachen.de>
 *
 * This file is part of Ostfriesentee.
 *
 * Ostfriesentee is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Ostfriesentee is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Ostfriesentee.  If not, see <http://www.gnu.org/licenses/>.
 */

public class Enums
{
	public static void main(String args[])
	{
		System.out.println("Enum Test");


		{
			System.out.println("Color Constants");
			ColorConstants c0 = new ColorConstants(ColorConstants.Blue);
			System.out.println("Color0 Value: " + c0.get());
			ColorConstants c1 = new ColorConstants(ColorConstants.Red);
			System.out.println("Color1 Value: " + c1.get());
			System.out.println("Color0 == Color1: " + (c0.get() == c1.get()));
			c1.set(ColorConstants.Blue);
			System.out.println("Color1 Value: " + c1.get());
			System.out.println("Color0 == Color1: " + (c0.get() == c1.get()));
		}

		{
			System.out.println("Color Static Class");
			ColorStaticClass c0 = ColorStaticClass.Blue;
			System.out.println("Color0 Value: " + c0.getValue());
			ColorStaticClass c1 = ColorStaticClass.Red;
			System.out.println("Color1 Value: " + c1.getValue());
			System.out.println("Color0 == Color1: " + (c0 == c1));
			c1 = ColorStaticClass.Blue;
			System.out.println("Color1 Value: " + c1.getValue());
			System.out.println("Color0 == Color1: " + (c0 == c1));
		}

	}
}
