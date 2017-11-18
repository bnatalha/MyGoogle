package domain;

import java.util.ArrayList;

/**
 * Simula uma �rvore Radix. Obedece a ordem natural das strings.
 *  
 * @author Nat�lia Azevedo de Brito
 */
public class RadixTree
{

	/**
	 * N� raiz
	 */
	private RadixNode root;
	
	/**
	 * Tamanho do alfabeto
	 */
	private final int ALPHABET_SIZE;
	
	// ================================================ METODOS ================================================
		
	/**
	 * Constr�i uma �rvore radix vazia com o alfbeto de tamanho N.
	 * @param N tamanho do alfabeto dessa �rvore.
	 */
	public RadixTree(int N) 
	{
		this.ALPHABET_SIZE = N;
		this.root = new RadixNode(N); 
	}
	
	/**
	 * Calcula a posi��o em que o n� filho com o prefixo que combina com 'str' deve estar.
	 * @param str String a ser analizada.
	 * @return o mod do valor na tabela ascii da primeira letra de 'str' pelo valor de 'a' na mesma tabela.
	 * @see http://www.asciitable.com/
	 */
	private int getPosition(String str)
	{
		int divisor = (int) 'a';
		return ( ((int)(str.charAt(0))) % divisor);
	}
	
	/**
	 * Procura por uma palavra na �rvore.
	 * @param str palavra a ser procurada.
	 * @return 'true' caso a palavra seja encontrada.
	 */
	public boolean searchWord(String str)
	{
		if(str != null)
		{
			return searchW(root,str);
		}
		return false;
	}
	
	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	private int getDiffCharIndex(String a, String b)
	{
		int i = 0;
		while (i != a.length() && i != b.length() )
		{
			if(a.charAt(i) != b.charAt(i)) break;
		}
		
		return i;
	}
	
