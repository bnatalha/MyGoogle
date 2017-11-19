package domain;

import java.util.ArrayList;
import java.util.List;

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
	 * @return um ArrayList com todas as strings da árvore.
	 */
	public ArrayList<String> getAllStrings()
	{
		return collectStrings("", true);
	}
	
	/**
	 * @return um ArrayList com todas as strings da que tem um determinado prefixo em comum.
	 * @param str prefixo buscado.
	 */
	public ArrayList<String> getAllPrefixedStrings(String str)
	{
		return collectStrings(str, false);
	}
	
	/**
	 * Procura por uma palavra na árvore.
	 * @param str palavra a ser procurada.
	 * @return 'true' caso a palavra seja encontrada.
	 */
	private ArrayList<String> collectStrings(String str, boolean WHOLE_WORD)
	{
		if(str != null)
		{
			ArrayList<String> collected  = new ArrayList<String>(); //
			RadixNode startingNode =  WHOLE_WORD? root : searchW(root,str,false);
			StringBuilder str_buildr = new StringBuilder(
					WHOLE_WORD ? "":
						str.substring(0,
									startingNode.getFatherHeight() + startingNode.getLength() - 1 )
					);	// constrói uma string usando 'str' como prefixo
			return collector(startingNode,str_buildr,collected);
		}
		return null;
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
			RadixNode result = searchW(root,str,true);
			if (result == null)
				return false;
			else
				return result.isEndOfString();
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
	 * Caso WHOLE_WORD seja 'true', retorna um nó onde tenha achado o fim para a string 'str';
	 * Caso contrário, retorna o nó cujos descendentes tenha como prefixo 'str'.
	 * @param n
	 * @param str
	 * @return null ou uma referência para o nó buscado.
	 */
	private RadixNode searchW(RadixNode n, String str, boolean WHOLE_WORD)
	{
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
					return searchW(suitable_child,str,WHOLE_WORD);
				}
				else if( separator < str.length() && separator < suitable_child.getLength() )
				{
					return null;	
				}
				else if( separator == str.length() && separator < suitable_child.getLength() )
				{
					return (WHOLE_WORD? null: suitable_child);
				}
				else if( separator == str.length() && separator == suitable_child.getLength() )
				{
					return suitable_child;
				}
			}
		}
		return null;
	}
	
	
	/**
	 * Coleta todas as strings desta árvore e as retorna em um ArrayList.
	 * 
	 * @param n nó que servirá de inicio para a analise.
	 * @param s armazenará os caracteres a medida que for passando pelos nós desta árvore
	 * @param queue armazenará as strings desta árvore
	 * @return um List<String> com todas as strings armazenadas nesta árvore a partir de um determinado nó.
	 */
	private ArrayList<String> collector(RadixNode n, StringBuilder s, ArrayList<String> queue)
	{
		// DESCIDA
		//
		if(n.getLabel() != null)	// se não for raiz;
			s.append(n.getLabel());	// adiciona o valor associado ao nó
		
		if(n.isEndOfString() == true) 
			queue.add(s.toString());
			
		for(int i = 0; i < ALPHABET_SIZE; i++)	// para todos os nós filhos de 'n'
		{
			if(n.getChilds()[i] != null)
				queue = collector(n.getChilds()[i], s, queue);	// descer por eles e ir coletando seus valores
		}
		// SUBIDA
		//
		if(n.getLabel() != null) // se não for raiz; 
			s.delete(n.getFatherHeight(),	s.length());	// deleta o ultimo caractere adicionado.
		
		return queue;		
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
					
					// Divide o nó escolhido e retorna o novo nó mais alto
					RadixNode newNode = makeChildFrom(suitable_child, separator, false);
					
					//insere o novo pedaço de string para ser filho do no original
					insertW(suitable_child,str);
				}
				else if( separator == str.length() && separator < suitable_child.getLength() )
				{
					// Divide o nó escolhido e retorna o novo nó mais alto
					RadixNode newNode = makeChildFrom(suitable_child, separator, true);
				}
				else if( separator == str.length() && separator == suitable_child.getLength() )
				{
					suitable_child.setEndOfString(true);
				}
			}
		}
	}
	
	private RadixNode makeChildFrom(RadixNode n, int separator, boolean END_OF_STRING)
	{
		// DIVISÃO
		// cria uma nova string com o sufixo diferente pertencente ao label do nó original
		String newLabel = n.getLabel().substring(separator);
		
		// Cria um nó com o label cortado do nó original
		RadixNode newNode = new RadixNode(ALPHABET_SIZE,newLabel);	
		
		// modifica a label do nó original para o prefixo em comum 
		n.setLabel(n.getLabel().substring(0,separator));
		
		// modifica altura do pai do novo nó guardada nele
		newNode.setFatherHeight(n.getFatherHeight() +
				n.getLength());
		
		// REORGANIZANDO FILHOS
		// move os filhos do nó analizado para o novo nó
		newNode.setChilds(n.getChilds());
		
		// cria um novo array de filhos vazio para o nó original
		n.setChilds(new RadixNode[ALPHABET_SIZE]);
		
		// faz com que o novo nó indica um final de uma string caso o original seja.
		newNode.setEndOfString(n.isEndOfString());
		
		// seta endOfString para o valor de END_OF_STRING
		n.setEndOfString(END_OF_STRING);
		
		//adiciona o novo nó para ser filho do original
		n.addChild(newNode);
		
		return newNode;
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
		 * Armazena a altura do nó pai. 
		 */
		private int fatherHeight;
		
		/**
		 * Guarda informações sobre a origem de uma palavra. 
		 */
		private IndexItem indexitem;
		
		
		/**
		 * Constrói um nó vazio de uma árvore de alfabeto de tamanho 'N'.
		 */
		public RadixNode(int N)
		{
			MAX_CHILDS = N;
			this.endOfString = false;
			this.childs = new RadixNode[MAX_CHILDS];
			
			for (int i = 0; i < MAX_CHILDS; i++)
			{
				if(childs[i] != null) childs[i].setFatherHeight( this.getLength() + this.fatherHeight);
			}
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
			
			for (int i = 0; i < MAX_CHILDS; i++)
			{
				if(childs[i] != null) childs[i].setFatherHeight(this.getLength());
			}
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
			if(label != null )return label.length();
			return 0;
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
		 * Retorna a altura do nó pai deste
		 */
		public int getFatherHeight()
		{
			return this.fatherHeight;
		}
		
		/**
		 * Fixa a altura do nó pai a este. 
		 */
		public void setFatherHeight(int fatherHeight)
		{
			this.fatherHeight = fatherHeight;
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
			
			for (int i = 0; i < MAX_CHILDS; i++)
			{
				if(childs[i] != null) childs[i].setFatherHeight(this.getLength() + this.fatherHeight);
			}
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
		public IndexItem getIndexItem() {
			return indexitem;
		}

		/**
		 * @param indexitem the indexitem to set
		 */
		public void setMyIndexItem(IndexItem indexitem) {
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
			n.setFatherHeight(this.getLength() + this.fatherHeight);
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
