package gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Scanner;

public class Filter {
	private ArrayList<String> blacklisted;
	private ArrayList<Character> invalidChars;
	
	public Filter() {
		File file = new File(System.getProperty("user.dir") + "/blacklist");
		try {
			Scanner sc = new Scanner(file).useDelimiter("/n");
			createBlacklist(sc);
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		file = new File(System.getProperty("user.dir") + "/invalid_characters");
		try {
			Scanner sc = new Scanner(file).useDelimiter("/n");
			createInvChars(sc);
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * 
	 * Funcao que cria um arraylist de Characters para salvar as caracteres considerados invalidos
	 * 
	 * @param  sc  Um objeto do tipo Scanner que lê o arquivo que foi passado
	 * */
	public void createInvChars(Scanner sc) {
		invalidChars = new ArrayList<Character>();
		while(sc.hasNext()) {
			invalidChars.add(sc.next().charAt(0));
		}
	}
	
	/**
	 * 
	 * Funcao que cria um arraylist de Strings para salvar as palavras da blacklist
	 * 
	 * @param  sc  Um objeto do tipo Scanner que lê o arquivo que foi passado
	 * */
	public void createBlacklist(Scanner sc) {
		blacklisted = new ArrayList<String>();
		String temp = "";
		while (sc.hasNext()) {
		      temp = sc.next();
		      blacklisted.add(temp);
		    }
	}
	
	/**
	 * 
	 * Funcao que checa se a palavra passada esta na blacklist
	 * 
	 * @param  word Palavra que sera checada
	 * @return retorna true se a palavra esta na blacklist e falso se nao estiver
	 * */
	public Boolean inBlacklist(String word) {
		for(String s : blacklisted) {
			if(word == s) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * Funcao que checa se a palavra possui um caractere invalido
	 * 
	 * @param  word Palavra que sera checada
	 * @return retorna true se a palavra possuir e falso se nao
	 * */
	public Boolean invalidString(String word) {
		for(char c : invalidChars) {
			if(word.indexOf(c) == -1) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Funcao para remover acentos de uma palavra
	 * 
	 * @param  word Palavra que sera normalizada
	 * @return A palavra ja normalizada
	 * */
	public String removeAccents(String word) {
		word = Normalizer.normalize(word, Normalizer.Form.NFD);
		return word.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
	}
	
	public static void main(String[] args ) {
		Filter filter = new Filter();
		System.out.println(filter.removeAccents("Rogério"));
	}
}
