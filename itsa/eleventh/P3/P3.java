/* Problem 3 抓作業抄襲 */

import java.io.*;
import java.util.Vector;
import static java.lang.System.out;

class Formatter {
	public Vector<Integer> getTwoNumbers(String str) {
		String[] strArray = str.split("\\s");
		Vector<Integer> twoNumbers = new Vector<Integer>();
		twoNumbers.add(Integer.parseInt(strArray[0]));
		twoNumbers.add(Integer.parseInt(strArray[1]));

		return twoNumbers;
	}
}

class MyReader {
	BufferedReader br;
	Formatter formatter;

	MyReader(BufferedReader br) {
		this.br = br;
		this.formatter = new Formatter();
	}


	public int getNumberOfCopies() throws IOException {
		String str = br.readLine();
		Vector<Integer> twoNumbers = formatter.getTwoNumbers(str);

		return twoNumbers.get(1);
	}

	public Vector<Vector<Integer>> getCopies(int numbers) throws IOException {
		Vector<Vector<Integer>> copies = new Vector<Vector<Integer>>();
		String str;
		String[] strArray;

		for (int i = 0; i < numbers; i++) {
			copies.add(formatter.getTwoNumbers(br.readLine()));
		}

		return copies;
	}
}

class MyPrinter {
	public static void print_r(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
					out.print(array[i][j] + " ");
			}
			out.println();
		}
	}

	public static void print(String message, boolean displayOutput) {
		if (displayOutput) {
			out.println(message);
		}
	}
}

public class P3 {

	/* 如果中介者只有一位, return 其他抄襲功課的同學
	 * return -1 表示找到兩位中介者
	 * return 0 表示並無抄襲
	 * return 其他數字代表中介只有一位, 而數字代表抄襲的同學號碼
	 * */
	static int findCopyCat(Vector<Integer> firstCopy, Vector<Integer> secondCopy) {
		boolean hasMediator = false;
		Vector<Integer> mediators = new Vector<Integer>();
		Vector<Integer> copyCats = new Vector<Integer>();

		for (int i : firstCopy) {
			for (int j : secondCopy) {
				if (i == j) {
					mediators.add(j);
				}
			}
		}

		int size = mediators.size();

		if (2 == size) {
			return -1;
		}

		if (1 == size) {
			int mediator = mediators.get(0);

			for (int j : secondCopy) {
				if (mediator != j) {
					return j;
				}
			}
		}

		return 0;
	}

	static int getMaxCountOfPeopleWhoCopies(Vector<Vector<Integer>> copies) {
		// 把 displayOutput 改為 true 來顯示測試用輸出
		boolean displayOutput = false;
		int count = 0;
		int copyCat = 0;
		Vector<Vector<Integer>> kinds = new Vector<Vector<Integer>>();

		Vector<Integer> firstCopy, secondCopy;

		for (int i = 0; i < copies.size() - 1; i++) {
			for (int j = i + 1; j < copies.size(); j++) {

				firstCopy = copies.get(i);
				secondCopy = copies.get(j);
				copyCat = findCopyCat(firstCopy, secondCopy);

				MyPrinter.print("i = " + i + " j  = " + j, displayOutput);
				MyPrinter.print(firstCopy + " 和 " + secondCopy + "比較", displayOutput);

				// 找到兩個中介者
				if (-1 == copyCat) {
					copies.remove(j);
					j--;
				}	// 找到一個中介者
				else if (0 != copyCat) {
					copies.remove(j);
					MyPrinter.print("第" + (j + 1) + "份已被刪除, " + copyCat + "已加入" + firstCopy, displayOutput);
					firstCopy.add(copyCat);
					MyPrinter.print("變成" + firstCopy + "\n", displayOutput);
					j--;
				}

			}
		}

		MyPrinter.print(copies.toString(), displayOutput);
		MyPrinter.print("==================", displayOutput);

		int max = 0;
		int size = 0;
		for (Vector<Integer> copy : copies) {
			size = copy.size();
			max = (size > max) ? size : max;
		}

		return max;
	}

	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		MyReader reader = new MyReader(br);
		MyPrinter printer = new MyPrinter();
		Vector<Vector<Integer>> copies;
		int count, numbers;
		String str, output;

		while ((str = br.readLine()) != null) {
			count = Integer.parseInt(str);
			output = "";

			for (int i = 0; i < count; i++) {
				numbers = reader.getNumberOfCopies();
				copies = reader.getCopies(numbers);
				output += getMaxCountOfPeopleWhoCopies(copies) + "\n";
				if (i != count - 1) {
					// 最後一行不須接空白
					br.readLine();
					output += "\n";
				}
			}

			out.print(output);
		}
	}

}
