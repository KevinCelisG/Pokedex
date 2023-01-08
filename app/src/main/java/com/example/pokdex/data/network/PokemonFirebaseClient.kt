package com.example.pokdex.data.network

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import com.example.pokdex.core.Constants
import com.example.pokdex.core.Utils
import com.example.pokdex.data.model.PokemonResponse
import com.google.firebase.firestore.DocumentReference
import retrofit2.Response
import java.util.*
import javax.inject.Inject

class PokemonFirebaseClient @Inject constructor(private val documentReference: DocumentReference) {

    /**
     * Post the data of response in Firestore with Firebase
     * @param response = Response<PokemonResponse> with de data response of Retrofit
     */
    @SuppressLint("NewApi")
    fun postResponse(response: Response<PokemonResponse>) {
        documentReference.collection(Constants.DATA_RESPONSES_KEY).add(
            hashMapOf(
                "code" to response.code().toString(),
                "body" to response.body().toString(),
                "date" to Calendar.getInstance().time.toString(),
                "isSuccessful" to response.isSuccessful,
                "message" to response.message(),
                "errorBody" to response.errorBody(),
                "method" to "GET"
            )
        )
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    /**
     * Get the data of all responses about a document with collection in Firestore of Firebase and call to Utils to save the data
     */
    fun saveResponses() {
        var responses = ""
        documentReference.collection(Constants.DATA_RESPONSES_KEY).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    responses += "Id: ${document.id} => Data: ${document.data} \n"
                }
                Utils.save(responses, documentReference.id)
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }
    }
}