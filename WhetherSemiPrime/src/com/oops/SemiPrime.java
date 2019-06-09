package com.oops;


class SemiPrime {

	static boolean run(int number) {
		int numberToCheckfor = 0;
		for (int counter = 2; numberToCheckfor < 2 && counter * counter <= number; ++counter) {
			while (number % counter == 0) {
				number /= counter;
				++numberToCheckfor;
			}
		}
		if (number > 1)
			++numberToCheckfor;
		return numberToCheckfor == 2 ? true : false;
	}

	static boolean semiprime(int n) {
		return run(n);
	}

	public static void main(String[] args) {
		int n = 0;
		semiprime(n);
		System.out.println(semiprime(n));
		n = -91;
		System.out.println(semiprime(n));
		
		// 6, 10, 14, 15, 21, 22, 26, 33, 34, 35, 38, 39, 46, 51, 55, 57, 58, 62, 65, 69, 74, 77, 82, 85, 86, 87, 91, 93, 94, 95,
	}
}
