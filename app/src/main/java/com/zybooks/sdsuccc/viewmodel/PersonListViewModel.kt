package com.zybooks.sdsuccc.viewmodel

import android.app.Application
import com.zybooks.sdsuccc.model.Person
import com.zybooks.sdsuccc.repo.CenterRepository

class PersonListViewModel(application: Application) {

    private val centerRepo = CenterRepository.getInstance(application.applicationContext)

    fun getPerson(cclassId: Long): List<Person> = centerRepo.getPersons(cclassId)
}