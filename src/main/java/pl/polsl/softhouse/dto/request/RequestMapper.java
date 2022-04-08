package pl.polsl.softhouse.dto.request;

import org.mapstruct.*;
import pl.polsl.softhouse.entities.Request;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RequestMapper {

    @Mapping(target = "id")
    @Mapping(source = "accountManager.id", target = "accountManagerId")
    RequestGetDto getRequest(Request Request);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "accountManagerId", target = "accountManager.id")
    Request updateRequest(RequestPutDto requestPutDto, @MappingTarget Request request);

    @Mapping(target = "id", expression = "java(null)")
    @Mapping(source = "accountManagerId", target = "accountManager.id")
    Request addRequest(RequestPostDto requestPostDto);
}
