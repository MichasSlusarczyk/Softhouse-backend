package pl.polsl.softhouse.dto.request;

import pl.polsl.softhouse.entities.enums.WorkPriority;

import java.time.LocalDateTime;

public class RequestPostDto {
    private Long accountManagerId;
    private String description;
    private LocalDateTime deadline;
    private WorkPriority priority;

    public Long getAccountManagerId() {
        return accountManagerId;
    }

    public void setAccountManagerId(Long accountManagerId) {
        this.accountManagerId = accountManagerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
