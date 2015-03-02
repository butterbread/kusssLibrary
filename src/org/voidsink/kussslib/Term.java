package org.voidsink.kussslib;
public class Term implements Comparable<Term> {

	
	public enum TermType {
		SUMMER("S"), WINTER("W");
		
		private final String value;

		private TermType(String value) {
			this.value = value;
		}
		
		public String toString() {
			return value;
		}
	};
	
	private final int year;
	private final TermType type;
	
	public Term(int year, TermType type) {
		this.year = year;
		this.type = type;
	}

	@Override
	public int compareTo(Term o) {
		if (o == null) {
			return -1;
		}
		
		if (this.year < o.getYear()) {
			return -1;
		} else if (this.year > o.getYear()){
			return 1;
		}
		
		return this.type.compareTo(o.getType());
	}
	
	public int getYear() {
		return year;
	}
	
	public TermType getType() {
		return type;
	}
	
	public String toString() {
		return String.format("%d%s", year, type.toString());
	}
	
}
