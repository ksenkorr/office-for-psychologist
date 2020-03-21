package model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "emotional_diaries")
public class EmotionalDiary {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private Date dairyTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, length = 1000)
    private String comment;

}
