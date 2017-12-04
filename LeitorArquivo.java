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
        int cont = 0;	// numero de vezes que entrada foi encontrado em 'caminho'
        try {
            FileReader arq = new FileReader(Caminho);
            BufferedReader lerArq = new BufferedReader(arq);	// buffer com o caminho
            String linha="";
            try {
                linha = lerArq.readLine();	//contem linha do arquivo
                while(linha!=null){	//enquanto tiver liinha no buffer
                	if(linha.toLowerCase().contains(entrada.toLowerCase())) {	//(tudo pra lowcase) se linha tiver entrada uma vez
            			cont++;	//aumenta o cont
            		}
                    linha = lerArq.readLine();	 //ler proxima linha
                }
                arq.close();
                return cont;	// retorna qnatidade
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
