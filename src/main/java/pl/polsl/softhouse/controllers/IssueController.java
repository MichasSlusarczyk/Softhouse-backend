package pl.polsl.softhouse.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.softhouse.dto.issue.IssueGetDto;
import pl.polsl.softhouse.dto.issue.IssuePostDto;
import pl.polsl.softhouse.entities.Issue;
import pl.polsl.softhouse.entities.enums.WorkStatus;
import pl.polsl.softhouse.services.IssueService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/issues")
public class IssueController {

    private final IssueService issueService;

    IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping
    public ResponseEntity<List<IssueGetDto>> getIssues(@RequestParam(required = false) Long userId,
                                                       @RequestParam(required = false) WorkStatus status) {
        if(userId != null && status != null) {
            return ResponseEntity.ok().body(issueService.getIssuesByUserIdAndStatus(userId, status));
        }
        else {
            return ResponseEntity.ok().body(
                    userId != null ? issueService.getIssuesByUserId(userId) :
                            status != null ? issueService.getIssuesByStatus(status) : issueService.getAllIssues()
            );
        }
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<IssueGetDto> getIssueById(Long id) {
        return ResponseEntity.ok().body(issueService.getIssueById(id));
    }

    @PostMapping(path = "addIssue")
    public ResponseEntity<Void> addIssue(@RequestBody IssuePostDto issuePostDto) {
        issueService.addIssue(issuePostDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(path = "updateIssue")
    public ResponseEntity<Void> updateIssue(@RequestParam Long id, @RequestBody IssuePostDto issuePostDto) {
        issueService.updateIssue(id, issuePostDto);
        return ResponseEntity.ok().build();
    }
}
