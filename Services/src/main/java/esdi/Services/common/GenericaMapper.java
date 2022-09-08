package esdi.Services.common;

import java.util.List;

public interface GenericaMapper<D, E> {

    D toDTO(E entity);

    E toEntity(D dto);

    List<D> toDTO(List<E> entity);

    List<E> toEntity(List<D> dto);

}