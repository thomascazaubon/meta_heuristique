
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
	
	public int getStartDate() {
		return startDate;
	}

	public void setStartDate(int i) {
		this.startDate = i;
	}
	
	public int getRate() {
		return rate;
	}

	public void setRate(int i) {
		this.rate = i;
	}
}
