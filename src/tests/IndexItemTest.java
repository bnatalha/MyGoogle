package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.IndexItem;

class IndexItemTest {
	IndexItem ii;
	
	@BeforeEach
	void setUp() throws Exception {
		
	}

	@Test
	void create() {
		ii = new IndexItem("apple.txt");
		
	}

}
