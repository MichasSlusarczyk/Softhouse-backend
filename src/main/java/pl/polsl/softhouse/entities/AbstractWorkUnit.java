package pl.polsl.softhouse.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import pl.polsl.softhouse.entities.enums.WorkPriority;
import pl.polsl.softhouse.entities.enums.WorkStatus;

@MappedSuperclass
public abstract class AbstractWorkUnit {
    
    public static final int RESULT_LENGTH = 512;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    protected WorkStatus status = WorkStatus.OPEN;

    @Column(nullable = false, length = RESULT_LENGTH)
    protected String result;

    @Column(nullable = false)
    protected LocalDateTime dateOpened = LocalDateTime.now();

    @Column(nullable = true)
    protected LocalDateTime dateInProgress;

    @Column(nullable = true)
    protected LocalDateTime dateClosed;

    @Column(nullable = true)
    protected LocalDateTime deadline;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    protected WorkPriority priority = WorkPriority.NORMAL;

    public WorkStatus getStatus() {
        return status;
    }

    public void setStatus(WorkStatus status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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
