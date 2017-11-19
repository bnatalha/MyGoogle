package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.RadixTree;

class RadixTest {
	
	RadixTree tree;
	final int ALPHABET_SIZE = 26;

	@BeforeEach
	void setUp() throws Exception {
		tree = new RadixTree(ALPHABET_SIZE);
	}
	
	/**
	 * Busca em �rvore vazia
	 */
	@Test
	void testSearch1() {
		assertEquals(false,tree.searchWord("a"));
	}

	/**
	 * Inser��o numa �rvore vazia.
	 */
	@Test
	void testIsertion1() {
		tree.insertWord("a");
		assertEquals(true,tree.searchWord("a"));
	}
	
	/**
	 * Remo��o numa �rvore vazia.
	 */
	@Test
	void testRemove1() {
		tree.removeWord("a");
		assertEquals(false,tree.searchWord("a"));
	}
	
	/**
	 * Inser��o de "a" em uma �rvore com "ab"
	 */
	@Test
	void testIsertion2() {
		tree.insertWord("ab");
		tree.insertWord("a");
		assertEquals(true,tree.searchWord("a"));
		assertEquals(true,tree.searchWord("ab"));
	}
	
	/**
	 * Remo��o de uma folha que possua irm�os e que tenha um pai que � fim de string. 
	 */
	@Test
	void testRemove2() {
		tree.insertWord("abc");
		tree.insertWord("abd");
		tree.insertWord("abe");
		
		tree.removeWord("abd");
		
		assertEquals(true,tree.searchWord("abc"));
		assertEquals(false,tree.searchWord("abd"));
		assertEquals(true,tree.searchWord("abe"));
	}
	
	/**
	 * Inser��o de "abc" em uma �rvore com "abd". Buscar por "ab" e "a". "abc" e "abd"
	 */
	@Test
	void testIsertion3() {
		tree.insertWord("abc");
		tree.insertWord("abd");
		assertEquals(false,tree.searchWord("a"));
		assertEquals(false,tree.searchWord("ab"));
		assertEquals(true,tree.searchWord("abc"));
		assertEquals(true,tree.searchWord("abd"));
	}
	
	/**
	 * Remo��o de uma folha que possua um irm�o com filhos,
	 * onde o pai n�o � fim de string.
	 */
	@Test
	void testRemove3() {
		tree.insertWord("a"); //Av� de "X"
		//"abc": Pai de "X"
		tree.insertWord("abf");	// folha "X" ser removida  
		tree.insertWord("abde"); // irm�o de "X"
		tree.insertWord("abdeg"); // "sobrinho de "X"
		tree.insertWord("abdeh"); // "sobrinho de "X"
		
		tree.removeWord("abf");
		
		
		assertEquals(true,tree.searchWord("a"));
		//
		assertEquals(false,tree.searchWord("abf"));
		assertEquals(true,tree.searchWord("abde"));
		assertEquals(true,tree.searchWord("abdeg"));
		assertEquals(true,tree.searchWord("abdeh"));
	}
	
	/**
	 * Inser��o de "abc" em uma �rvore com "abd" e "a". Buscar por "ab", "a", "b", "abc" e "abd".
	 */
	@Test
	void testIsertion4() {
		tree.insertWord("abc");
		tree.insertWord("abd");
		tree.insertWord("a");
		assertEquals(true,tree.searchWord("a"));
		assertEquals(false,tree.searchWord("b"));
		assertEquals(false,tree.searchWord("ab"));
		assertEquals(true,tree.searchWord("abc"));
		assertEquals(true,tree.searchWord("abd"));
	}

}
