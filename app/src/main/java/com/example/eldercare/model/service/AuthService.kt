package com.example.eldercare.model.service

import com.example.eldercare.model.AuthUser
import kotlinx.coroutines.flow.Flow

/**
 * Interface defining the contract for account-related operations and user authentication.
 * Implementations of this interface provide functionality to manage user accounts,
 * authentication, and user-related data.
 */
interface AuthService {
    val currentUserId: String
    val hasUser: Boolean

    val currentAuthUser: Flow<AuthUser>

    suspend fun authenticate(email: String, password: String)
    suspend fun sendRecoveryEmail(email: String)
    suspend fun createUserWithEmailAndPassword(email: String, password: String)
    suspend fun deleteAccount()
    suspend fun signOut()
}
