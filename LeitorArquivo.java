import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LeitorArquivo {
	
	/**
	 * 
	 * Funcao responsavel por executar leitura do arquivo.
	 * 
	 * @param  Caminho  Caminho para o arquivo.
	 * @param  Entrada  Contem a string que o usuario pesquisou.
	 * @return Numero de vezes que a palavra foi encontrada no arquivo.
	 * */
    public int read(String Caminho, String entrada){
        int cont = 0;
        try {
            FileReader arq = new FileReader(Caminho);
            BufferedReader lerArq = new BufferedReader(arq);
            String linha="";
            try {
                linha = lerArq.readLine();
                while(linha!=null){
                	if(linha.toLowerCase().contains(entrada.toLowerCase())) {
            			cont++;
            		}
                    linha = lerArq.readLine();
                }
                arq.close();
                return cont;
            } catch (IOException ex) {
                System.out.println("Erro: Não foi possível ler o arquivo!");
                return 0;
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Erro: Arquivo não encontrado!");
            return 0;
        }
    }
}
