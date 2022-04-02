package pl.polsl.softhouse.dto.request;

import org.mapstruct.*;
import pl.polsl.softhouse.entities.Request;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RequestMapper {
    @Mapping(target = "id", ignore = true)
    Request updateRequest(RequestDto requestDto, @MappingTarget Request request);

    @Mapping(target = "id", constant = "0L")
    Request createRequestFromDto(RequestDto requestDto);
}
