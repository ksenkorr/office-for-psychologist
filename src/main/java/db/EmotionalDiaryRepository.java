package db;

import model.EmotionalDiary;
import model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmotionalDiaryRepository extends PagingAndSortingRepository<EmotionalDiary, Integer> {

    List<EmotionalDiary> findEmotionalDiariesByUser(User user);

}