	/**
	 * Busca recursiva
	 * @param n
	 * @param str
	 * @return
	 */
	private boolean searchW(RadixNode n, String str)
	{
		if (n != null)
		{
			RadixNode suitable_child = n.getChildAt(getPosition(str));
			if( suitable_child == null)
			{
				return false;				
			}
			else
			{
				int separator = getDiffCharIndex(str,suitable_child.getLabel());
				if( separator < str.length() && separator == suitable_child.getLength() )
				{
					str = str.substring(separator, str.length()); //?
					searchW(suitable_child,str);
				}
				else if( separator < str.length() && separator < suitable_child.getLength() )
				{
					return false;	
				}
				else if( separator == str.length() && separator < suitable_child.getLength() )
				{
					return false;
				}
				else if( separator == str.length() && separator == suitable_child.getLength() )
				{
					return suitable_child.isEndOfString();
				}
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param s
	 */
	public void insertWord(String str)
	{
		
	}
	
	/**
	 * 
	 * @param n
	 * @param s
	 */
	private void insertW(RadixNode n, String str)
	{
		if (n != null)
		{
			RadixNode suitable_child = n.getChildAt(getPosition(str));
			if( suitable_child == null)
			{
				RadixNode newchild = new RadixNode(ALPHABET_SIZE,str);
				n.addChild(newchild);				
			}
			else
			{
				int separator = getDiffCharIndex(str,suitable_child.getLabel());
				if( separator < str.length() && separator == suitable_child.getLength() )
				{
					str = str.substring(separator); //?
					insertW(suitable_child,str);
				}
				else if( separator < str.length() && separator < suitable_child.getLength() )
				{
					// corta a string a ser inserida para ser apenas a parte q tem seu sufixo diferenciado
					str = str.substring(separator);
					// cria uma nova string com a parte diferente pertencente ao label do n� analizado
					String newLabel = suitable_child.getLabel().substring(separator);
					
					//Cria um n� com o label cortado do n� original
					RadixNode newNode = new RadixNode(ALPHABET_SIZE,newLabel);	
					
					//move os filhos do n� analizado para o novo n�
					newNode.setChilds(suitable_child.getChilds());
					// faz com que o novo n� indica o final de uma string caso o n� de onde ele originou seja.
					newNode.setEndOfString(suitable_child.isEndOfString());
					
					// cria um novo array de filhos vazio para o n� original
					suitable_child.setChilds(new RadixNode[ALPHABET_SIZE]);
					// modifica sua label para o prefixo em comum 
					suitable_child.setLabel(suitable_child.getLabel().substring(0,separator));
					// seta endOfString para falso
					suitable_child.setEndOfString(false);
					
					//adiciona o novo n� e o n� da string a ser inserida como filhos do original
					suitable_child.addChild(newNode);
					suitable_child.addChild(new RadixNode(ALPHABET_SIZE,str));
				}
				else if( separator == str.length() && separator < suitable_child.getLength() )
				{
					// cria uma nova string com o sufixo diferente pertencente ao label do n� original
					String newLabel = suitable_child.getLabel().substring(separator);
					
					// Cria um n� com o label cortado do n� original
					RadixNode newNode = new RadixNode(ALPHABET_SIZE,newLabel);	
					
					// move os filhos do n� analizado para o novo n�
					newNode.setChilds(suitable_child.getChilds());
					// faz com que o novo n� indica um final de uma string caso o original seja.
					newNode.setEndOfString(suitable_child.isEndOfString());
					
					// cria um novo array de filhos vazio para o n� original
					suitable_child.setChilds(new RadixNode[ALPHABET_SIZE]);
					// modifica a label do n� original para o prefixo em comum 
					suitable_child.setLabel(suitable_child.getLabel().substring(0,separator));
					// seta endOfString para verdadeiro
					suitable_child.setEndOfString(true);
					
					//adiciona o novo n� como filho do original
					suitable_child.addChild(newNode);
				}
				else if( separator == str.length() && separator == suitable_child.getLength() )
				{
					suitable_child.setEndOfString(true);
				}
			}
		}
	}
	
	// ================================================ N� ================================================
	
	/**
	 * Simula um n� de uma �rvore radix. Adaptado para o projeto.
	 * @author Nat�lia Azevedo de Brito
	 */
	public class RadixNode 
	{
		/**
		 * Contem o peda�o de string que serve de caminho para este n� a partir do seu pai. (??)
		 */
		private String label;
		
		/**
		 * Peso desse n�. Indica em que posi��o do array de filhos ele deve ser guardado.
		 */
		private int ranking;
		
		/**
		 * Armazena os filhos desse n�
		 */
		private RadixNode[] childs;
		
		/**
		 * Guarda o n�mero m�ximo de filhos que um n� pode ter.
		 */
		private final int MAX_CHILDS;
		
		/**
		 * Indica se esse n� � o fim de uma string.
		 */
		private boolean endOfString;
		
		/**
		 * Guarda informa��es sobre a origem de uma palavra. 
		 */
		private MyIndexItem indexitem;
		
		
		/**
		 * Constr�i um n� vazio de uma �rvore de alfabeto de tamanho 'N'.
		 */
		public RadixNode(int N)
		{
			MAX_CHILDS = N;
			this.endOfString = false;
			this.childs = new RadixNode[MAX_CHILDS];
		}
		
		/**
		 * Constr�i um n� de uma �rvore com o alfabeto de tamanho 'N', j� com um label.
		 */
		public RadixNode(int N, String label)
		{
			MAX_CHILDS = N;
			this.endOfString = false;
			this.label = label;
			updateRanking();
			this.childs = new RadixNode[MAX_CHILDS];
		}
		
		/**
		 * @return the label
		 */
		public String getLabel() {
			return label;
		}
		
		/**
		 * @return the length of the label
		 */
		public int getLength() {
			return label.length();
		}

		/**
		 * @param label the label to set
		 */
		public void setLabel(String label) {
			this.label = label;
			updateRanking();
		}
		
		/**
		 * Atualiza o peso/ranking desse n�.
		 */
		private void updateRanking()
		{
			int divisor = (int) 'a';
			ranking = ( ((int)(label.charAt(0))) % divisor);
		}
		
		/**
		 * @return the ranking
		 */
		public int getRanking()
		{
			return ranking;
		}

		/**
		 * @return the childs
		 */
		public RadixNode[] getChilds() {
			return childs;
		}

		/**
		 * @param childs the childs to set
		 */
		public void setChilds(RadixNode[] childs) {
			this.childs = childs;
		}
		
		/**
		 * @return the endOfString
		 */
		public boolean isEndOfString() {
			return endOfString;
		}

		/**
		 * @param eos the endOfString to set
		 */
		public void setEndOfString(boolean eos) {
			this.endOfString = eos;
		}
		
		/**
		 * @return the indexitem
		 */
		public MyIndexItem getIndexItem() {
			return indexitem;
		}

		/**
		 * @param indexitem the indexitem to set
		 */
		public void setMyIndexItem(MyIndexItem indexitem) {
			this.indexitem = indexitem;
		}
		
		/**
		 * @param index posi��o do n� filho
		 * @return n� filho da posi��o 'index' caso ele exista.
		 */
		public RadixNode getChildAt(int index) {
			return this.childs[index];
		}
		
		public void addChild(RadixNode n)
		{
			childs[n.getRanking()] = n;
		}
	}
}
