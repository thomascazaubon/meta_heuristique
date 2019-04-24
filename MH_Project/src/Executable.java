import java.io.IOException;

public class Executable {

	public static void main(String[] args) {
		System.out.println("test");
		try {
			Reader.readGraph("test.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
