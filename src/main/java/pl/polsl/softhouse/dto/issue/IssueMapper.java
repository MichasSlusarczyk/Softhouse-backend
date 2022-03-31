package pl.polsl.softhouse.dto.issue;

import org.mapstruct.*;
import pl.polsl.softhouse.entities.Issue;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IssueMapper {
    @Mapping(target = "id", ignore = true)
    Issue updateClosedIssue(IssueClosedDto issueClosedDto, @MappingTarget Issue issue);

    @Mapping(target = "id", ignore = true)
    Issue updateIssue(IssueDto issueDto, @MappingTarget Issue issue);
}
