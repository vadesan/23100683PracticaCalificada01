package com.vadesan.practica01dsm.presentation.data.remote.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.vadesan.practica01dsm.presentation.data.model.OperationModel
import kotlinx.coroutines.tasks.await
import java.time.LocalDateTime

object FirebaseAuthManager {
    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    public var currentUser = ""

    //Register User
    suspend fun registerUser(name: String, email: String, password: String): Result<Unit> {
        return try {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            val uid = authResult.user?.uid.toString()
            val user = hashMapOf(
                "uid" to uid,
                "email" to email,
                "createdAt" to LocalDateTime.now()
            )
            firestore.collection("usersPC01").document(uid).set(user).await()
            Result.success(Unit)
        } catch(e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun loginUser(email: String, password: String): Result<Unit> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            //currentUser = auth.currentUser.uid
            Result.success(Unit)
        } catch(e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun recoverRates(): Result<Unit> {
        return try {
            val currencies = firestore.collection("rates").get().await()
            Result.success(Unit)
        }
        catch(e: Exception) {
            Result.failure(e)
        }

    }

    suspend fun registerOperation(originCurrency: String, destinyCurrency: String, amount: Double, result: Double): Result<Unit> {
        return try {
            val newOperationRef = firestore.collection("historyPC01").document()
            val newOperation = OperationModel(
                originCurrency,
                destinyCurrency,
                amount,
                result,
                LocalDateTime.now()
            )
            newOperationRef.set(newOperation).await()

            Result.success(Unit)
        } catch(e: Exception) {
            Result.failure(e)
        }
    }

}