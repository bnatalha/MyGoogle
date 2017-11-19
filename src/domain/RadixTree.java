package domain;

import java.util.ArrayList;

/**
 * Simula uma Árvore Radix. Obedece a ordem natural das strings.
 * 
 * obs: pensar em como fica remove e insert e search para a indexação de mais de uma palavra e sua origem
 *  
 * @author Natália Azevedo de Brito
 */
public class RadixTree
{

	/**
	 * Nó raiz
	 */
	private RadixNode root;
	
	/**
	 * Tamanho do alfabeto
	 */
	private final int ALPHABET_SIZE;
	
	// ================================================ METODOS ================================================
		
	/**
	 * Constrói uma árvore radix vazia com o alfbeto de tamanho N.
	 * @param N tamanho do alfabeto dessa árvore.
	 */
	public RadixTree(int N) 
	{
		this.ALPHABET_SIZE = N;
		this.root = new RadixNode(N); 
	}
	
	/**
	 * Calcula a posição em que o nó filho com o prefixo que combina com 'str' deve estar.
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
	 * Procura por uma palavra na árvore.
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
			i++;
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
					return searchW(suitable_child,str);
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
		if(str != null) insertW(root,str);
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
				newchild.setEndOfString(true); 
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
					// cria uma nova string com a parte diferente pertencente ao label do nó analizado
					String newLabel = suitable_child.getLabel().substring(separator);
					
					//Cria um nó com o label cortado do nó original
					RadixNode newNode = new RadixNode(ALPHABET_SIZE,newLabel);	
					
					//move os filhos do nó analizado para o novo nó
					newNode.setChilds(suitable_child.getChilds());
					// faz com que o novo nó indica o final de uma string caso o nó de onde ele originou seja.
					newNode.setEndOfString(suitable_child.isEndOfString());
					
					// cria um novo array de filhos vazio para o nó original
					suitable_child.setChilds(new RadixNode[ALPHABET_SIZE]);
					// modifica sua label para o prefixo em comum 
					suitable_child.setLabel(suitable_child.getLabel().substring(0,separator));
					// seta endOfString para falso
					suitable_child.setEndOfString(false);
					
					//adiciona o novo nó e como filho do original
					suitable_child.addChild(newNode);
					//insere o novo pedaço de string para ser filho do no original
					insertW(suitable_child,str);
				}
				else if( separator == str.length() && separator < suitable_child.getLength() )
				{
					// cria uma nova string com o sufixo diferente pertencente ao label do nó original
					String newLabel = suitable_child.getLabel().substring(separator);
					
					// Cria um nó com o label cortado do nó original
					RadixNode newNode = new RadixNode(ALPHABET_SIZE,newLabel);	
					
					// move os filhos do nó analizado para o novo nó
					newNode.setChilds(suitable_child.getChilds());
					// faz com que o novo nó indica um final de uma string caso o original seja.
					newNode.setEndOfString(suitable_child.isEndOfString());
					
					// cria um novo array de filhos vazio para o nó original
					suitable_child.setChilds(new RadixNode[ALPHABET_SIZE]);
					// modifica a label do nó original para o prefixo em comum 
					suitable_child.setLabel(suitable_child.getLabel().substring(0,separator));
					// seta endOfString para verdadeiro
					suitable_child.setEndOfString(true);
					
					//adiciona o novo nó como filho do original
					suitable_child.addChild(newNode);
				}
				else if( separator == str.length() && separator == suitable_child.getLength() )
				{
					suitable_child.setEndOfString(true);
				}
			}
		}
	}
	
	/**
	 * 
	 * @param s
	 */
	public void removeWord(String s)
	{
		if(s != null) searchAndRemoveW(root,s);
	}
	
