
public class SolNode {

	int id;
	int rate;
	int startDate;
	
	public SolNode(int id, int rate, int startDate) {
		this.id = id;
		this.rate = rate;
		this.startDate = startDate;
	}
	
	public int getId() {
		return id;
	}
	
	public int getRate() {
		return rate;
	}
	
	public int getStartDate() {
		return startDate;
	}
}
