package GlobalID.tools;

public enum Categories {
	Guanajuato("Guanajuato"), 
	Jalisco("Jalisco"), 
	Michoacan("Michoacan");

	public final String label;

	private Categories(String label) {
		this.label = label;
	}
}