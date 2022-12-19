package telran.util;

import java.util.Comparator;

public class IntSortComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer n1, Integer n2) {
		int res = 0;
		if (n1 % 2 == 1 && n2 % 2 == 1) {
			res = -Integer.compare(n1, n2);
		} else if (n1 % 2 == 1) {
			res = 1;
		} else if (n2 % 2 == 1) {
			res = -1;
		} else {
			res = Integer.compare(n1, n2);
		}
		return res;
	}

}
