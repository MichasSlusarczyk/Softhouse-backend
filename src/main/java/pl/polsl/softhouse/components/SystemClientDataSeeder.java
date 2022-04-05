package pl.polsl.softhouse.components;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.polsl.softhouse.entities.Client;
import pl.polsl.softhouse.entities.SystemClient;
import pl.polsl.softhouse.entities.SystemEntity;
import pl.polsl.softhouse.repositories.ClientRepository;
import pl.polsl.softhouse.repositories.SystemClientRepository;
import pl.polsl.softhouse.repositories.SystemRepository;

@Component
public class SystemClientDataSeeder implements CommandLineRunner {

    private final SystemRepository systemRepository;
    private final ClientRepository clientRepository;
    private final SystemClientRepository systemClientRepository;

    public SystemClientDataSeeder(SystemRepository systemRepository,
                                  ClientRepository clientRepository,
                                  SystemClientRepository systemClientRepository) {
        this.systemRepository = systemRepository;
        this.clientRepository = clientRepository;
        this.systemClientRepository = systemClientRepository;
    }

    @Override
    public void run(String... args) {
        seedSystems();
        seedClients();
        seedSystemClients();
    }

    private void seedSystems() {
        SystemEntity system = new SystemEntity();
        system.setName("Seeded System");
        systemRepository.save(system);
    }

    private void seedClients() {
        Client client = new Client();
        client.setName("Seeded Client");
        clientRepository.save(client);
    }

    private void seedSystemClients() {
        SystemClient systemClient = new SystemClient();
        systemClient.setVersion("1.0.0");

        systemClient.setSystem(new SystemEntity());
        systemClient.getSystem().setId(1L);

        systemClient.setClient(new Client());
        systemClient.getClient().setId(1L);

        systemClientRepository.save(systemClient);
    }
}
