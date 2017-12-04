package gui;

import java.util.ArrayList;

public class Busca {
	
	private int tipo;
	private String entrada;
	private ArrayList<String> texto = new ArrayList<String>();
	private ArrayList<IndexItem> dados; 
	
	/**
	 * Construtores
	 * */
	public Busca(String entrada) {
		this.entrada= entrada;
		tipo = this.tipoPesquisa(entrada);
		dados = new ArrayList<IndexItem>();
		//texto = new ArrayList<String>();
		IndexItem temp = new IndexItem(entrada);
		dados.add(temp);
	}
	
	/**
	 * Funcao responsavel por adicionar uma ocorrencia
	 * 
	 * @param	arquivo		Nome do arquivo
	 * @param	linha		Linha do arquivo em que foi encontrada a palavra
	 * */
	public void casoBusca(String arquivo, int linha, int k) {
		
		IndexItem ii = new IndexItem(this.texto.get(k));
		ii.addOcurrenceAt(arquivo, linha);
		dados.add(ii);
		//System.out.println("\n\nIndexItem Criado:\n" + ii);
	}
	
	public void imprimir() {
		int k = dados.size() -1;
		System.out.println("\n\nImprimindo todos os " + k +" IndexItens:\n");
		System.out.println(dados);
	}
	
	public boolean andConfirm() {
		int k = 0;
		for(int i = 0; i < dados.size(); i++) {
			for(int j = i; j < dados.size(); j++) {
				if(dados.get(i).getSources().get(i).getFilename().equals(dados.get(j).getSources().get(j).getFilename())){
					k++;
				}
			}
		}
		if(k+1 == dados.size())
			return true;
		return false;
	}
	
	/**
	 * Gets e sets
	 * */
	public int getTipo() {return tipo;}
	public String getEntrada() {return entrada;}
	public void setEntrada(String entrada) {this.entrada = entrada;}
	public ArrayList<IndexItem> getDados() {return dados;}
	
	/**
	 * Funca responsavel por checar se a entrada do usuario contem "or" ou "and".
	 * 
	 * @param	texto	Recebe a entrada do usuario.
	 * @return 	 1		Para o caso de ser "or".
	 * 			-1		Para o caso de ser "and".
	 * 			 0		Para o caso de nao ser nenhuma.
	 * */
	public int tipoPesquisa(String entrada) {
		int i = 0;
		String[] temp;
		
		if(entrada.toLowerCase().contains("<or>".toLowerCase())) {
			temp = entrada.split(" <or> ");
			for(String s : temp) { texto.add(s); }
			i = 1;
		}
		else if(entrada.toLowerCase().contains("<and>".toLowerCase())) {
			temp = entrada.split(" <and> ");
			for(String s : temp) { texto.add(s); }
			i = -1;
		}
		else {
			texto.add(entrada);
		}
		return i;
	}

	public String getTexto(int k) {
		if(k<numeroPesquisas())
			return texto.get(k);
		return null;
	}
	
	public int numeroPesquisas() {
		return texto.size();
	}
}
