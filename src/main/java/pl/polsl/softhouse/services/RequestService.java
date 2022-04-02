package pl.polsl.softhouse.services;

import org.springframework.stereotype.Service;
import pl.polsl.softhouse.dto.request.RequestDto;
import pl.polsl.softhouse.dto.request.RequestMapper;
import pl.polsl.softhouse.entities.Request;
import pl.polsl.softhouse.exceptions.InvalidDataException;
import pl.polsl.softhouse.exceptions.request.RequestNotFoundException;
import pl.polsl.softhouse.repositories.RequestRepository;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class RequestService {

    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;
    private final Validator validator;

    public RequestService(RequestRepository requestRepository, RequestMapper requestMapper, Validator validator) {
        this.requestRepository = requestRepository;
        this.requestMapper = requestMapper;
        this.validator = validator;
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

    public void deleteRequestById(Long id){
        if(id == null)
        {
            throw new InvalidDataException("No id provided.");
        }

        if(!requestRepository.existsById(id))
        {
            throw new RequestNotFoundException(id);
        }

        requestRepository.deleteById(id);
    }

    public void addRequest(RequestDto requestDto)
    {
        if (requestDto == null)
        {
            throw new InvalidDataException(("No data sent."));
        }

        Request request = requestMapper.createRequestFromDto(requestDto);
        validateOrThrow(request);

        requestRepository.save(request);
    }


    public void updateRequest(Long id, RequestDto requestDto){
        if (id == null || requestDto == null)
        {
            throw new InvalidDataException(("No id provided."));
        }

        Request request = requestRepository
                .findById(id)
                .map((foundRequest) -> requestMapper
                        .updateRequest(requestDto, foundRequest))
                .orElseThrow(() -> new RequestNotFoundException(id));

        requestRepository.save(request);
    }

    public List<Request> getAllRequestsByUserId(Long userId)
    {
        if (userId == null )
        {
            throw new InvalidDataException(("No user id provided."));
        }

        return requestRepository.findAllByUserId(userId).orElseThrow(() ->
                new RequestNotFoundException("No request founded correlated with user id " + userId));
    }

    private void validateOrThrow(Request request) {
        Set<ConstraintViolation<Request>> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
