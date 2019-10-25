import java.util.Scanner;

//Ambiente de testes
public class Teste {
	public static void main(String[] args) {
		//Scaner criando um scanner e lendo a entrada dada pelo usuário
		Scanner cin = new Scanner(System.in);
		System.out.print("Digite o texto que deseja procurar: ");
		String entrada = cin.nextLine();
		//Leitura do arquivo
		String arq = "tst.txt";		
		LeitorArquivo t = new LeitorArquivo();
		int cont = t.read(arq,entrada);
		//Condições de parada
		if(cont > 1)
			System.out.println("Ocorreram " + cont + " ocorrencias.");
		else
			System.out.println("Ocorrereu " + cont + " ocorrencia.");
		
	}
}



