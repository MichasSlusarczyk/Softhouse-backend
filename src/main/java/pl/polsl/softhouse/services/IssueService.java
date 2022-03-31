package pl.polsl.softhouse.services;

import org.springframework.stereotype.Service;
import pl.polsl.softhouse.dto.issue.IssueClosedDto;
import pl.polsl.softhouse.dto.issue.IssueDto;
import pl.polsl.softhouse.dto.issue.IssueMapper;
import pl.polsl.softhouse.entities.Issue;
import pl.polsl.softhouse.exceptions.InvalidDataException;
import pl.polsl.softhouse.exceptions.issue.IssueNotFoundException;
import pl.polsl.softhouse.repositories.IssueRepository;

import java.util.List;

@Service
public class IssueService {
    private final IssueRepository issueRepository;
    private final IssueMapper issueMapper;

    public IssueService(IssueRepository issueRepository, IssueMapper issueMapper) {
        this.issueRepository = issueRepository;
        this.issueMapper = issueMapper;
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

    public void updateClosedIssue(Long id, IssueClosedDto issueClosedDto) {
        if(id == null || issueClosedDto == null)
            throw new InvalidDataException("No id provided.");

        Issue issue = issueRepository
                .findById(id)
                .map((foundIssue) -> issueMapper.updateClosedIssue(issueClosedDto, foundIssue))
                .orElseThrow(() -> new IssueNotFoundException(id));
        issueRepository.save(issue);
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

    public void addIssue(Issue issue) {
        if(issue == null)
            throw new InvalidDataException("No Issue object provided");

        issueRepository.save(issue);
    }

    public List<Issue> getIssuesByUserId(Long userId) {
        if(userId == null)
            throw new InvalidDataException("No user id provided");

        return issueRepository
                .findAllByUserId(userId)
                .orElseThrow(() -> new IssueNotFoundException("There are no issues connected to user with id " + userId));
    }
}
