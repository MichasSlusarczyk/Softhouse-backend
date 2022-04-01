package pl.polsl.softhouse.services;

import org.springframework.stereotype.Service;
import pl.polsl.softhouse.entities.Request;
import pl.polsl.softhouse.exceptions.InvalidDataException;
import pl.polsl.softhouse.exceptions.request.RequestNotFoundException;
import pl.polsl.softhouse.repositories.RequestRepository;

import java.util.List;

@Service
public class RequestService {

    private final RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    public Request getRequestById(Long id) {
        if (id == null) {
            throw new InvalidDataException("No id provided.");
        }

        return requestRepository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException(id));
    }
}
