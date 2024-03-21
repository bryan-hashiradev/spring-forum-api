package br.com.hashiradev.forum.mapper

interface Mapper<T, U> {
    abstract fun map(t: T): U
}
