package domain;

import java.util.ArrayList;

/**
 * Se tiver mais de uma palavra por linha?
 * @author Nat�lia Azevedo de Brito
 */
public class IndexItem
{
	/**
	 * Palavra indexada 	 
	 */
	private final String word; // ?
	
	/**
	 * Armazena um conjunto de informa��es referentes aos arquivos fonte onde este item foi encontrado.
	 */
	private ArrayList<IndexItemSource> sources;
	
	/**
	 * Constr�i um MyIndexItem a partir de uma palavra.
	 */
	public IndexItem(String word)
	{
		this.word = word;
		this.sources = new ArrayList<IndexItemSource>();
	}
	
	/**
	 * Adiciona uma ocorrencia do item na linha 'line' do arquivo 'filename'.
	 * @param filename nome do arquivo em que a ocorr�ncia ser� adicionada
	 * @param line linha da ocorr�ncia
	 */
	public void addOcurrenceAt(String filename, int line)
	{
		IndexItemSource in_src = findSource(filename);	// pesquisa pelo IndexSource de 'filename'
		
		// caso n�o encontre, cria um IndexSource para este arquivo e o adiciona as fontes
		if(in_src == null)	
		{
			in_src = new IndexItemSource("filename");	
			sources.add(in_src);
		}
		
		in_src.addLine(new Integer(line));	// adiciona a ocorr�ncia ao IndexSource do arquivo.
	}
	
	/**
	 * Pesquisa pelo IndexSource de um arquivo em 'sources'.
	 * @param filename
	 * @return Se encontrar, retorna uma refer�ncia para o IndexSource. Caso n�o encontre, retorna 'null'. 
	 */
	private IndexItemSource findSource(String filename)
	{
		for(IndexItemSource s : sources)	// Procura pelo IndexSource do arquivo 'filename'
			if(s != null)
				if(s.getFilename().equals(filename))	// se Achou uma IndexSource para este arquivo
					return s;
		
		return null;
	}
	
	/**
	 * @return Uma string organizada com todas as ocorr�ncias dessa palavra no banco de dados.
	 */
	public String toString()
	{
		StringBuilder sb = new StringBuilder("\""+ word + "\":");
		if(sources.isEmpty())
		{
			sb.append("\n\tNot Found.");
		}
		else
		{
			for(IndexItemSource iis : sources)
			{
				sb.append("\n\t" + iis.toString());
			}
		}		
		return sb.toString();
	}
	
	/**
	 * Lugares onde podem ser encontrados a palavra indexada
	 */
	private class IndexItemSource
	{		
		/**
		 * Nome do arquivo referente as ocorr�ncias
		 */
		private final String filename;
				
		/**
		 * Linhas onde a palavra foi encontrada
		 */		
		private ArrayList<Integer> ocurrences;
		
		/**
		 * Constr�i um MyIndexItem para um arquivo .txt
		 * @param filename nome do arquivo .txt
		 */
		public IndexItemSource(String filename)
		{
			this.filename = filename;
			this.ocurrences = new ArrayList<Integer>();
		}
		
		/**
		 * Adiciona uma ocorr�ncia na linha 'line'.
		 */
		public void addLine(int line) {
			ocurrences.add(line);
		}

		/**
		 * @return the filename
		 */
		public String getFilename() {
			return filename;
		}
		
		/**
		 * @return the ocurrences
		 */
		public ArrayList<Integer> getOcurrences() {
			return ocurrences;
		}
		
		/**
		 * @return Uma string organizada com as ocorr�ncias encontradas neste arquivo.
		 */
		@Override
		public String toString() {
			
			StringBuilder sb = new StringBuilder("On file '" + filename + "', ");	
			
			// Se n�o tiver linhas, n�o houveram ocorr�ncias.
			if(ocurrences.isEmpty())
			{
				sb.append("no ocurrences.");
			}
			// caso contr�rio, houveram ocorr�ncias
			else
			{
				sb.append("found at:\n");
				
				for (Integer i : ocurrences)	// acrescenta as linhas onde houve ocorr�ncia
					sb.append(" line "+ i + ";");
				
				sb.deleteCharAt(sb.length()-1);	//deleta �ltima virgula inserida
				sb.append('.');	// coloca um ponto final.
			}
			return sb.toString();
		}
		
		/**
		 * @return 'true' se n�o existir ocorr�ncias para este arquivo.
		 */
		public boolean isEmpty() {
			return ocurrences.isEmpty();
		}
	}	
	
}
