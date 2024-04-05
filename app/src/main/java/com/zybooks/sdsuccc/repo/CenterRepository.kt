package com.zybooks.sdsuccc.repo

import androidx.lifecycle.LiveData
import android.content.Context
import androidx.room.Room
import com.zybooks.sdsuccc.model.Person
import com.zybooks.sdsuccc.model.Cclass

class CenterRepository private constructor(context: Context) {
    companion object {
        private var instance: CenterRepository? = null

        fun getInstance(context: Context): CenterRepository {
            if (instance == null) {
                instance = CenterRepository(context)
            }
            return instance!!
        }
    }

    private val database : CenterDatabase = Room.databaseBuilder(
        context.applicationContext,
        CenterDatabase::class.java,
        "center.db"
    )
        .allowMainThreadQueries()
        .build()

    private val cclassDao = database.cclassDao()
    private val personDao = database.personDao()

    fun getCclass(cclassId: Long): LiveData<Cclass?> = cclassDao.getCclass(cclassId)

    fun getCclasses(): LiveData<List<Cclass>> = cclassDao.getCclasses()

    fun addCclass(cclass: Cclass) {
        cclass.id = cclassDao.addCclass(cclass)
    }

    fun deleteCclass(cclass: Cclass) = cclassDao.deleteCclass(cclass)

    fun getPerson(personId: Long): LiveData<Person?> = personDao.getPerson(personId)

    fun getPersons(cclassId: Long): LiveData<List<Person>> = personDao.getPersons(cclassId)

    fun addPerson(person: Person) {
        person.id = personDao.addPerson(person)
    }

    fun updatePerson(person: Person) = personDao.updatePerson(person)

    fun deletePerson(person: Person) = personDao.deletePerson(person)

    private fun addStarterData() {
        var cclassId = cclassDao.addCclass(Cclass(text = "Chicks"))
        personDao.addPerson(
            Person(
                name = "Mark Pateros",
                dob = "Sep. 06, 2023",
                allergy = "No allergies",
                cclassId = cclassId
            )
        )
        personDao.addPerson(
            Person(
                name = "Steven Sosin",
                dob = "Jul. 14, 2023",
                allergy = "No allergies",
                cclassId = cclassId
            )
        )

        cclassId = cclassDao.addCclass(Cclass(text = "Koalas"))
        personDao.addPerson(
            Person(
                name = "Hajin Lee",
                dob = "Apr. 21, 2022",
                allergy = "Egg allergy",
                cclassId = cclassId
            )
        )

        personDao.addPerson(
            Person(
                name = "Hermione Dancer",
                dob = "Apr.06, 2022",
                allergy = "No allergies",
                cclassId = cclassId
            )
        )
        cclassId = cclassDao.addCclass(Cclass(text = "Pandas"))
    }
}