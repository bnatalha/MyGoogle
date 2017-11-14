package Domain;
import java.util.ArrayList;
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
			if(currentNode.getChildAt( s.charAt(i) ) == null)	// se no nó atual não existir uma referencia para um nó apontado pelo caractere da vez
			{
				currentNode.putChildAt( s.charAt(i) );	// cria um nó para ser apontado pelo caractere analizado. 
			}
			currentNode = currentNode.getChildAt( s.charAt(i) ); // avança o ponteiro para o nó apontado por 'currentChar'.
		}
		
		currentNode.setFinal(true); // seta o nó pra ser fim.		
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
			if(currentNode.getChildAt( s.charAt(i) ) != null)	// se no nó atual existir uma referencia para um nó apontado pelo caractere da vez.
			{
				currentNode = currentNode.getChildAt( s.charAt(i) );	// avança o ponteiro para o nó apontado pelo caractere. 
			}
			else
				return false;
		}
		return currentNode.isFinal();
	}
	
	/**
	private boolean searchR(String s, int i, TriNode n)
	{
		if(n != null && s != null && i >= 0) {
			if(i == s.length())
			{
				if(n.isFim()) return true;
				else return false;
			}
			if(n.get(s.charAt(i)) != null) searchR( s, i+1, n.get(s.charAt(i)) );
			else return false;
		}
		return false;	// exceção
	}
	**/
	
	public void removeWord(String s) {removeR(s,0,raiz);}
	
	private TriNode removeR(String s, int i, TriNode n)
	{
		if(n != null && s != null && i >= 0) {
			if(i == s.length())
			{
				if(n.isFinal()) return n;
				else return null;
			}
			if(n.getChildAt(s.charAt(i)) != null)
			{
				TriNode child = removeR( s, i+1, n.getChildAt(s.charAt(i)) );
				if(child != null)
				{
					if(child.hasNoChild())
					{
						n.removeChildAt(s.charAt(i)); // remove o filho dos links
						//child = null; // aponta o filho pra null;
					}
					else
					{
						child.setFinal(false);
					}
				}
				return n;
			}
			else return null;
		}
		else return null;	// exceção
	}
	
	public void printAll()
	{
		ArrayList<String> collected = collector(raiz);
		
	}
	
	private ArrayList<String> collector(TriNode n, StringBuilder s, ArrayList<String> queue)
	{
		for(TriNode child: n.getChilds()) // não é iteravel(?)
		{
			if(child.isFinal() == true) queue.add(s.toString()); 
			if(child.hasNoChild() == false) s.append(child.getValor()); 
			String key = new String();
			
		}
		return collected;		
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
		protected boolean endOfString;
		
		/**
		 * Array de referências para o próximo nó
		 */
		protected HashMap<Character,TriNode> links;
		
		/**
		 * Construtor vazio.
		 */
		public TriNode()
		{
			this.endOfString = false;
			this.links = new HashMap<Character,TriNode>(30);
		}
		
		/**
		 * Constrói um nó e armazena o valor passado nesse nó.
		 * @param valor valor a ser armazenado no nó.
		 */
		public TriNode(char valor)
		{
			this.valor = valor;
			this.endOfString = false;
			this.links = new HashMap<Character,TriNode>(30);
		}
		
		/**
		 * @param key endereço do nó em 'links'
		 * @return TriNode apontado por 'key' em 'links', caso ele exista. Do contrário, retorna null.
		 */
		public TriNode getChildAt(char key)
		{
			return links.get(key);
		}
		
		/**
		 * @return valor deste nó.
		 */
		public char getValor()
		{
			return this.valor;
		}
		
		/**
		 * Adiciona um nó vázio aos links deste nó.
		 * @param key chave que será associada ao próximo que será adicionado
		 * @return o TriNode que foi adicionado a árvore.
		 */
		public void putChildAt(char key)
		{
			links.put(key,new TriNode(key));	// cria um novo nó e o associa a 'key' passada
		}
		
		/**
		 * @return 'true', se estiver chegado ao final de uma string armazenada, 'false' caso contrário
		 */
		public boolean isFinal() 
		{
			return endOfString;
		}
		
		/**
		 * @return os filhos desse nó.
		 */
		public HashMap<Character,TriNode> getChilds() 
		{
			return links;
		}
		
		/**
		 * Altera o valor de 'fim' para o passado como parametro nesta função.
		 * @param fim novo valor de 'fim'
		 */
		public void setFinal (boolean fim) 
		{
			this.endOfString = fim;
		}
		
		/**
		 * @return 'true' se este nó não aponta para nenhum outro nó, 'false' caso contrário.
		 */
		public boolean hasNoChild()
		{
			return links.isEmpty();
		}
		
		/**
		 * Remove um nó filho deste nó.
		 * @param k nó a ser removido
		 */
		public void removeChildAt(char k)
		{
			links.remove(k);
		}
		
	}
	
}
