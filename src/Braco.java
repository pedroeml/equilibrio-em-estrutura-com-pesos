import java.util.Iterator;

public class Braco {
	private SubBraco root;
	
	public Braco(String nomeBraco, int numFilhos) {
		root = new SubBraco(nomeBraco, numFilhos);
	}
	
	public SubBraco find(String nome) {
		return root.find(root, nome);
	}
	public void insert(String p, Object f) {
		SubBraco n = root.find(root, p);
		
		if (n == null)
			return;
		
		n.addPeso(f);
	}
	
	public void print() {
		print(root, "  ");
		System.out.println();
	}
	
	private void print(SubBraco b, String tab) {
		System.out.print(b.nomeBraco);
		Iterator<Object> n = b.listaFilhos.iterator();
		Object obj;
		while(n.hasNext()) {
			obj = n.next();
			if (obj != null) {
				System.out.println("\n"+tab+"|");
				System.out.print(tab+"--");
				if (obj instanceof Integer)
					System.out.print(((Integer) obj).intValue());
				else
					print((SubBraco) obj, tab+"|  ");
			}
		}
	}
}
