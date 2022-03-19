package pl.polsl.softhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.polsl.softhouse.entities.Client;
import pl.polsl.softhouse.services.ClientService;

@RestController
@RequestMapping(path="api/client")
public class ClientController {
    
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @PostMapping
    public void addClient(Client client) {
        clientService.addClient(client);
    }

    @DeleteMapping(path="{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }

    @PutMapping(path="{id}")
    public void updateClient(@PathVariable Long id, @RequestParam String name) {
        clientService.updateClient(id, name);
    }
}
