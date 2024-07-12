package com.example.jettrivia.repository

import android.util.Log
import com.example.jettrivia.data.DataOrException
import javax.inject.Inject
import com.example.jettrivia.network.QuestionAPI
import com.example.jettrivia.model.QuestionsItem


class QuestionRepository @Inject constructor(private val api: QuestionAPI) {
    private val dataOrException = DataOrException<ArrayList<QuestionsItem>, Boolean, Exception>()
    suspend fun getAllQuestions():DataOrException<ArrayList<QuestionsItem>, Boolean, Exception> {
        try {
            dataOrException.loading = true
            dataOrException.data = api.getAllQuestions()
            if (dataOrException.data.toString().isNotEmpty()) dataOrException.loading = false
        }
        catch (e: Exception) {
            dataOrException.e = e
            Log.d("Exception", "getAllQuestions: ${dataOrException.e!!.localizedMessage}")
        }
        return dataOrException
    }
}