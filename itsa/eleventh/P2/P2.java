/* Problem 2. 小白鼠走方格 */

import static java.lang.System.out;
import java.util.Vector;
import java.io.*;

class MyReader {
	BufferedReader br;
	MyStringFormatter formatter;

	MyReader(BufferedReader br) {
		this.br = br;
		formatter = new MyStringFormatter();
	}

	public int getSide() throws IOException {
		return Integer.parseInt(br.readLine());
	}

	public int[][] getSquare(int side) throws IOException {
		int[][] square = new int[side][side];

		for (int i = 0; i < side; i++) {
			int[] points = formatter.lineToPoints(br.readLine());

			for (int j = 0; j < side; j++) {
				square[i][j] = points[j];
			}
		}

		String newLine = br.readLine();

		return square;
	}
}

class MyStringFormatter {

	public int[] lineToPoints(String str) {
		String[] strArray = str.split("\\s");
		int[] intArray = new int[strArray.length];

		for (int i = 0; i < strArray.length; i++) {
			intArray[i] = Integer.parseInt(strArray[i]);
		}
		
		return intArray;
	}
}

class Mice {
	int currentX;
	int currentY;
	int[][] square;
	Vector<Integer> record;
	MyPrinter printer;

	public int getLongestDistance(int[][] square) {
		this.currentX = 0;
		this.currentY = 0;
		this.square = square;
		this.record = new Vector<Integer>();
		this.printer = new MyPrinter();

		move(currentX, currentY);

		return record.size();
	}

	public void move(int currentX, int currentY) {
		this.currentX = currentX;
		this.currentY = currentY;

		record.add(square[this.currentX][this.currentY]);

		Vector<int[]> paths = findPaths(square, this.currentX, this.currentY);

		for (int[] path : paths) {
			int x = path[0];
			int y = path[1];

			//printer.printStatus(currentX, currentY, record, square, paths);

			if (record.contains(square[x][y])) {
				//out.println(square[x][y] + "重複了, 不可走! \n=============");
				continue;
			}
			else {
				//out.println("=== 移動到" + square[x][y] + " ===\n");
				move(x, y);
			}

		}
	}

	public Vector<int[]> findPaths(int[][] square, int currentX, int currentY) {
		Vector<int[]> paths = new Vector<int[]>();
		int squareLength = square.length;

		// 上
		if ((currentX - 1) >= 0) {
			paths.add(new int[] {currentX - 1, currentY});
		}

		// 右
		if ((currentY + 1) <= (squareLength - 1)) {
			paths.add(new int[] {currentX, currentY + 1});
		}
		
		// 下
		if ((currentX + 1) <= (squareLength - 1)) {
			paths.add(new int[] {currentX + 1, currentY});
		}

		// 左
		if ((currentY - 1) >= 0) {
			paths.add(new int[] {currentX, currentY - 1});
		}

		return paths;
	}
}

class MyPrinter {

	public static void print_r(Vector<Integer> record) {
		Object[] array = record.toArray();

		for (Object point : record) {
			out.print(point + " ");
		}
		out.println();
	}

	public static void printPaths(Vector<int[]> paths) {
		out.println("範圍:");
		for (int[] point : paths) {
			out.println(point[0] + " " + point[1]);
		}
	}

	public static void print_r(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
					out.print(array[i][j] + " ");
			}
			out.println();
		}
	}

	public void printDistance(int distance) {
		out.println(distance + "\n");
	}

	public void printStatus(int currentX, int currentY, Vector<Integer> record, int[][] square, Vector<int[]> paths) {
		out.println("當前的點: " + square[currentX][currentY]);
		out.println("踩過的點:");
		print_r(record);
		printPaths(paths);
		out.println();
		print_r(square);
	}

}

public class P2 {

	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		MyReader reader = new MyReader(br);
		MyPrinter printer = new MyPrinter();
		Mice mice;
		String str;

		while ((str = br.readLine()) != null) {
			int count = Integer.parseInt(str);

			for (int i = 0; i < count; i++) {
				int side = reader.getSide();
				int[][] square = reader.getSquare(side);

				mice = new Mice();
				printer.printDistance(mice.getLongestDistance(square));
			}
		}
	}
}
