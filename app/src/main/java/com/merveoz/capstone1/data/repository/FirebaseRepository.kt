package com.merveoz.capstone1.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.merveoz.capstone1.common.Resource
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class FirebaseRepository ( private val firebaseAuth: FirebaseAuth) {

    fun isUserLoggedIn() = firebaseAuth.currentUser != null

    fun getUserId() = firebaseAuth.currentUser?.uid.orEmpty()

    suspend fun signIn(email: String, password: String): Resource<Unit> {

        return try {

            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()

            if (result.user != null) {
                Resource.Success(Unit)
            } else {
                Resource.Error("Something went wrong!")
            }
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

    suspend fun signUp(email: String, password: String): Resource<Unit> {

        return try {

            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()

            if (result.user != null) {
                Resource.Success(Unit)
            } else {
                Resource.Error("Something went wrong!")
            }
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

    fun logOut() = firebaseAuth.signOut()
}