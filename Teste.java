import java.util.Scanner;

public class Teste {
	public static void main(String[] args) {
		
		Scanner cin = new Scanner(System.in);
		System.out.print("Digite o texto que deseja procurar: ");
		String entrada = cin.nextLine();
		
		String arq = "tst.txt";		
		LeitorArquivo t = new LeitorArquivo();
		int cont = t.read(arq,entrada);
		if(cont > 1)
			System.out.println("Ocorreram " + cont + " ocorrencias.");
		else
			System.out.println("Ocorrereu " + cont + " ocorrencia.");
		
	}
}



