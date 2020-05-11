package db;

import model.EmotionalDiary;
import model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmotionalDiaryRepository extends CrudRepository<EmotionalDiary, Integer> {
        //PagingAndSortingRepository<EmotionalDiary, Integer> {

    List<EmotionalDiary> findEmotionalDiariesByUser(User user);

}
