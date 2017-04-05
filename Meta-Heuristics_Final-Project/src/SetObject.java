import java.util.ArrayList;
import java.util.List;

public class SetObject {
	private String name;
	private int cost;
	private List<Integer> elements = new ArrayList<Integer>();
	
	
	public SetObject(String name, int cost) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.cost = cost;
	}

	
	public void addElements(Integer new_element){
		elements.add((Integer) new_element);
	}
	
	
	// ==================================================== GETTERS AND SETTERS =========================================================
	

	public String getName() {
		return name;
	}

	public int getCost() {
		return cost;
	}
	
	public List<Integer> getElements() {
		return elements;
	}
 
}
