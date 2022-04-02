package pl.polsl.softhouse.services;

import org.springframework.stereotype.Service;
import pl.polsl.softhouse.dto.issue.IssueDto;
import pl.polsl.softhouse.dto.issue.IssueMapper;
import pl.polsl.softhouse.dto.issue.IssuePostDto;
import pl.polsl.softhouse.entities.Issue;
import pl.polsl.softhouse.exceptions.InvalidDataException;
import pl.polsl.softhouse.exceptions.issue.IssueNotFoundException;
import pl.polsl.softhouse.repositories.IssueRepository;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class IssueService {
    private final IssueRepository issueRepository;
    private final IssueMapper issueMapper;
    private final Validator validator;

    public IssueService(IssueRepository issueRepository, IssueMapper issueMapper, Validator validator) {
        this.issueRepository = issueRepository;
        this.issueMapper = issueMapper;
        this.validator = validator;
    }

    public List<Issue> getAllIssues() {

        return issueRepository.findAll();
    }

    public Issue getIssueById(Long id) {
        if(id == null)
            throw new InvalidDataException("No id provided.");

        return issueRepository
                .findById(id)
                .orElseThrow(() -> new IssueNotFoundException(id));
    }

    public void updateIssue(Long id, IssueDto issueDto) {
        if(id == null || issueDto == null)
            throw new InvalidDataException("No id provided.");

        Issue issue = issueRepository
                .findById(id)
                .map((foundIssue) -> issueMapper.updateIssue(issueDto, foundIssue))
                .orElseThrow(() -> new IssueNotFoundException(id));
        issueRepository.save(issue);
    }

    public void addIssue(IssuePostDto issuePostDto) {
        if(issuePostDto == null)
            throw new InvalidDataException("No Issue object provided");
        Issue issue = issueMapper.createIssueFromIssuePostDto(issuePostDto);
        validate(issue);
        issueRepository.save(issue);
    }

    public List<Issue> getIssuesByUserId(Long userId) {
        if(userId == null)
            throw new InvalidDataException("No user id provided");

        return issueRepository.findAllIssuesByUserId(userId);
    }

    private void validate(Issue issue) {
        Set<ConstraintViolation<Issue>> violations = validator.validate(issue);
        if(!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }
}
