package gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Scanner;

public class Filter {
	private File blacklist;
	private ArrayList<String> blacklisted;
	
	public Filter() {
		blacklist = new File(System.getProperty("user.dir") + "/blacklist");
		try {
			Scanner sc = new Scanner(blacklist).useDelimiter("/n");
			createBlacklist(sc);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void createBlacklist(Scanner sc) {
		blacklisted = new ArrayList<String>();
		String temp = "";
		while (sc.hasNext()) {
		      temp = sc.next();
		      blacklisted.add(temp);
		    }
		sc.close();
	}
	
	public Boolean filtering(String word) {
		for(String s : blacklisted) {
			if(word == s) {
				return true;
			}
		}
		return false;
	}
	public void print() {
		for(String s : blacklisted) {
			System.out.println(s + "\n");
		}
	}
	
	public static String removeAccents(String word) {
		word = Normalizer.normalize(word, Normalizer.Form.NFD);
		word = word.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
		return word;
	}
	
	public static void main(String[] args ) {
		Filter filter = new Filter();
		filter.print();
	}
}
