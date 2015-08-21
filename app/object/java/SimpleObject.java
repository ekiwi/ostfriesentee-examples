/*
 * HelloWorld.java
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

public class SimpleObject
{
	private int a;
	private int b;

	public SimpleObject(int a, int b) {
		Ostfriesentee.print("\nSimpleObject Constructor: begin\n\n");
		this.a = a;
		this.b = b;
		// Ostfriesentee.print("this.b = " + b + ";\n");
		Ostfriesentee.print("\nSimpleObject Constructor: end\n\n");
	}

	public int getA() { return this.a; }
}
