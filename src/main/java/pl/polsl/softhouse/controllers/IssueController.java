package pl.polsl.softhouse.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.softhouse.dto.issue.IssueGetDto;
import pl.polsl.softhouse.dto.issue.IssuePostDto;
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
    public ResponseEntity<List<IssueGetDto>> getAllIssues() {
        return ResponseEntity.ok().body(issueService.getAllIssues());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<IssueGetDto> getIssueById(Long id) {
        return ResponseEntity.ok().body(issueService.getIssueById(id));
    }

    @PostMapping
    public ResponseEntity<Void> addIssue(@RequestBody IssuePostDto issuePostDto) {
        issueService.addIssue(issuePostDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Void> updateIssue(@PathVariable Long id, @RequestBody IssuePostDto issuePostDto) {
        issueService.updateIssue(id, issuePostDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "productManagersIssues/{userId}")
    public ResponseEntity<List<IssueGetDto>> getIssuesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok().body(issueService.getIssuesByUserId(userId));
    }
}
