package br.com.hashiradev.forum.service

import br.com.hashiradev.forum.model.CourseModel
import org.springframework.stereotype.Service

@Service
class CourseService(
    private var courses: List<CourseModel>
) {
    init {
        courses = listOf(
            CourseModel(
                1,
                "kotlin",
                "programação"
            ),
            CourseModel(
                2,
                "Java",
                "programação"
            ),
            CourseModel(
                3,
                "JPA com Hibernate",
                "programação"
            )
        )
    }

    fun index() = courses
    fun getByID(id: Long): CourseModel = courses.first { it.id == id }
}