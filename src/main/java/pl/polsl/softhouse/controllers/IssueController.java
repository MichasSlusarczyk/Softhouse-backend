package pl.polsl.softhouse.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.softhouse.dto.issue.IssueClosedDto;
import pl.polsl.softhouse.dto.issue.IssueDto;
import pl.polsl.softhouse.entities.Issue;
import pl.polsl.softhouse.services.IssueService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/issues")
public class IssueController {

    private final IssueService issueService;

    IssueController(IssueService issueService) { this.issueService = issueService; }

    @GetMapping
    public ResponseEntity<List<Issue>> getAllIssues() {
        return ResponseEntity.ok().body(issueService.getAllIssues());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Issue> getIssueById(Long id) {
        return ResponseEntity.ok().body(issueService.getIssueById(id));
    }

    @PostMapping
    public ResponseEntity<Void> addIssue(@RequestBody Issue issue) {
        issueService.addIssue(issue);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Void> updateIssue(@PathVariable Long id, @RequestBody IssueDto issueDto) {
        issueService.updateIssue(id, issueDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "closed/{id}")
    public ResponseEntity<Void> updateClosedIssue(@PathVariable Long id, @RequestBody IssueClosedDto issueClosedDto) {
        issueService.updateClosedIssue(id, issueClosedDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "productManagersIssues/{userId}")
    public ResponseEntity<List<Issue>> getIssuesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok().body(issueService.getIssuesByUserId(userId));
    }
}
