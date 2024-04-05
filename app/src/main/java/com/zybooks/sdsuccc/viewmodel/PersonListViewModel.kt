package com.zybooks.sdsuccc.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.zybooks.sdsuccc.model.Person
import com.zybooks.sdsuccc.repo.CenterRepository

class PersonListViewModel(application: Application) : AndroidViewModel(application) {

    private val centerRepo = CenterRepository.getInstance(application)

    private val cclassIdLiveData = MutableLiveData<Long>()

    val personListLiveData: LiveData<List<Person>> =
        cclassIdLiveData.switchMap { cclassId ->
            centerRepo.getPersons(cclassId)
        }

    fun loadPersons(cclassId: Long) {
        cclassIdLiveData.value = cclassId
    }

    fun addPerson(person: Person) = centerRepo.addPerson(person)

    fun deletePerson(person: Person) = centerRepo.deletePerson(person)
}