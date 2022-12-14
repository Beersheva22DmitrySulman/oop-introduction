package telran.cipher;

import java.util.Random;

public class BaseCipher {
	private String key;

	private static final int MIN_CODE = 33;
	private static final int MAX_CODE = 126;

	private static final int MIN_LENGTH = 2;
	private static final int MAX_LENGTH = MAX_CODE - MIN_CODE + 1;

	public BaseCipher(int length) {
		length = Math.max(length, MIN_LENGTH);
		length = Math.min(length, MAX_LENGTH);
		key = generateKey(length);
	}

	public String cipher(int number) {
		if (number < 0) {
			throw new IllegalArgumentException("Number should be non-negative.");
		}
		int numberLength = getNumberLength(number);
		int base = key.length();
		char[] charArray = new char[numberLength];
		for (int i = numberLength - 1; i >= 0; i--) {
			charArray[i] = key.charAt(number % base);
			number /= base;
		}

		return String.valueOf(charArray);
	}

	public int decipher(String cipher) {
		int cipherLength = cipher.length();
		int base = key.length();
		int res = 0;
		for (int i = 0; i < cipherLength; i++) {
			int digit = key.indexOf(cipher.charAt(i));
			if (digit == -1) {
				throw new IllegalArgumentException();
			}
			res = res * base + digit;
		}

		return res;
	}

	public String getKey() {
		return key;
	}

	private String generateKey(int length) {
		char[] charArray = new char[length];
		boolean[] helper = new boolean[MAX_CODE - MIN_CODE + 1];
		for (int i = 0; i < length; i++) {
			int num = generateUniqueRandomNumber(helper);
			helper[num - MIN_CODE] = true;
			charArray[i] = (char) num;
		}

		return String.valueOf(charArray);
	}

	private int generateUniqueRandomNumber(boolean[] helper) {
		Random random = new Random();
		int num;
		do {
			num = random.nextInt(MIN_CODE, MAX_CODE + 1);
		} while (helper[num - MIN_CODE]);

		return num;
	}

	private int getNumberLength(int number) {
		int base = key.length();
		int res = 0;
		do {
			number /= base;
			res++;
		} while (number != 0);

		return res;
	}
}
