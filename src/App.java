import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class App {
	//private static GenericTree T;
	private static Braco b;
	private static Object[][] matriz;
	
	public static void main(String[] args) {
		lerArquivo();
		genBracos();
		b.print();
		checkEquilibrio();
	}
    
	public static void lerArquivo() {
		String caminhoDoArquivo = "C:\\Users\\" + System.getProperty("user.name").toString() +"\\Dropbox\\Documentos\\PUCRS\\Arquivos\\2015.2\\AED-I\\Trabalhos\\T3\\casos\\teste10.txt";
        
		try {
            FileReader arquivoParaLeitura = new FileReader(caminhoDoArquivo);
            
            BufferedReader leitorArquivo = new BufferedReader(arquivoParaLeitura);
            
            String linha = leitorArquivo.readLine();
            
            matriz = new Object[Integer.parseInt(linha)][3];
            
            int line = 0;
            
            while((linha = leitorArquivo.readLine()) != null) {
            	String[] sc = linha.split(" ");
            	
            	String[] content = new String[Integer.parseInt(sc[1])];
            	for(int i = 0; i<Integer.parseInt(sc[1]); i++)
            		content[i] = sc[2+i];
            	
            	matriz[line][0] = sc[0];
            	matriz[line][1] = sc[1];
            	matriz[line][2] = content;
            	line++;
            }
            leitorArquivo.close();
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
	
	public static void genBracos() {
		boolean temRaiz = false;
		for (int i = 0; i<matriz.length; i++) {
			String parent = (String) matriz[i][0];
			int qntFilhos = Integer.parseInt((String) matriz[i][1]);
			if (!temRaiz) {
				b = new Braco(parent, qntFilhos);
				temRaiz = !temRaiz;
			}
			
			String[] array = (String[]) matriz[i][2];
			
			for(int j = 0; j<qntFilhos; j++) {
				if (array[j].charAt(0) != 'X')
					b.insert(parent, Integer.parseInt(array[j]));
				else {
					SubBraco aux = null;
					for(int k = 0; k<matriz.length; k++)
						if (((String) matriz[k][0]).compareToIgnoreCase(array[j]) == 0)
							 aux = new SubBraco(array[j], Integer.parseInt((String) matriz[k][1]));
					b.insert(parent, aux);
				}	
			}
		}
	}
	
	public static void checkEquilibrio() {
		String bracosEquilibrados = "";
		boolean equilibrada = false;
		int qntEquilibrados = 0;
		for (int i = 0; i<matriz.length; i++) {
			String parent = (String) matriz[i][0];
			if (i != 0)
				equilibrada = b.find(parent).estaEquilibrada();
			else
				equilibrada = true;
			if (equilibrada) {
				qntEquilibrados++;
				bracosEquilibrados += parent+", ";
			}
			System.out.println(parent+": "+equilibrada);
		}
		System.out.println("\nEQUILIBRADOS: "+qntEquilibrados+"\n"+bracosEquilibrados+"\n");
	}
}