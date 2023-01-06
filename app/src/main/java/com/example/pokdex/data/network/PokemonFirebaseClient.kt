package com.example.pokdex.data.network

import android.content.ContentValues.TAG
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.pokdex.data.model.PokemonResponse
import com.google.firebase.firestore.DocumentReference
import retrofit2.Response
import java.time.Instant
import javax.inject.Inject

class PokemonFirebaseClient @Inject constructor(private val documentReference: DocumentReference) {

    @RequiresApi(Build.VERSION_CODES.O)
    fun postResponse(response: Response<PokemonResponse>) {
        documentReference.collection("data").add(
            hashMapOf(
                "code" to response.code().toString(),
                "body" to response.body().toString(),
                "date" to Instant.now(),
                "isSuccessful" to response.isSuccessful,
                "message" to response.message(),
                "errorBody" to response.errorBody(),
                "request" to "GET"
            )
        )
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

}