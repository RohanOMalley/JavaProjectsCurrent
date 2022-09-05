import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class WikiTester {
    @Test
    public void testPut() {
    	Set<String> yuh = WikiScraper.findWikiLinks("Milkshake");
    	System.out.println(yuh);
    }
}
