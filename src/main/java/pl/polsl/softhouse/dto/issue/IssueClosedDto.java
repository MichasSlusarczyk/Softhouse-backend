package pl.polsl.softhouse.dto.issue;

import pl.polsl.softhouse.entities.enums.WorkStatus;

import java.time.LocalDateTime;

public class IssueClosedDto {
    private Long id;
    private String result;
    private WorkStatus status;
    private LocalDateTime dateClosed;

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

    public LocalDateTime getDateClosed() {
        return dateClosed;
    }

    public void setDateClosed(LocalDateTime dateClosed) {
        this.dateClosed = dateClosed;
    }
}
