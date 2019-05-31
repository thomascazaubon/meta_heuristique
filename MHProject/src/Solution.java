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
	
	public String toStringSol(Graph g)
	{
		String content = "";
		content += g.getName() + "\n";
		content += Integer.toString(EvacNode.getNbEvacNode()) + "\n";
		for (SolNode sn : this.nodes)
		{
			content += Integer.toString(sn.getId()) + " " + Integer.toString(((SolNode) sn).getRate()) + " " + Integer.toString(sn.startDate) + "\n";
		}
		content += "valid\n";
		content += this.cost;
		return content;
	}

	public void baisserStartDate() {
		for (SolNode s : this.nodes)
		{
			if (s.getStartDate()>0)
			{
				s.setStartDate(s.getStartDate()-1);
			}
		}
		
	}
	
}
