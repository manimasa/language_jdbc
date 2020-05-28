package ax.ha.it.languages;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
/**
 * Connect to database
 * @author dell
 *
 */
public class JdbcLanguageDao implements LanguageDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	private LanguageRowMapper languageRowMapper;

	private static final String CREATE_TABLE_SQL = "create table language ("
			+ "id INTEGER AUTO_INCREMENT, " + "name VARCHAR(40), "
			+ "popularity INTEGER, " + "PRIMARY KEY (id))";

	private static final String DROP_TABLE_SQL = "drop table if exists language";

	private static final String CREATE_LANGUAGE_SQL = "insert into language (name, popularity) "
			+ "values (:name, :popularity)";

	private static final String GET_ALL_LANGUAGES_SQL = "select * from language";

	private static final String FIND_ALL_BY_NAME_LIKE_SQL = "select * from language where name like :name";

	private static final String UPDATE_LANGUAGE_SQL = "update language set name = :name, popularity = :popularity where id = :id";

	private static final String GET_LANGUAGE_WITH_ID_SQL = "select * from language where id = :id";

	@Autowired
	//Constructor 
	public JdbcLanguageDao(NamedParameterJdbcTemplate jdbcTemplate,
			LanguageRowMapper languageRowMapper) {
		this.jdbcTemplate = jdbcTemplate;
		this.languageRowMapper = languageRowMapper;
		jdbcTemplate.getJdbcOperations().execute(DROP_TABLE_SQL);
		jdbcTemplate.getJdbcOperations().execute(CREATE_TABLE_SQL);
	}

	@Override
	public List<Language> getLanguages() {
		return jdbcTemplate.query(GET_ALL_LANGUAGES_SQL, languageRowMapper);
	}

	@Override
	public void addLanguage(Language language) {
		SqlParameterSource params = new MapSqlParameterSource().addValue(
				"name", language.getName()).addValue("popularity",
				language.getPopularity());

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(CREATE_LANGUAGE_SQL, params, keyHolder);
		language.setId(keyHolder.getKey().longValue());

	}

	@Override
	public List<Language> findLanguageByName(String name) {
		SqlParameterSource params = new MapSqlParameterSource("name", "%"
				+ name + "%");
		return jdbcTemplate.query(FIND_ALL_BY_NAME_LIKE_SQL, params,
				languageRowMapper);
	}

	@Override
	public void updateLanguage(Language language) {
		SqlParameterSource params = new MapSqlParameterSource().
				addValue("id",language.getId()).
				addValue("name", language.getName()).
				addValue("popularity", language.getPopularity());
		jdbcTemplate.update(UPDATE_LANGUAGE_SQL, params);
	}

	@Override
	//Since there is a unik language by id get the returned language therefore .get(0)
	public Language findLanguageById(long id) {

		SqlParameterSource params = new MapSqlParameterSource("id", id);
		return jdbcTemplate.query(GET_LANGUAGE_WITH_ID_SQL, params,
				languageRowMapper).get(0);
	}
	
	// Only static inner classes can be autowired
	@Component
	public static class LanguageRowMapper implements RowMapper<Language> {

		@Override
		public Language mapRow(ResultSet resultSet, int rowNum)
				throws SQLException {

			Language language = new Language();
			language.setId(resultSet.getLong(1));
			language.setName(resultSet.getString(2));
			language.setPopularity(resultSet.getInt(3));

			return language;
		}

	}	

}

