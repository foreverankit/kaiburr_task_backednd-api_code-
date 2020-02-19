package rc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DbSeeder implements CommandLineRunner {

    private ServerRepository serverRepository;

    public DbSeeder(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Server server= new Server(
                "my centos1",
                "python",
                "django"
        );

        Server server1 = new Server(
                "my centos",
                "Java",
                "Spring"

        );

        // deleteAll
        this.serverRepository.deleteAll();

        // add our servers to db
        List<Server> servers = Arrays.asList(server,server1);
        this.serverRepository.saveAll(servers);


    }
}
