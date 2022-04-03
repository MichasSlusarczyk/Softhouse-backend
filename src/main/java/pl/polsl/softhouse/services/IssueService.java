package pl.polsl.softhouse.services;

import org.springframework.stereotype.Service;
import pl.polsl.softhouse.dto.issue.IssueGetDto;
import pl.polsl.softhouse.dto.issue.IssueMapper;
import pl.polsl.softhouse.dto.issue.IssuePostDto;
import pl.polsl.softhouse.entities.Issue;
import pl.polsl.softhouse.entities.enums.UserRole;
import pl.polsl.softhouse.exceptions.AuthorizationException;
import pl.polsl.softhouse.exceptions.InvalidDataException;
import pl.polsl.softhouse.exceptions.issue.IssueNotFoundException;
import pl.polsl.softhouse.exceptions.user.UserNotFoundException;
import pl.polsl.softhouse.repositories.IssueRepository;
import pl.polsl.softhouse.repositories.UserRepository;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class IssueService {
    private final IssueRepository issueRepository;
    private final IssueMapper issueMapper;
    private final UserRepository userRepository;
    private final Validator validator;

    public IssueService(IssueRepository issueRepository, IssueMapper issueMapper, UserRepository userRepository, Validator validator) {
        this.issueRepository = issueRepository;
        this.issueMapper = issueMapper;
        this.userRepository = userRepository;
        this.validator = validator;
    }

    public List<IssueGetDto> getAllIssues() {
        return issueRepository.findAll()
                .stream()
                .map(issueMapper::issueToGetDto)
                .collect(Collectors.toList());
    }

    public IssueGetDto getIssueById(Long id) {
        if (id == null) {
            throw new InvalidDataException("No id provided.");
        }

        return issueRepository
                .findById(id)
                .map(issueMapper::issueToGetDto)
                .orElseThrow(() -> new IssueNotFoundException(id));
    }

    public void updateIssue(Long id, IssuePostDto issueDto) {
        if (id == null || issueDto == null) {
            throw new InvalidDataException("No id or data provided.");
        }

        if (issueDto.getProductManagerId() != null) {
            checkUserOrThrow(issueDto.getProductManagerId());
        }

        Issue issue = issueRepository
                .findById(id)
                .map((foundIssue) -> issueMapper.updateIssue(issueDto, foundIssue))
                .orElseThrow(() -> new IssueNotFoundException(id));

        issueRepository.save(issue);
    }

    public void addIssue(IssuePostDto issuePostDto) {
        if (issuePostDto == null || issuePostDto.getProductManagerId() == null) {
            throw new InvalidDataException("No data or product manager id provided");
        }

        checkUserOrThrow(issuePostDto.getProductManagerId());
        Issue issue = issueMapper.createIssueFromPostDto(issuePostDto);
        validateOrThrow(issue);

        issueRepository.save(issue);
    }

    public List<IssueGetDto> getIssuesByUserId(Long userId) {
        if (userId == null) {
            throw new InvalidDataException("No user id provided.");
        }

        checkUserOrThrow(userId);

        return issueRepository.findAllIssuesByUserId(userId)
                .stream()
                .map(issueMapper::issueToGetDto)
                .collect(Collectors.toList());
    }

    private void validateOrThrow(Issue issue) {
        Set<ConstraintViolation<Issue>> violations = validator.validate(issue);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    private void checkUserOrThrow(long userId) {
        if (userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId))
                .getRole() != UserRole.PRODUCT_MANAGER) {
            throw new AuthorizationException("User with id " + userId + " does not have the authority to manage issues.");
        }
    }
}
