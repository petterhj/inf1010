class Oppgave3 {
	public static void main(String[] args) {
        PlateArrayStack tas = new PlateArrayStack(5);

        System.out.println("Is empty: "+tas.isEmpty());

        tas.push(new Plate("first"));
        tas.push(new Plate("second"));
        tas.push(new Plate("third"));

        System.out.println("Is empty: "+tas.isEmpty());
        
        System.out.println("At the top: "+tas.top().getId());

        tas.push(new Plate("fourth"));
        tas.push(new Plate("fifth"));

        System.out.println("At the top: "+tas.pop().getId());

        System.out.println("At the top: "+tas.pop().getId());
	}
}

interface TallerkenStabel {
	public Tallerken pop();
	public Tallerken top();
	public void push(Tallerken tlrk);
	public boolean isEmpty();
}


class Tallerken {    
    private String _id;

    public Tallerken(String id) {
        _id = id;
    }
    public String getId() {
        return _id;
    }
}


class TallerkenArrayStabel implements TallerkenStabel {
	private Tallerken[] stabel;
	private int øverste = 0;
	
	public TallerkenArrayStabel (int størrelse) {
		stabel = new Tallerken[størrelse];
	}
	
	public Tallerken pop() {
        if (øverste == 0)
            return null;
        øverste -= 1;
        Plate t = stabel[øverste];
        stabel[øverste] = null;
        return t;
	}
	public Tallerken top() {
        if (øverste == 0)
            return null;
        return stabel[øverste-1];
	}
	public void push(Tallerken tlrk) {
        if (øverste == stabel.length)
            throw new IllegalStateException("stabelen er full");
        stabel[størrelse] = t;
        størrelse += 1;
	}
	public boolean isEmpty() {
		return øverste == 0;
	}
}