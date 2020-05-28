package ax.ha.it.languages;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly=true)
public class LanguageService {

	@Autowired
	private LanguageDao languageDao;
	
	public List<Language> getLanguages() {	
		return languageDao.getLanguages();
	}
	
	public List<Language> findLanguageByName(String name) {
		return languageDao.findLanguageByName(name);
	}

	@Transactional
	public void addLanguage(Language language) {
		languageDao.addLanguage(language);		
	}	
	
	@Transactional 
	public Language upvote(Language language) {
		return changePopularity(language, +1);
	}
	
	@Transactional 
	public Language downvote(Language language) {
		return changePopularity(language, -1);
	}	
	
	private Language changePopularity(Language language, int amount) {
		Language stored = languageDao.findLanguageById(language.getId());
		if (stored != null) {
			stored.setPopularity(stored.getPopularity()+amount);
			languageDao.updateLanguage(stored);
		}
		return stored;
	}
	
}
