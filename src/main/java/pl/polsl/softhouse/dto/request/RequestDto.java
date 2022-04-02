package pl.polsl.softhouse.dto.request;

import pl.polsl.softhouse.entities.Issue;
import pl.polsl.softhouse.entities.Request;
import pl.polsl.softhouse.entities.Task;
import pl.polsl.softhouse.entities.UserEntity;
import pl.polsl.softhouse.entities.enums.IssueType;
import pl.polsl.softhouse.entities.enums.WorkPriority;
import pl.polsl.softhouse.entities.enums.WorkStatus;

import java.time.LocalDateTime;
import java.util.List;

public class RequestDto {
    private Long id;
    private UserEntity accountManager;
    private String description;
    private String result;
    private WorkStatus status;
    private LocalDateTime dateOpened;
    private LocalDateTime dateInProgress;
    private LocalDateTime dateClosed;
    private LocalDateTime deadline;
    private WorkPriority priority;
    private List<Issue> issues;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getAccountManager() {
        return accountManager;
    }

    public void setAccountManager(UserEntity accountManager) {
        this.accountManager = accountManager;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public WorkStatus getStatus() {
        return status;
    }

    public void setStatus(WorkStatus status) {
        this.status = status;
    }

    public LocalDateTime getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(LocalDateTime dateOpened) {
        this.dateOpened = dateOpened;
    }

    public LocalDateTime getDateInProgress() {
        return dateInProgress;
    }

    public void setDateInProgress(LocalDateTime dateInProgress) {
        this.dateInProgress = dateInProgress;
    }

    public LocalDateTime getDateClosed() {
        return dateClosed;
    }

    public void setDateClosed(LocalDateTime dateClosed) {
        this.dateClosed = dateClosed;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public WorkPriority getPriority() {
        return priority;
    }

    public void setPriority(WorkPriority priority) {
        this.priority = priority;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }
}
