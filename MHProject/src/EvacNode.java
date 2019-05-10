import java.util.ArrayList;

public class EvacNode extends Node{

	int pop;
	int rate;
	ArrayList<Integer> route;
	
	
	public EvacNode(int id, int pop, int rate)
	{
		super(id);
		this.pop = pop;
		this.rate = rate;
		this.route = new ArrayList<Integer>();
	}
	
	public void addNodeRoute(int i)
	{
		this.route.add(i);
	}
	
	public ArrayList<Integer> getRoute()
	{
		return route;
	}
	
	public int getPop() {
		return pop;
	}
	
	public int getRate() {
		return rate;
	}
	
	public void setPop(int pop) {
		this.pop = pop;
	}
	
	public ArrayList<Node> buildRoute(Graph g)
	{
		ArrayList<Node> resRoute = new ArrayList<Node>();
		for (Integer nr : this.route) {
			resRoute.add(g.getNode(nr));
		}
		
		return resRoute;
	}
	

}
