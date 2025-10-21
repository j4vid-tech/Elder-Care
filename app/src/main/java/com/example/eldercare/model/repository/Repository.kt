package com.example.eldercare.model.repository

import kotlinx.coroutines.flow.Flow

/**
 * A repository for CRUD (Create, Read, Update, Delete) operations on data entities of type [T].
 *
 * @param T The type of data entity managed by the repository.
 */
interface Repository<T> {
    /**
     * Retrieves a data entity of type [T] by its unique identifier.
     *
     * @param id The unique identifier of the data entity of type [T].
     * @return The data entity of type [T] if found, or null if not found.
     */
    suspend fun getById(id: String): T?

    /**
     * Retrieves all data entities of the specified type [T].
     *
     * @return A flow of all data entities of type [T].
     */
    fun getAll(): Flow<List<T>>

    /**
     * Adds a new data entity of type [T] to the repository.
     *
     * @param data The data entity of type [T] to be added.
     */
    suspend fun add(data: T)

    /**
     * Updates an existing data entity of type [T] in the repository.
     *
     * @param id The unique identifier of the data entity to be updated.
     * @param data The updated data entity of type [T].
     */
    suspend fun update(id: String, data: T)

    /**
     * Deletes a data entity of type [T] by its unique identifier.
     *
     * @param id The unique identifier of the data entity of type [T] to be deleted.
     */
    suspend fun delete(id: String)
}
