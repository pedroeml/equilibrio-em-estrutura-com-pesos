import java.util.Iterator;
import java.util.LinkedList;

public class SubBraco {
		protected LinkedList<Object> listaFilhos;
		private int numFilhos;
		protected String nomeBraco;
		
		public SubBraco(String nomeBraco, int numFilhos) {
			this.nomeBraco = nomeBraco;
			this.numFilhos = numFilhos;
			listaFilhos = new LinkedList<Object>();
		}
		
		public String getNomeBraco() {
			return this.nomeBraco;
		}
		
		public Object getFilho(int index) {
			return this.listaFilhos.get(index);
		}
		
		public Object getPesoMeio() {
			System.out.println("======["+this.nomeBraco+" filhos: "+this.numFilhos+"/2="+(this.numFilhos/2));
			return this.listaFilhos.get(this.numFilhos/2); // Pega o nodo do meio da lista de filhos.
		}
		
		public void addPeso(Object obj) {
			if (this.listaFilhos.size() < this.numFilhos) // Se o limite informado já não foi atingido...
				this.listaFilhos.addLast(obj);
		}
		
		public boolean estaEquilibrada() {
			int int1, int2;
			for (int i = 0; i<this.numFilhos/2; i++) {
				/* Se a lista for de 5 nodos, a comparação será a seguinte:
				 * 0 com 4 e 1 com 3, restando o índice 2 que é o peso do meio. */
				int1 = this.iteraPesoMeio(this.getFilho(i));
				int2 = this.iteraPesoMeio(this.getFilho(this.numFilhos-i-1));
				if (int1 != int2)
					return false;
			}
			return true;
		}
		
		protected int iteraPesoMeio(Object obj) {
			if (obj instanceof Integer) // Se o peso do meio for uma instância da classe Integer...
				return ((Integer) obj).intValue();
			return iteraPesoMeio(((SubBraco) obj).getPesoMeio()); // Senão, o peso do meio é um braço, logo temos que pegar o peso do meio deste outro braço e ver se ele é uma instância na chamada recursiva.
		}
		
		protected SubBraco find(SubBraco n, String nome) {
			if (n == null)
				return null;
			
			if (n.nomeBraco.compareToIgnoreCase(nome) == 0)
				return n;
			
			Iterator<Object> f = n.listaFilhos.iterator();
			Object obj;
			SubBraco ax = null;
			while (f.hasNext()) {
				obj = f.next();
				if(obj instanceof SubBraco)
					ax = find((SubBraco) obj, nome);
				
				if (ax != null)
					return ax;
			 }
			 
			return null;
		}
		
		@Override
		public String toString() {
		String aux = nomeBraco+"( ";
		for (Object obj : this.listaFilhos) {
			if (obj instanceof Integer)
				aux += ((Integer) obj).intValue()+" ";
			else
				aux += ((SubBraco) obj).nomeBraco+" ";
		}
		aux += " )";
			// TODO Auto-generated method stub
		return aux;
		}
}
