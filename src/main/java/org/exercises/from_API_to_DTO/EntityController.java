package org.exercises.from_API_to_DTO;

import java.util.List;

public interface EntityController<T> {

    List<T> getByRating(Double rating, List<T> listOfEntities);

    List<T> getSortedByReleaseDate(List<T> listOfEntities);
}
