package rc;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Server")
public class Server {

    private String name;

    @Id
    private String id;

    private String language;

    private String framework;

    public Server(String name, String language, String framework) {
        this.name = name;
        this.language = language;
        this.framework = framework;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getLanguage() {
        return language;
    }

    public String getFramework() {
        return framework;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setFramework(String framework) {
        this.framework = framework;
    }
}
