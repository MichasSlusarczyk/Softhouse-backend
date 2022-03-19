package pl.polsl.softhouse.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.polsl.softhouse.entities.Client;
import pl.polsl.softhouse.repositories.ClientRepository;

@Service
public class ClientService {
    
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).get();
    }

    public Client addClient(Client client) {
        client.setId(0L);

        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        Optional<Client> foundClient = clientRepository.findById(id);
        if (foundClient.isEmpty()) {
            throw new IllegalArgumentException("Client does not exist."); // TODO
        }
        
        clientRepository.delete(foundClient.get());
    }

    public Client updateClient(Long id, String name) {
        Optional<Client> foundClient = clientRepository.findById(id);
        if (foundClient.isEmpty()) {
            throw new IllegalArgumentException("Client does not exist."); // TODO
        }
        Client client = foundClient.get();

        if (name != null && !name.isBlank() && !name.equals(client.getName())) {
            client.setName(name);
        }

        return clientRepository.save(client);
    }
}