	/**
	 * 
	 * @param n
	 * @param str
	 * @return
	 */
	private RadixNode searchAndRemoveW(RadixNode n, String str)
	{
		// busca / descida
		if (n != null)
		{
			RadixNode suitable_child = n.getChildAt(getPosition(str));
			if( suitable_child == null)
			{
				return null;				
			}
			else
			{
				int separator = getDiffCharIndex(str,suitable_child.getLabel());
				if( separator < str.length() && separator == suitable_child.getLength() )
				{
					str = str.substring(separator, str.length()); //?
					suitable_child = searchAndRemoveW(suitable_child,str);
					
					// Remoção 2º nível: 
					// o que acontece com o nó pai e e seus netos após a retirada do seu filho
					if(suitable_child == null) return null;
					else
					{
						return removeW(n,suitable_child);
					}					
				}
				else if( separator == str.length() && separator == suitable_child.getLength() )
				{
					// Fim da busca
					if(suitable_child.isEndOfString()) // encontrou a string;
					{
						// Remoção 1º nível
						
						// seta fim de string neste nó pra falso
						suitable_child.setEndOfString(false);
						
						// retorna 
						return removeW(n,suitable_child);						
					}
				}
				else return null;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param father
	 * @param child
	 * @return
	 */
	private RadixNode removeW(RadixNode father, RadixNode child)
	{
		if(child.isLeaf())	// se o filho é folha
		{
			father.removeChild(child);
			
			//se o pai só tem um filho e este não for fim de string, absorva-o para o pai
			if(father.currentChildsCounter() == 1 && father.isEndOfString() == false)
			{
				//acha filho único
				RadixNode only_child = father.getFirstNonNullChild();
				
				// concatena suas labels
				father.setLabel(father.getLabel() + only_child.getLabel());
				
				// atualiza o end of string do nó pai nó com o do seu filho
				father.setEndOfString(only_child.isEndOfString());
				
				// os netos viram filhos
				father.setChilds(only_child.getChilds());
				
				// o filho único é deletado
				father.removeChild(only_child);
			}
		}
		return father; // retorna este nó /o pai para ser a próxima criança analizada.
	}
	
	// ================================================ NÓ ================================================
	
	/**
	 * Simula um nó de uma árvore radix. Adaptado para o projeto.
	 * @author Natália Azevedo de Brito
	 */
	public class RadixNode 
	{
		/**
		 * Contem o pedaço de string que serve de caminho para este nó a partir do seu pai. (??)
		 */
		private String label;
		
		/**
		 * Peso desse nó. Indica em que posição do array de filhos ele deve ser guardado.
		 */
		private int ranking;
		
		/**
		 * Armazena os filhos desse nó
		 */
		private RadixNode[] childs;
		
		/**
		 * Guarda o número máximo de filhos que um nó pode ter.
		 */
		private final int MAX_CHILDS;
		
		/**
		 * Indica se esse nó é o fim de uma string.
		 */
		private boolean endOfString;
		
		/**
		 * Guarda informações sobre a origem de uma palavra. 
		 */
		private MyIndexItem indexitem;
		
		
		/**
		 * Constrói um nó vazio de uma árvore de alfabeto de tamanho 'N'.
		 */
		public RadixNode(int N)
		{
			MAX_CHILDS = N;
			this.endOfString = false;
			this.childs = new RadixNode[MAX_CHILDS];
		}
		
		/**
		 * Constrói um nó de uma árvore com o alfabeto de tamanho 'N', já com um label.
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
		 * Atualiza o peso/ranking desse nó.
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
		 * @return número de filho não nulos
		 */
		public int currentChildsCounter()
		{
			int sum = 0;
			for (int i = 0; i < MAX_CHILDS; i++)
			{
				if(childs[i] != null) sum++;
			}
			return sum;
		}
		
		/**
		 * @return número de filho não nulos
		 */
		public boolean isLeaf()
		{
			if(currentChildsCounter() == 0) return true;
			return false;
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
		 * @param index posição do nó filho
		 * @return nó filho da posição 'index' caso ele exista.
		 */
		public RadixNode getChildAt(int index) {
			return this.childs[index];
		}
		
		/**
		 * @param index posição do nó filho
		 * @return nó filho da posição 'index' caso ele exista.
		 */
		public RadixNode getFirstNonNullChild() {
			
			for (int i = 0; i < MAX_CHILDS; i++ )
			{
				if(childs[i] != null) return childs[i];
			}
			return null;
		}
		
		/**
		 * 
		 * @param n nó a ser adicionado como filho
		 */
		public void addChild(RadixNode n)
		{
			childs[n.getRanking()] = n;
		}
		
		/**
		 * 
		 * @param n nó filho a ser removido
		 */
		public void removeChild(RadixNode n)
		{
			if(n != null) childs[n.getRanking()] = null;
		}
	}
}
