package ax.ha.it.languages;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class LanguagesJdbcApplication implements CommandLineRunner{

	@Autowired
	LanguageService service;
	
	public static void main(String[] args) {
		SpringApplication.run(LanguagesJdbcApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
			System.out.println("Adding some languages");
			Language java = new Language();
			java.setName("Java");
			java.setPopularity(3);

			Language cplusplus = new Language();
			cplusplus.setName("C++");
			cplusplus.setPopularity(-1);			
			
			service.addLanguage(java);
			service.addLanguage(cplusplus);

			List<Language> languages = service.getLanguages();
			for (Language l : languages) {
				System.out.println(l);
			}

			System.out.println("Changing popularity");			
			service.upvote(java);
			service.downvote(cplusplus);

			languages = service.getLanguages();
			for (Language l : languages) {
				System.out.println(l);
			}		

			
			System.out.println("Finding by name (java)");
			languages = service.findLanguageByName("Java");
			for (Language l : languages) {
				System.out.println(l);
			}

		}

	}

