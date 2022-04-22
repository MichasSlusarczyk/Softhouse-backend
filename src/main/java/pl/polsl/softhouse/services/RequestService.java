package pl.polsl.softhouse.services;

import org.springframework.stereotype.Service;
import pl.polsl.softhouse.components.GenericValidator;
import pl.polsl.softhouse.dto.request.RequestGetDto;
import pl.polsl.softhouse.dto.request.RequestPostDto;
import pl.polsl.softhouse.dto.request.RequestPutDto;
import pl.polsl.softhouse.dto.request.RequestMapper;
import pl.polsl.softhouse.entities.Request;
import pl.polsl.softhouse.entities.enums.UserRole;
import pl.polsl.softhouse.exceptions.InvalidDataException;
import pl.polsl.softhouse.exceptions.request.RequestNotFoundException;
import pl.polsl.softhouse.exceptions.user.UserException;
import pl.polsl.softhouse.exceptions.user.UserNotFoundException;
import pl.polsl.softhouse.repositories.RequestRepository;
import pl.polsl.softhouse.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestService {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final RequestMapper requestMapper;
    private final GenericValidator<Request> validator;

    public RequestService(RequestRepository requestRepository, UserRepository userRepository, RequestMapper requestMapper, GenericValidator<Request> validator) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
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

        checkUserOrThrow(userId);

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

    public long addRequest(RequestPostDto requestPostDto) {
        if (requestPostDto == null) {
            throw new InvalidDataException(("No data sent."));
        }

        if(requestPostDto.getAccountManagerId() == null) {
            throw new InvalidDataException("No account manager id provided.");
        }

        checkUserOrThrow(requestPostDto.getAccountManagerId());

        Request request = requestMapper.addRequest(requestPostDto);
        validator.validateOrThrow(request);

        return requestRepository.save(request).getId();
    }


    public void updateRequest(Long id, RequestPutDto requestPutDto) {
        if (id == null || requestPutDto == null) {
            throw new InvalidDataException(("No id provided."));
        }

        if(requestPutDto.getAccountManagerId() != null) {
            checkUserOrThrow(requestPutDto.getAccountManagerId());
        }

        Request request = requestRepository
                .findById(id)
                .map((foundRequest) -> requestMapper
                        .updateRequest(requestPutDto, foundRequest))
                .orElseThrow(() -> new RequestNotFoundException(id));

        requestRepository.save(request);
    }

    public void checkUserOrThrow(Long userId){
        if(userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId))
                .getRole() != UserRole.ACCOUNT_MANAGER)
            throw new UserException("User with id " + userId + " does not have authority to manage requests.");
    }
}
