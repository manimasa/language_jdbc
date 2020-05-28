
package ax.ha.it.languages;
import java.util.List;

/**
 * 
 * @author dell
 * All methods that will be called from jdbcLanguageDao
 * are defined here and implemented there
 */
public interface LanguageDao {
	List<Language> getLanguages();

	void addLanguage(Language language);

	List<Language> findLanguageByName(String name);
	
	Language findLanguageById(long id);
	
	void updateLanguage(Language language);	

}
