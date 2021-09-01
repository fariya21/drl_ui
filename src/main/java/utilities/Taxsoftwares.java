package utilities;

public enum Taxsoftwares {
	CCH("CCH Axcess Tax"),
	Go("GoSystem Tax RS"),
	Lacerte("Lacerte"),    
    Others("Others"),
    Pro("ProSystem fx Tax"),   
    Ultra("UltraTax CS");    	


public final String label;

private Taxsoftwares(String label) {
    this.label = label;
}
}
