import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LeitorArquivo {
	
	/**
	 * 
	 * Funcao responsavel por executar leitura do arquivo.
	 * 
	 * @param  Caminho  Caminho para o arquivo.
	 * @param  Entrada  Contem a string que o usuario pesquisou.
	 * @return Numero de vezes que a palavra foi encontrada no arquivo.
	 * */
    public Busca read(String caminho, Busca atual, int k){
        try {
            FileReader arq = new FileReader(caminho);
            BufferedReader lerArq = new BufferedReader(arq);
            String linha="";
            String palavraAtual = atual.getTexto(k);
            int cont = 0;
            
            
            try {
                linha = lerArq.readLine();
                while(linha!=null){
                	cont++;
                	linha = linha.replace(","," ");
                	linha = linha.replace("."," ");
                	linha = linha.replace("?"," ");
                	linha = linha.replace("!"," ");
                	//precisa dar split na plavra q
                	if(linha.toLowerCase().contains(palavraAtual.toLowerCase())) {
                		atual.casoBusca(caminho, cont, k);
            		}
                    linha = lerArq.readLine();
                }
                
                
                //System.out.println(atual.getDados());
                arq.close();
                return atual;
            }
            
            
            
            
            catch (IOException ex) {
                System.out.println("Erro: Não foi possível ler o arquivo!");
                return null;
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Erro: Arquivo não encontrado!");
            return null;
        }
    }
}
