/* Problem 5. 有多少個零 */

import static java.lang.System.out;
import java.io.*;

class Formatter {
	public int[] getTwoNumbers(String str) {
		String[] strArray = str.split("\\s");
		return new int[] {Integer.parseInt(strArray[0]), Integer.parseInt(strArray[1])};
	}

	public int countZeros(String str) {
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			if ('0' == str.charAt(i)) {
				count++;
			}
		}

		return count;
	}
}

public class P5 {

	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Formatter formatter = new Formatter();
		String str;
		String output;

		while ((str = br.readLine()) != null) {
			int times = Integer.parseInt(str);
			int zeros;

			for (int i = 0; i < times; i++) {
				zeros = 0;
				int[] numbers = formatter.getTwoNumbers(br.readLine());
				output = "";
				for (int j = numbers[0] + 1; j < numbers[1]; j++) {
					String word = j + "";
					zeros += formatter.countZeros(word);
				}

				output = (0 == zeros) ? "No Solution" : zeros + "";
				output += (i == (times - 1)) ? "" : "\n";

				out.println(output);
			}

		}
	}
}
