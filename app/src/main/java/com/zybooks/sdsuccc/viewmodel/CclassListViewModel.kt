package com.zybooks.sdsuccc.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.zybooks.sdsuccc.model.Cclass
import com.zybooks.sdsuccc.repo.CenterRepository

class CclassListViewModel(application: Application) : AndroidViewModel(application) {

    private val centerRepo = CenterRepository.getInstance(application.applicationContext)

    val cclassListLiveData: LiveData<List<Cclass>> = centerRepo.getCclasses()

    fun addCclass(cclass: Cclass) = centerRepo.addCclass(cclass)

    fun deleteCclass(cclass: Cclass) = centerRepo.deleteCclass(cclass)
}