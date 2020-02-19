package rc;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ServerController {

    private ServerRepository  serverRepository;

    public ServerController(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    @GetMapping("/servers")
    public List<Server> getAll(){
        List<Server> servers = this.serverRepository.findAll();
        return servers;
    }

    @GetMapping("/{id}")
    public Optional<Server> getById(@PathVariable("id") String id){
        Optional<Server> servers = this.serverRepository.findById(id);
        if(servers.isEmpty())
            throw new ResourceNotFoundException();

        return servers;
    }

    @PostMapping("/servers")
    public void update(@RequestBody Server server){
        this.serverRepository.save(server);
    }

    @PutMapping("/servers")
    public void insert(@RequestBody Server server){
        this.serverRepository.insert(server);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") String id){
        this.serverRepository.deleteById(id);
    }

    @GetMapping("/name/{name}")
    public List<Server> getByName(@PathVariable("name") String name){
        List<Server> servers = this.serverRepository.findByName(name);

        if(servers.isEmpty())
            throw new ResourceNotFoundException();

        return servers;
    }
}
