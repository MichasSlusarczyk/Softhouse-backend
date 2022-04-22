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
    @Mapping(source = "productManagerId", target = "productManager.id")
    @Mapping(source = "requestId", target = "request.id")
    Issue updateIssue(IssuePostDto issueDto, @MappingTarget Issue issue);

//    @Mapping(target = "id")
//    UserEntity getUserFromId(Long id);
//
//    @Mapping(target = "id")
//    Request getRequestFromId(Long id);

    @Mapping(target = "id", expression = "java(null)")
    @Mapping(source = "requestId", target = "request.id")
    @Mapping(source = "productManagerId", target = "productManager.id")
    Issue createIssueFromPostDto(IssuePostDto issuePostDto);

    @Mapping(source = "productManager.id", target = "productManagerId")
    @Mapping(source = "request.id", target = "requestId")
    IssueGetDto issueToGetDto(Issue issue);
}
