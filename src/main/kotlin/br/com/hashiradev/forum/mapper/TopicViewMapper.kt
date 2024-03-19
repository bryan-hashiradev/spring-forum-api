package br.com.hashiradev.forum.mapper

import br.com.hashiradev.forum.DTO.TopicView
import br.com.hashiradev.forum.model.TopicModel
import org.springframework.stereotype.Component

@Component
class TopicViewMapper: Mapper<TopicModel, TopicView> {
    override fun map(topic: TopicModel): TopicView {
        return TopicView(
            topic.id,
            topic.title,
            topic.message,
            topic.status,
            topic.createAt
        )
    }
}