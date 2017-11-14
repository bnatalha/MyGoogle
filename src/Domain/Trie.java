package Domain;
import java.util.HashMap;

/**
 * Simula a TAD Árvore Trie.
 * 
 * @author Natália Azevedo de Brito
 */
public class Trie {
	
	/**
	 * Nó raiz.
	 */
	private TriNode raiz;
	
	/**
	 * Construtor vazio
	 */
	public Trie()
	{
		this.raiz = new TriNode();
	}
	
	/**
	 * Insere uma palavra na árvore.
	 * @param s palavra a ser inserida.
	 */
	public void insertWord(String s)
	{
		if (s.length() == 0) return; // se 's' fo vazio, sai da função.
		
		TriNode currentNode = raiz;	// cria uma referencia para o nó que será analizado (inicialmente, a raiz).
		int i = 0; // index relacionado ao caractere apontado em 's'.
		
		for( ; i< s.length(); i++)
		{
			if(currentNode.get( s.charAt(i) ) == null)	// se no nó atual não existir uma referencia para um nó apontado pelo caractere da vez
			{
				currentNode.put( s.charAt(i) );	// cria um nó para ser apontado pelo caractere analizado. 
			}
			currentNode = currentNode.get( s.charAt(i) ); // avança o ponteiro para o nó apontado por 'currentChar'.
		}
		
		currentNode.setFim(true); // seta o nó pra ser fim.		
	}
	
	/**
	 * @param s palavra a ser pesquisada.
	 * @return 'true' se encontrou 's', 'false' se não encontrou.
	 */
	public boolean search (String s)
	{
		if (s.length() == 0 || raiz == null) return false; // se 's' for uma string vazia, sai da função.
		
		TriNode currentNode = raiz;	// cria uma referencia para o nó que será analizado (inicialmente, a raiz).
		int i = 0; // index relacionado ao caractere apontado em 's'.
		
		for( ; i< s.length(); i++)
		{
			if(currentNode.get( s.charAt(i) ) != null)	// se no nó atual existir uma referencia para um nó apontado pelo caractere da vez.
			{
				currentNode = currentNode.get( s.charAt(i) );	// avança o ponteiro para o nó apontado pelo caractere. 
			}
			else
				return false;
		}
		return currentNode.isFim();
	}
	
	/**
	 * Simula um nó de uma Árvore Trie.
	 */
	public class TriNode
	{
		/**
		 * Valor do nó.
		 */
		protected char valor;
		/**
		 * Indica se o nó é o fim de uma string.
		 */
		protected boolean fim;
		
		/**
		 * Array de referências para o próximo nó
		 */
		protected HashMap<Character,TriNode> links;
		
		/**
		 * Construtor vazio.
		 */
		public TriNode()
		{
			this.fim = false;
			this.links = new HashMap<Character,TriNode>(30);
		}
		
		/**
		 * Constrói um nó e armazena o valor passado nesse nó.
		 * @param valor valor a ser armazenado no nó.
		 */
		public TriNode(char valor)
		{
			this.valor = valor;
			this.fim = false;
			this.links = new HashMap<Character,TriNode>(30);
		}
		
		/**
		 * @param key endereço do nó em 'links'
		 * @return TriNode apontado por 'key' em 'links', caso ele exista. Do contrário, retorna null.
		 */
		public TriNode get(char key)
		{
			return links.get(key);
		}
		
		/**
		 * Adiciona um nó vázio aos links deste nó.
		 * @param key chave que será associada ao próximo que será adicionado
		 * @return o TriNode que foi adicionado a árvore.
		 */
		public TriNode put(char key)
		{
			links.put(key,new TriNode(key));	// cria um novo nó e o associa a 'key' passada
			return links.get(key);	// retorna uma referência para o nó criado.
		}
		
		/**
		 * @return 'true', se estiver chegado ao final de uma string armazenada, 'false' caso contrário
		 */
		public boolean isFim() {return fim;}
		
		/**
		 * Altera o valor de 'fim' para o passado como parametro nesta função.
		 * @param fim novo valor de 'fim'
		 */
		public void setFim (boolean fim) {this.fim = fim;}
		
	}
	

}
