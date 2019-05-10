import java.util.ArrayList;

public class Solution {

	ArrayList<SolNode> nodes;
	int cost;
	
	public Solution (ArrayList<SolNode> nodes, int cost) {
		this.nodes = nodes;
		this.cost = cost;	
	}
	
	public ArrayList<SolNode> getNodes(){
		return nodes;
	}
	
	public int getCost() {
		return cost;
	}
	
	public void display() {
		System.out.println("************ SOLUTION ************");
		System.out.println("Number of evac nodes : " + nodes.size());
		for(SolNode n : nodes) {
			System.out.println("   => " + n.getId() + " [rate : " + n.getRate() + " | start date : " + n.getStartDate() + "]");
		}
		System.out.println("Cost of the solution is " + cost + ".");
		System.out.println("******** END OF SOLUTION *********");
	}
	
}
