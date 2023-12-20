package site.ph0en1x.taskmanagementsystem.utils;

import java.util.List;
import java.util.stream.Collectors;

public interface Mapper <T, D> {
    D toDto(T entity);

    T toEntity(D dto);

    default List<D> toDto(List<T> list) {
        if ( list == null ) {
            return null;
        }

        return list.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

}
