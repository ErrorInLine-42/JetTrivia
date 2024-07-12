package com.example.jettrivia.screen

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jettrivia.data.DataOrException
import com.example.jettrivia.data.getQuestionIndex
import com.example.jettrivia.data.saveQuestionIndex
import com.example.jettrivia.model.QuestionsItem
import com.example.jettrivia.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    private val repository: QuestionRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    val data: MutableState<DataOrException<ArrayList<QuestionsItem>, Boolean, Exception>> =
        mutableStateOf(DataOrException(null, true, Exception("")))

    val questionIndex = mutableStateOf(0)

    init {
        getSavedQuestionIndex()
        getAllQuestions()
    }

    private fun getSavedQuestionIndex() {
        viewModelScope.launch {
            questionIndex.value = getQuestionIndex(context).first()
        }
    }

    private fun getAllQuestions() {
        viewModelScope.launch {
            data.value.loading = true
            data.value = repository.getAllQuestions()
            if (data.value.data.toString().isNotEmpty()) {
                data.value.loading = false
            }
        }
    }

    fun saveQuestionIndex(index: Int) {
        viewModelScope.launch {
            saveQuestionIndex(context, index)
        }
    }
}
