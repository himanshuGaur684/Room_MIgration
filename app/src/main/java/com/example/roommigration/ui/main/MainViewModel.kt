package com.example.roommigration.ui.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roommigration.database.model.User
import com.example.roommigration.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val mainRepository: MainRepository) : ViewModel() {

    private val _list: MutableState<List<User>> = mutableStateOf(emptyList())
    val list: State<List<User>> get() = _list


    init {
        viewModelScope.launch {
            mainRepository.getAllUser().collectLatest {
                _list.value = it
            }
        }
    }

    fun insertUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        mainRepository.insertData(user)
    }

}