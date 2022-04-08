package com.example.cm.viewModel

import androidx.lifecycle.*
import com.example.cm.model.Person
import com.example.cm.repository.personRepository
import kotlinx.coroutines.launch

class personViewModel(private val repository: personRepository): ViewModel() {


    val allPeople: LiveData<List<Person>> = repository.allPeople.asLiveData()

    fun insert(person: Person) = viewModelScope.launch {
        repository.insert(person)
    }

}

class personViewModelFactory(private val repository: personRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(personViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return personViewModel(repository) as T
        }
        throw IllegalAccessException("Unknown view model class")
    }

}