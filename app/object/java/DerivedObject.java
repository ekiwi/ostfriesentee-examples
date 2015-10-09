/*
 * DerivedObject.java
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
 * along with Darjeeling.  If not, see <http://www.gnu.org/licenses/>.
 */

import javax.ostfriesentee.Ostfriesentee;

public class DerivedObject extends SimpleObject
{
	private int b;
	private int c;

	public DerivedObject(int a, int b, int c) {
		super(a, -100);
		Ostfriesentee.print("\nDerivedObject Constructor: begin\n\n");
		this.b = b;
		Ostfriesentee.print("this.b = " + this.b + ";\n");
		this.c = c;
		Ostfriesentee.print("this.c = " + this.c + ";\n");
		Ostfriesentee.print("\nDerivedObject Constructor: end\n\n");
	}

	public int getB() { return this.b; }
	public int getC() { return this.c; }
	public int getSuperB() { return super.getB(); }
}
