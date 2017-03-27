import java.util.List;

public class SetObject {
	private String name;
	private int cost;
	private List<Integer> set;
	private int totalCost;
	private List<Integer> costs;

	

	public SetObject(String name, int cost) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.cost = cost;
	}

	private void addTotalCost(List<Integer> newCost){
		
		for(Integer cost: newCost){
			this.totalCost = this.totalCost + cost; 
		}		
	}
	
	
	// ==================================================== GETTERS AND SETTERS =========================================================
	
	public List<Integer> getCosts() {
		return costs;
	}

	public void setCosts(List<Integer> costs) {
		this.costs = costs;
		addTotalCost(costs);
	}
	
	public void setSet(List<Integer> set) {
		this.set = set;
	}

	public String getName() {
		return name;
	}

	public int getCost() {
		return cost;
	}
	
	
	public int getTotalCost(){
		return totalCost;
	}

	public List<Integer> getSet() {
		return set;
	}
	
	
 
}
