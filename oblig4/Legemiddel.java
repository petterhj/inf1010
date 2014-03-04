// Interface: LegemiddelForm
public interface LegemiddelForm {
	public String form();
	
}

// Class: Legemiddel
public class Legemiddel implements LegemiddelForm {
	private int id;
	private String navn;
	private int pris;
	private String form;
	private int mengde;
	
	public String form() {
		return this.form;
	}
}

// Class: LegemiddelTypeA (Narkotisk)
public class LegemiddelTypeA extends Legemiddel {
	private int narkotisk;
}

// Class: LegemiddelTypeB (Vanedannende)
public class LegemiddelTypeB extends Legemiddel {
	private int vanedannende;
}

// Class: LegemiddelTypeC (Vanlig legemiddel)
public class LegemiddelTypeC extends Legemiddel {
	
}