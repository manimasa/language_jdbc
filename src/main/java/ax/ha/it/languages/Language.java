package ax.ha.it.languages;

public class Language {
	
	private Long id;
	
	private String name;
	
	private int popularity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPopularity() {
		return popularity;
	}

	public void setPopularity(int popularity) {
		if (popularity >= 0) {
			this.popularity = popularity;
		}
	}

	@Override
	public String toString() {
		return "Language [id=" + id + ", name=" + name + ", popularity="
				+ popularity + "]";
	}
	
	
}
