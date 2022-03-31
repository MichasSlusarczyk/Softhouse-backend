package pl.polsl.softhouse.dto.issue;

import pl.polsl.softhouse.entities.Request;
import pl.polsl.softhouse.entities.Task;
import pl.polsl.softhouse.entities.UserEntity;
import pl.polsl.softhouse.entities.enums.IssueType;
import pl.polsl.softhouse.entities.enums.WorkPriority;
import pl.polsl.softhouse.entities.enums.WorkStatus;

import java.time.LocalDateTime;
import java.util.List;

public class IssueDto {
    private Long id;
    private String result;
    private WorkStatus status;
    private String description;
    private IssueType type;
    private UserEntity productManager;
    private Request request;
    private List<Task> tasks;
    private LocalDateTime dateOpened;
    private LocalDateTime dateInProgress;
    private LocalDateTime dateClosed;
    private LocalDateTime deadline;
    private WorkPriority priority;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public IssueType getType() {
        return type;
    }

    public void setType(IssueType type) {
        this.type = type;
    }

    public UserEntity getProductManager() {
        return productManager;
    }

    public void setProductManager(UserEntity productManager) {
        this.productManager = productManager;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
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
}
