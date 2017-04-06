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

	
	// =============================================== HASH CODE AND EQUALS ===============================================================
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SetObject other = (SetObject) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


	public int getCost() {
		return cost;
	}
	
	public List<Integer> getElements() {
		return elements;
	}
 
}
