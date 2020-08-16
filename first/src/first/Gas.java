package first;

import java.util.Scanner;

public class Gas {

	/*
	 * Encode the original string by finding sequences in the string where the
	 * same character repeats. Replace each such sequence by a token consisting
	 * of: the number of characters in the sequence followed by the repeating
	 * character. Return the encoded string.
	 */
	public static String encode(String original) {

		String s = "";
		int x = 0;
		while (x < original.length()) {

			if ((x != 0) && (original.charAt(x) == original.charAt(x - 1))) {

				int count = 1;
				int index = x;

				while ((index < original.length())
						&& (original.charAt(index) == original.charAt(x))) {

					count = count + 1;
					index++;

				}

				s = s + count + original.charAt(x);
				x = index;

			} else {
				if (x + 1 < original.length()) {
					if (original.charAt(x) != original.charAt(x + 1)) {
						s = s + original.charAt(x);
					}
				} else {
					s = s + original.charAt(x);
				}
				x++;
			}

		}

		return s;

	}

	/*
	 * Decodes the original string encoded with the encode method. Returns the
	 * decoded string.
	 * 
	 * YOUR decode METHOD MUST BE RECURSIVE, do not use while, do/while, or for
	 * loops
	 */

	public static String decode(String original) {

		if (original.length() <= 1) {
			return original;
		}

		else {
			char c = original.charAt(0);

			if (Character.isDigit(c) == true) {
				char c2 = original.charAt(1);
				int num = Character.getNumericValue(c);
				if (num > 1) {
					c--;

					String newOriginalString = (c) + original.substring(1);

					return c2 + decode(newOriginalString);
				} else {
					return c2 + decode(original.substring(2));
				}

			} else {

				return c + decode(original.substring(1));

			}

		}

	}

	public static void main(String[] args) {

		String x = encode("qwwwwwwwwweeeeerrtyyyyyqqqqwEErTTT");

		System.out.println(x);

		System.out.println(decode("q9w5e2rt5y4qw2Er3T"));
		
	}

}
