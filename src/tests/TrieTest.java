package tests;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.Trie;

/**
 * Testes para a classe Trie.
 * @author Natália Azevedo de Brito
 */
class TrieTest {

	private Trie test;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		test = new Trie();
		test.insertWord("aba");
		test.insertWord("abe");
		test.insertWord("ab");
	}

	/**
	 * Testa se a busca consegue achar uma palavra inserida.
	 */
	@Test
	void testinsertion1() {
		assertEquals(true,test.search("aba"));
		assertEquals(true,test.search("abe"));
		assertEquals(true,test.search("ab"));
		assertEquals(false,test.search("abc"));
	}
	
	/**
	 * Testa a remoção.
	 */
	@Test
	void testremove1() {
		test.removeWord("aba");
		assertEquals(false,test.search("aba"));
		assertEquals(true,test.search("abe"));
		assertEquals(true,test.search("ab"));
	}
	
	/**
	 * Testa a remoção.
	 */
	@Test
	void testremove2() {
		test.removeWord("ab");
		assertEquals(true,test.search("aba"));
		assertEquals(true,test.search("abe"));
		assertEquals(false,test.search("ab"));
	}
	
	/**
	 * Testa a remoção.
	 */
	@Test
	void testremove3() {
		test.removeWord("ab");
		test.removeWord("aba");
		assertEquals(false,test.search("aba"));
		assertEquals(true,test.search("abe"));
		assertEquals(false,test.search("ab"));
	}
	
	/**
	 * Testa a remoção.
	 */
	@Test
	void testremove4() {
		test.removeWord("aba");
		test.removeWord("ab");
		assertEquals(false,test.search("aba"));
		assertEquals(true,test.search("abe"));
		assertEquals(false,test.search("ab"));
	}
	
	/**
	 * Testa a remoção.
	 */
	@Test
	void testremove5() {
		test.removeWord("aba");
		test.removeWord("abe");
		test.removeWord("ab");
		assertEquals(false,test.search("aba"));
		assertEquals(false,test.search("abe"));
		assertEquals(false,test.search("ab"));
	}
	
	/**
	 * Testa o coletor de strings.
	 */
	@Test
	void testcollector() {
		ArrayList<String> strings = test.getStrings();
		boolean foundABA = false;
		boolean foundABE = false;
		boolean foundAB = false;
		
		for (String s : strings)
		{
			if (s.equals("aba")) foundABA = true;
			else if (s.equals("abe")) foundABE = true;
			else if (s.equals("ab")) foundAB = true;
		}
		
		assertEquals(true,foundABA);
		assertEquals(true,foundABE);
		assertEquals(true,foundAB);
	}
}
