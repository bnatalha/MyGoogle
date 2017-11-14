package Domain;
import java.util.ArrayList;
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
			if(currentNode.getChildAt( s.charAt(i) ) == null)	// se no n� atual n�o existir uma referencia para um n� apontado pelo caractere da vez
			{
				currentNode.putChildAt( s.charAt(i) );	// cria um n� para ser apontado pelo caractere analizado. 
			}
			currentNode = currentNode.getChildAt( s.charAt(i) ); // avan�a o ponteiro para o n� apontado por 'currentChar'.
		}
		
		currentNode.setFinal(true); // seta o n� pra ser fim.		
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
			if(currentNode.getChildAt( s.charAt(i) ) != null)	// se no n� atual existir uma referencia para um n� apontado pelo caractere da vez.
			{
				currentNode = currentNode.getChildAt( s.charAt(i) );	// avan�a o ponteiro para o n� apontado pelo caractere. 
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
		return false;	// exce��o
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
		else return null;	// exce��o
	}
	
	public void printAll()
	{
		ArrayList<String> collected = collector(raiz);
		
	}
	
	private ArrayList<String> collector(TriNode n, StringBuilder s, ArrayList<String> queue)
	{
		for(TriNode child: n.getChilds()) // n�o � iteravel(?)
		{
			if(child.isFinal() == true) queue.add(s.toString()); 
			if(child.hasNoChild() == false) s.append(child.getValor()); 
			String key = new String();
			
		}
		return collected;		
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
		protected boolean endOfString;
		
		/**
		 * Array de refer�ncias para o pr�ximo n�
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
		 * Constr�i um n� e armazena o valor passado nesse n�.
		 * @param valor valor a ser armazenado no n�.
		 */
		public TriNode(char valor)
		{
			this.valor = valor;
			this.endOfString = false;
			this.links = new HashMap<Character,TriNode>(30);
		}
		
		/**
		 * @param key endere�o do n� em 'links'
		 * @return TriNode apontado por 'key' em 'links', caso ele exista. Do contr�rio, retorna null.
		 */
		public TriNode getChildAt(char key)
		{
			return links.get(key);
		}
		
		/**
		 * @return valor deste n�.
		 */
		public char getValor()
		{
			return this.valor;
		}
		
		/**
		 * Adiciona um n� v�zio aos links deste n�.
		 * @param key chave que ser� associada ao pr�ximo que ser� adicionado
		 * @return o TriNode que foi adicionado a �rvore.
		 */
		public void putChildAt(char key)
		{
			links.put(key,new TriNode(key));	// cria um novo n� e o associa a 'key' passada
		}
		
		/**
		 * @return 'true', se estiver chegado ao final de uma string armazenada, 'false' caso contr�rio
		 */
		public boolean isFinal() 
		{
			return endOfString;
		}
		
		/**
		 * @return os filhos desse n�.
		 */
		public HashMap<Character,TriNode> getChilds() 
		{
			return links;
		}
		
		/**
		 * Altera o valor de 'fim' para o passado como parametro nesta fun��o.
		 * @param fim novo valor de 'fim'
		 */
		public void setFinal (boolean fim) 
		{
			this.endOfString = fim;
		}
		
		/**
		 * @return 'true' se este n� n�o aponta para nenhum outro n�, 'false' caso contr�rio.
		 */
		public boolean hasNoChild()
		{
			return links.isEmpty();
		}
		
		/**
		 * Remove um n� filho deste n�.
		 * @param k n� a ser removido
		 */
		public void removeChildAt(char k)
		{
			links.remove(k);
		}
		
	}
	
}
