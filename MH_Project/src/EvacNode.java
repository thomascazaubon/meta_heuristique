import java.util.ArrayList;

public class EvacNode extends Node{

	int pop;
	int rate;
	ArrayList<Node> route;
	
	
	public EvacNode(int id, int pop, int rate)
	{
		super(id);
		this.pop = pop;
		this.rate = rate;
		this.route = new ArrayList<Node>();
	}
	
	public void addRoute(Node n)
	{
		this.route.add(n);
	}
	
	public ArrayList<Node> getRoute()
	{
		return route;
	}
	
	public int getPop() {
		return pop;
	}
	
	public int getRate() {
		return rate;
	}

}
