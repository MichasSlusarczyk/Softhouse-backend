package pl.polsl.softhouse.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import pl.polsl.softhouse.entities.enums.IssueType;
import pl.polsl.softhouse.entities.enums.WorkPriority;
import pl.polsl.softhouse.entities.enums.WorkResult;
import pl.polsl.softhouse.entities.enums.WorkStatus;

@MappedSuperclass
public abstract class AbstractWorkUnit {
    
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    protected WorkStatus status = WorkStatus.OPEN;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = true)
    protected WorkResult result;

    @Column(nullable = false)
    protected LocalDate dateOpened;

    @Column(nullable = true)
    protected LocalDate dateInProgress;

    @Column(nullable = true)
    protected LocalDate dateFinished;

    @Column(nullable = true)
    protected LocalDate deadline;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    protected WorkPriority priority = WorkPriority.NORMAL;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    protected IssueType type;

    public WorkStatus getStatus() {
        return status;
    }

    public void setStatus(WorkStatus status) {
        this.status = status;
    }

    public WorkResult getResult() {
        return result;
    }

    public void setResult(WorkResult result) {
        this.result = result;
    }

    public LocalDate getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(LocalDate dateOpened) {
        this.dateOpened = dateOpened;
    }

    public LocalDate getDateInProgress() {
        return dateInProgress;
    }

    public void setDateInProgress(LocalDate dateInProgress) {
        this.dateInProgress = dateInProgress;
    }

    public LocalDate getDateFinished() {
        return dateFinished;
    }

    public void setDateFinished(LocalDate dateFinished) {
        this.dateFinished = dateFinished;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public WorkPriority getPriority() {
        return priority;
    }

    public void setPriority(WorkPriority priority) {
        this.priority = priority;
    }

    public IssueType getType() {
        return type;
    }

    public void setType(IssueType type) {
        this.type = type;
    }
}
