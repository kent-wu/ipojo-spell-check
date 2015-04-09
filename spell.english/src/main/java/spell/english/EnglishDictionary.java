package spell.english;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import spell.services.DictionaryService;

@Component
@Provides
@Instantiate
public class EnglishDictionary implements DictionaryService{

    String[] dictionary = { "welcome", "to", "the", "ipojo", "tutorial" };

    @Override
    public boolean checkWord(String word) {
        word = word.toLowerCase();

        for (String dict : dictionary) {
            if (dict.equals(word)) {
                return true;
            }
        }

        return false;
    }
}
