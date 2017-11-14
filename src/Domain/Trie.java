package Domain;
import java.util.HashMap;

/**
 * Simula a TAD �rvore Trie.
 * 
 * @author Nat�lia Azevedo de Brito
 */
public class Trie {
	
	/**
	 * N� raiz.
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
	 * Insere uma palavra na �rvore.
	 * @param s palavra a ser inserida.
	 */
	public void insertWord(String s)
	{
		if (s.length() == 0) return; // se 's' fo vazio, sai da fun��o.
		
		TriNode currentNode = raiz;	// cria uma referencia para o n� que ser� analizado (inicialmente, a raiz).
		int i = 0; // index relacionado ao caractere apontado em 's'.
		
		for( ; i< s.length(); i++)
		{
			if(currentNode.get( s.charAt(i) ) == null)	// se no n� atual n�o existir uma referencia para um n� apontado pelo caractere da vez
			{
				currentNode.put( s.charAt(i) );	// cria um n� para ser apontado pelo caractere analizado. 
			}
			currentNode = currentNode.get( s.charAt(i) ); // avan�a o ponteiro para o n� apontado por 'currentChar'.
		}
		
		currentNode.setFim(true); // seta o n� pra ser fim.		
	}
	
	/**
	 * @param s palavra a ser pesquisada.
	 * @return 'true' se encontrou 's', 'false' se n�o encontrou.
	 */
	public boolean search (String s)
	{
		if (s.length() == 0 || raiz == null) return false; // se 's' for uma string vazia, sai da fun��o.
		
		TriNode currentNode = raiz;	// cria uma referencia para o n� que ser� analizado (inicialmente, a raiz).
		int i = 0; // index relacionado ao caractere apontado em 's'.
		
		for( ; i< s.length(); i++)
		{
			if(currentNode.get( s.charAt(i) ) != null)	// se no n� atual existir uma referencia para um n� apontado pelo caractere da vez.
			{
				currentNode = currentNode.get( s.charAt(i) );	// avan�a o ponteiro para o n� apontado pelo caractere. 
			}
			else
				return false;
		}
		return currentNode.isFim();
	}
	
	/**
	 * Simula um n� de uma �rvore Trie.
	 */
	public class TriNode
	{
		/**
		 * Valor do n�.
		 */
		protected char valor;
		/**
		 * Indica se o n� � o fim de uma string.
		 */
		protected boolean fim;
		
		/**
		 * Array de refer�ncias para o pr�ximo n�
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
		 * Constr�i um n� e armazena o valor passado nesse n�.
		 * @param valor valor a ser armazenado no n�.
		 */
		public TriNode(char valor)
		{
			this.valor = valor;
			this.fim = false;
			this.links = new HashMap<Character,TriNode>(30);
		}
		
		/**
		 * @param key endere�o do n� em 'links'
		 * @return TriNode apontado por 'key' em 'links', caso ele exista. Do contr�rio, retorna null.
		 */
		public TriNode get(char key)
		{
			return links.get(key);
		}
		
		/**
		 * Adiciona um n� v�zio aos links deste n�.
		 * @param key chave que ser� associada ao pr�ximo que ser� adicionado
		 * @return o TriNode que foi adicionado a �rvore.
		 */
		public TriNode put(char key)
		{
			links.put(key,new TriNode(key));	// cria um novo n� e o associa a 'key' passada
			return links.get(key);	// retorna uma refer�ncia para o n� criado.
		}
		
		/**
		 * @return 'true', se estiver chegado ao final de uma string armazenada, 'false' caso contr�rio
		 */
		public boolean isFim() {return fim;}
		
		/**
		 * Altera o valor de 'fim' para o passado como parametro nesta fun��o.
		 * @param fim novo valor de 'fim'
		 */
		public void setFim (boolean fim) {this.fim = fim;}
		
	}
	

}
