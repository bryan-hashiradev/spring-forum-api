package br.com.hashiradev.forum.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime
@Entity(name = "answers")
data class AnswerModel(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var message: String,
    @ManyToOne()
    @JoinColumn(name = "user_id")
    val author: UserModel,
    @ManyToOne
    val topic: TopicModel,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val isSolution: Boolean = false,
)
