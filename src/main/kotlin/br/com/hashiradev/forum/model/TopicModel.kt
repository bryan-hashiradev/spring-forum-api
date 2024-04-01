package br.com.hashiradev.forum.model

import br.com.hashiradev.forum.model.ENUM.TopicStatus
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "topics")
data class TopicModel(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var title: String,
    var message: String,
    @ManyToOne
    val user: UserModel,
    @ManyToOne
    val course: CourseModel,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Enumerated(EnumType.STRING)
    val status: TopicStatus = TopicStatus.OPEN,
    @OneToMany(mappedBy = "topic")
    val answers: List<AnswerModel> = ArrayList(),
)
