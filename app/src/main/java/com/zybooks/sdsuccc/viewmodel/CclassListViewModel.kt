package com.zybooks.sdsuccc.viewmodel

import android.app.Application
import com.zybooks.sdsuccc.model.Cclass
import com.zybooks.sdsuccc.repo.CenterRepository

class CclassListViewModel(application: Application) {

    private val centerRepo = CenterRepository.getInstance(application.applicationContext)

    fun getCclasses(): List<Cclass> = centerRepo.getCclasses()

    fun addCclass(cclass: Cclass) = centerRepo.addCclass(cclass)
}
