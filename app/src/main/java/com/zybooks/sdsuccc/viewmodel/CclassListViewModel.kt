package com.zybooks.sdsuccc.viewmodel

import android.app.Application
import com.zybooks.sdsuccc.model.Cclass
import com.zybooks.sdsuccc.repo.CenterRepository

class SubjectListViewModel(application: Application) {

    private val centerRepo = CenterRepository.getInstance(application.applicationContext)

    fun getCclass(): List<Cclass> = centerRepo.getCclass()

    fun addCclass(cclass: Cclass) = centerRepo.addCclass(cclass)
}