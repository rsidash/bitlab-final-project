package kz.bitlab.bitlabfinalproject.service.mapper;

import org.mapstruct.MapperConfig;
import org.mapstruct.MappingTarget;

import java.util.List;

@MapperConfig(componentModel = "spring")
public interface BaseMapper<D, E> {
    D toDto(E entity);
    E toEntity(D dto);
    List<D> toDtoList(List<E> entities);
//    void updateFromDto(D dto, @MappingTarget E entity);
}
