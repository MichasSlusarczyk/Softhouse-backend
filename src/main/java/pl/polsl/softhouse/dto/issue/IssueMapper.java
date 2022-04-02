package pl.polsl.softhouse.dto.issue;

import org.mapstruct.*;
import pl.polsl.softhouse.entities.Issue;
import pl.polsl.softhouse.entities.Request;
import pl.polsl.softhouse.entities.UserEntity;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IssueMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "productManagerId", target = "productManager")
    @Mapping(source = "requestId", target = "request")
    Issue updateIssue(IssueDto issueDto, @MappingTarget Issue issue);

    @Mapping(target = "id")
    UserEntity getUserFromId(Long id);

    @Mapping(target = "id")
    Request getRequestFromId(Long id);

    @Mapping(target = "id", constant = "0L")
    @Mapping(source = "productManagerId", target = "productManager")
    Issue createIssueFromIssuePostDto(IssuePostDto issuePostDto);
}
