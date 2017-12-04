package master;
import domain.IndexItem;
import domain.RadixTree;

/**
 * Administrador do MyGoogle. Responsável por armazenar os arquivos no banco de dados, e fazer pesquisas. 
 * 
 * @author Natália Azevedo de Brito
 */
public class DataManager {
	
	/**
	 * index;
	 */
	private RadixTree index;
	
	/**
	 * banco de dados. onde ficam os arquivos que serão usados na busca;
	 */
	private String database;
	
	/**
	 * alfabeto
	 */
	private final int ALPHABET = 26;
	
	/**
	 * filtro(?)
	 */
	
	/**
	 * searcher
	 */
	
	/**
	 * Construtor
	 */
	public DataManager()
	{
		this.index = new RadixTree(ALPHABET);
	}
	
	/**
	 * Pega uma lista com nomes de arquivos e os armazena no index
	 */
	public void LerArquivos(ArrayList<File> files)
	{
		// chama a classe de leitura para
		// para todo IndexedItem em Leitor.getItemIndexList()
		//		index.insertIndexItem()
		// done
	}
	
	/**
	 * Procura por uma palavra no index e retorna um resultado. 
	 * 
	 * @param str palavra procurada
	 * @return String com o resultado
	 */
	public String searchWord(String str)
	{
		IndexItem result = index.searchIndexItem(str);	// procura o IndexItem da palavra no nosso index.
		
		if (result == null)
			return new String("Word \"" +str+"\" not found.");
		
		return result.toString(); 
	}
	
	/**
	 * Remove um arquivo da database
	 * @param str Nome do arquivo a ser removido
	 * @return true se conseguiu remover. false
	 */
	public boolean removeFile(String str)
	{
		// busca o arquivo a ser removido na database
		// se encontrar,
		//		remove todas ocorrencias dele na database
		//		o remove. e retorna true
		// caso contrario, retorna false
	}
	
	/**
	 * Adiciona um arquivo a database
	 * @param str Nome/File do arquivo a ser adicionado
	 */
	public addFile(String str)	//addfiles??????
	{
		//Cria uma copia do arquivo apontado e o salva na database, subcrevendo-o caso ja exista na database.
		
		// se encontrou uma palavra q ja tinha sido armazenada,
		// 		compara IndexItems. atualiza o ja armazenado
	}
	
	/**
	 * @return blacklist.txt
	 */
	//??????????????????????????????????????????
	public void updateIndex()
	{
		// recostroi a arvore com base nos arquivos da database
	}
	
}

/**
 * primeira leitura, le todos os arquivos da database.
 * update index vai pegar uma lista de arquivos a serem atualizados e  
 */

/**
 * leitor: 
 * 
 * leitura por arquivo.
 * armazena lista de IndexItems que sera atualizado a cada nova ocorrencia lida arquivo. boom.
 */