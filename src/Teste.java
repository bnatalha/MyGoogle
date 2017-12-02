import java.util.ArrayList;
import java.util.Scanner;

public class Teste {
	private static Scanner cin;

	public static void main(String[] args) {
		
		cin = new Scanner(System.in);
		System.out.print("Digite o texto que deseja procurar: ");
		String entrada = cin.nextLine();
		
		String space = ' ' + entrada + ' ';
		entrada = space;
		//System.out.println(entrada);
		
		//Transforma a entrada na classe base de busca
		ArrayList<Busca> buscas = new ArrayList<Busca>();
		Busca buscaUsuario = new Busca(entrada);
		
		//Lemos o arquivo e procuramos pela entrada
		String arq = "tst.txt";		
		LeitorArquivo t = new LeitorArquivo();
		
		if(buscaUsuario.getTipo() == 1 || buscaUsuario.getTipo() == 0)
			for(int i = 0; i < buscaUsuario.numeroPesquisas(); i++) {
				buscaUsuario = t.read(arq, buscaUsuario, i);
		}
		else if(buscaUsuario.getTipo() == 2) {
			for(int i = 0; i < buscaUsuario.numeroPesquisas(); i++) {
				buscaUsuario = t.read(arq, buscaUsuario, i);
				System.out.println(buscaUsuario.andConfirm());
			}
		}
		
		buscaUsuario.imprimir();
		
	}
}



