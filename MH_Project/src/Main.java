import java.io.IOException;

public class Main {
	
	public static void main(String[] args) {
		Graph g;
		try {
			g = Reader.readGraph("instances/exemple1.full");
			g.display();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Invalid path !");
		}
	}
}
