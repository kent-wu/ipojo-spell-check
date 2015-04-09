package spell.checker;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Requires;
import spell.services.DictionaryService;
import spell.services.SpellChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Component
@Provides
@Instantiate
public class SpellCheck implements SpellChecker{

    @Requires
    private DictionaryService dictionary;

    @Override
    public String[] check(String passage) {
        // No misspelled words for an empty string.
        if ((passage == null) || (passage.length() == 0)) {
            return null;
        }

        List<String> errorList = new ArrayList<String>();

        // Tokenize the passage using spaces and punctuation.
        StringTokenizer st = new StringTokenizer(passage, " ,.!?;:");

        // Loop through each word in the passage.
        while (st.hasMoreTokens()) {
            String word = st.nextToken();

            // Check the current word.
            if (!dictionary.checkWord(word)) {
                // If the word is not correct, then add it
                // to the incorrect word list.
                errorList.add(word);
            }
        }

        // Return null if no words are incorrect.
        if (errorList.size() == 0) {
            return null;
        }

        // Return the array of incorrect words.
        System.out.println("Wrong words:" + errorList);
        return errorList.toArray(new String[errorList.size()]);
    }
}
