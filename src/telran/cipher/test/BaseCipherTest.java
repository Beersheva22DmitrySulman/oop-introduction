package telran.cipher.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

import telran.cipher.BaseCipher;

class BaseCipherTest {

	@Test
	void BaseChiperTest() {
		Random random = new Random();
		// 100 tests with random key length (from 2 to 94) and random input non-negative integer for encode
		for (int i = 0; i < 100; i++) {			
			BaseCipher cipher = new BaseCipher(random.nextInt(2, 95));
			int number = random.nextInt(0, Integer.MAX_VALUE);
			String numberEncoded = cipher.cipher(number);
			System.out.printf("Key length: %d, key: %s, number: %d, number encoded: %s\n", 
					cipher.getKey().length(), 
					cipher.getKey(), 
					number, 
					numberEncoded);
			assertEquals(number, cipher.decipher(numberEncoded));
		}
		
		// test zero number
		BaseCipher cipher = new BaseCipher(random.nextInt(2, 95));
		int number = 0;
		String numberEncoded = cipher.cipher(number);
		System.out.printf("Key length: %d, key: %s, number: %d, number encoded: %s\n", 
				cipher.getKey().length(), 
				cipher.getKey(), 
				number, 
				numberEncoded);
		assertEquals(number, cipher.decipher(numberEncoded));
		
		// test min/max key length
		BaseCipher cipher1 = new BaseCipher(1000);
		assertEquals(94, cipher1.getKey().length());
		BaseCipher cipher2 = new BaseCipher(0);
		assertEquals(2, cipher2.getKey().length());
	}
}
