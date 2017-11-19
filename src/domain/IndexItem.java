package domain;

import java.util.ArrayList;

/**
 * Se tiver mais de uma palavra por linha?
 * @author Natália Azevedo de Brito
 */
public class IndexItem
{
	/**
	 * Palavra indexada 	 
	 */
	private final String word; // ?
	
	/**
	 * Armazena um conjunto de informações referentes aos arquivos fonte onde este item foi encontrado.
	 */
	private ArrayList<IndexItemSource> sources;
	
	/**
	 * Constrói um MyIndexItem a partir de uma palavra.
	 */
	public IndexItem(String word)
	{
		this.word = word;
		this.sources = new ArrayList<IndexItemSource>();
	}
	
	/**
	 * Adiciona uma ocorrencia do item na linha 'line' do arquivo 'filename'.
	 * @param filename nome do arquivo em que a ocorrência será adicionada
	 * @param line linha da ocorrência
	 */
	public void addOcurrenceAt(String filename, int line)
	{
		IndexItemSource in_src = findSource(filename);	// pesquisa pelo IndexSource de 'filename'
		
		// caso não encontre, cria um IndexSource para este arquivo e o adiciona as fontes
		if(in_src == null)	
		{
			in_src = new IndexItemSource("filename");	
			sources.add(in_src);
		}
		
		in_src.addLine(new Integer(line));	// adiciona a ocorrência ao IndexSource do arquivo.
	}
	
	/**
	 * Pesquisa pelo IndexSource de um arquivo em 'sources'.
	 * @param filename
	 * @return Se encontrar, retorna uma referência para o IndexSource. Caso não encontre, retorna 'null'. 
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
	 * @return Uma string organizada com todas as ocorrências dessa palavra no banco de dados.
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
		 * Nome do arquivo referente as ocorrências
		 */
		private final String filename;
				
		/**
		 * Linhas onde a palavra foi encontrada
		 */		
		private ArrayList<Integer> ocurrences;
		
		/**
		 * Constrói um MyIndexItem para um arquivo .txt
		 * @param filename nome do arquivo .txt
		 */
		public IndexItemSource(String filename)
		{
			this.filename = filename;
			this.ocurrences = new ArrayList<Integer>();
		}
		
		/**
		 * Adiciona uma ocorrência na linha 'line'.
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
		 * @return Uma string organizada com as ocorrências encontradas neste arquivo.
		 */
		@Override
		public String toString() {
			
			StringBuilder sb = new StringBuilder("On file '" + filename + "', ");	
			
			// Se não tiver linhas, não houveram ocorrências.
			if(ocurrences.isEmpty())
			{
				sb.append("no ocurrences.");
			}
			// caso contrário, houveram ocorrências
			else
			{
				sb.append("found at:\n");
				
				for (Integer i : ocurrences)	// acrescenta as linhas onde houve ocorrência
					sb.append(" line "+ i + ";");
				
				sb.deleteCharAt(sb.length()-1);	//deleta última virgula inserida
				sb.append('.');	// coloca um ponto final.
			}
			return sb.toString();
		}
		
		/**
		 * @return 'true' se não existir ocorrências para este arquivo.
		 */
		public boolean isEmpty() {
			return ocurrences.isEmpty();
		}
	}	
	
}
