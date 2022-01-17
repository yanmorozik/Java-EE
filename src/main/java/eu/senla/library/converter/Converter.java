package eu.senla.library.converter;

import eu.senla.library.dto.AuthorDto;
import eu.senla.library.model.BaseEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class Converter<T extends BaseEntity, DTO> {

    private final ModelMapper modelMapper;

    private final Class<T> entityClass = getGenericEntityClass();
    private final Class<DTO> dtoClass = getGenericDtoClass();

    @SuppressWarnings("unchecked")
    private Class<T> getGenericEntityClass() {
        ParameterizedType parameterizedType = (ParameterizedType) getClass()
                .getGenericSuperclass();
        return (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }

    @SuppressWarnings("unchecked")
    private Class<DTO> getGenericDtoClass() {
        ParameterizedType parameterizedType = (ParameterizedType) getClass()
                .getGenericSuperclass();
        return (Class<DTO>) parameterizedType.getActualTypeArguments()[1];
    }

    public T convert(DTO dto) {
        return modelMapper.map(dto, entityClass);
    }

    public DTO convert(T entity) {
        return modelMapper.map(entity, dtoClass);
    }

    public List<DTO> convert(List<T> entities) {
        return entities.stream().map(entity->modelMapper.map(entity,dtoClass)).collect(Collectors.toList());
    }

}