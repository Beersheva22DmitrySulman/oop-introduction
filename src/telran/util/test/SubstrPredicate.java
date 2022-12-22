package telran.util.test;

import java.util.function.Predicate;

public class SubstrPredicate implements Predicate<String> {
	private String substr;
	
	public SubstrPredicate(String substr) {
		this.substr = substr;
	}
	
	@Override
	public boolean test(String t) {
		return t.contains(substr);
	}

}
