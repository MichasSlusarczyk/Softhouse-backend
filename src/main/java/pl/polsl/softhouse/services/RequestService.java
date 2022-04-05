package pl.polsl.softhouse.services;

import org.springframework.stereotype.Service;
import pl.polsl.softhouse.dto.request.RequestGetDto;
import pl.polsl.softhouse.dto.request.RequestPostDto;
import pl.polsl.softhouse.dto.request.RequestPutDto;
import pl.polsl.softhouse.dto.request.RequestMapper;
import pl.polsl.softhouse.entities.Request;
import pl.polsl.softhouse.exceptions.InvalidDataException;
import pl.polsl.softhouse.exceptions.request.RequestNotFoundException;
import pl.polsl.softhouse.repositories.RequestRepository;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.ArrayList;
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

    public List<RequestGetDto> getAllRequests() {

        ArrayList<RequestGetDto> resultList = new ArrayList<>();
        requestRepository.findAll().forEach((c) ->  resultList.add(requestMapper.getRequest(c)));
        return resultList;
    }

    public RequestGetDto getRequestById(Long id) {
        if (id == null) {
            throw new InvalidDataException("No id provided.");
        }

        Request request = requestRepository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException(id));

        return requestMapper.getRequest(request);
    }

    public List<RequestGetDto> getAllRequestsByUserId(Long userId) {
        if (userId == null) {
            throw new InvalidDataException(("No user id provided."));
        }

        ArrayList<RequestGetDto> resultList = new ArrayList<>();
        requestRepository.findAllByUserId(userId).forEach((c) ->  resultList.add(requestMapper.getRequest(c)));
        return resultList;
    }

    public void deleteRequestById(Long id) {
        if (id == null) {
            throw new InvalidDataException("No id provided.");
        }

        if (!requestRepository.existsById(id)) {
            throw new RequestNotFoundException(id);
        }

        requestRepository.deleteById(id);
    }

    public void addRequest(RequestPostDto requestPostDto) {
        if (requestPostDto == null) {
            throw new InvalidDataException(("No data sent."));
        }

        Request request = requestMapper.addRequest(requestPostDto);
        validateOrThrow(request);

        requestRepository.save(request);
    }


    public void updateRequest(Long id, RequestPutDto requestPutDto) {
        if (id == null || requestPutDto == null) {
            throw new InvalidDataException(("No id provided."));
        }

        Request request = requestRepository
                .findById(id)
                .map((foundRequest) -> requestMapper
                        .updateRequest(requestPutDto, foundRequest))
                .orElseThrow(() -> new RequestNotFoundException(id));

        requestRepository.save(request);
    }

    private void validateOrThrow(Request request) {
        Set<ConstraintViolation<Request>> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
