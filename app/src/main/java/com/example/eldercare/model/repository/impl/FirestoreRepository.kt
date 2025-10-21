package com.example.eldercare.model.repository.impl

import com.example.eldercare.model.Meal
import com.example.eldercare.model.repository.Repository
import com.example.eldercare.model.service.AuthService
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.dataObjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await

/**
 * An abstract base class for Firestore repositories that provides common CRUD (Create, Read, Update, Delete) operations
 * for interacting with Firestore collections. Subclasses should implement the [clazz] property to specify the data type
 * they work with.
 *
 * @param T The type of data entity managed by the repository.
 * @property collectionName The name of the Firestore collection associated with this repository.
 * @property firestore An instance of FirebaseFirestore for database access.
 * @property auth An instance of the AccountService for authentication-related functionality.
 */
abstract class FirestoreRepository<T : Any> constructor(
    private val collectionName: String,
    private val firestore: FirebaseFirestore,
    private val auth: AuthService
) : Repository<T> {
    protected val collection = firestore.collection(collectionName)

    protected abstract val clazz: Class<T>

    override suspend fun getById(id: String): T? =
        collection.document(id).get().await().toObject(clazz)

    abstract override fun getAll(): Flow<List<T>>
    // needs to be explicitly defined in the concrete class due to type inference issues

    override suspend fun add(data: T) {
        collection.add(data).await()
    }

    override suspend fun update(id: String, data: T) {
        collection.document(id).set(data).await()
    }

    override suspend fun delete(id: String) {
        collection.document(id).delete().await()
    }
}
