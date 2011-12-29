/* Problem 4. Broken numbers */

import static java.lang.System.out;
import java.io.*;

public class P4 {

	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str, output;
		String secret = "abcdefghi";

		while ((str = br.readLine()) != null) {
			output = "";

			for (int i = 0; i < str.length(); i++) {
				char letter = str.charAt(i);

				if (Character.isDigit(letter)) {
					output += secret.charAt(letter - 48 - 1);
				}
			}

			if ("" == output) {
				output = "No Solution";
			}

			out.println(output);
		}
	}

}
