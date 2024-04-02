package com.zybooks.sdsuccc.repo

import android.content.Context
import com.zybooks.sdsuccc.model.Person
import com.zybooks.sdsuccc.model.Cclass
import java.util.*

class CenterRepository private constructor(context: Context) {

    private val cclassList = mutableListOf<Cclass>()
    private val personMap = mutableMapOf<Long, MutableList<Person>>()

    companion object {
        private var instance: CenterRepository? = null

        fun getInstance(context: Context): CenterRepository {
            if (instance == null) {
                instance = CenterRepository(context)
            }
            return instance!!
        }
    }

    init {
        addStarterData()
    }

    fun addCclass(cclass: Cclass) {
        cclassList.add(cclass)
        personMap[cclass.id] = mutableListOf()
    }

    fun getCclass(): List<Cclass> {
        return Collections.unmodifiableList(cclassList)
    }

    fun addPerson(person: Person) {
        personMap[person.cclassId]?.add(person)
    }

    fun getPersons(cclassId: Long): List<Person> {
        return Collections.unmodifiableList(personMap[cclassId]!!)
    }

    private fun addStarterData() {

        addCclass(Cclass(1, "Chicks"))
        addPerson(Person(1, "Mark Pateros", "Sep. 06, 2023", "No allergies", 1))
        addPerson(Person(2, "Steven Sosin",
            "Jul. 14, 2023","No allergies", 1))

        addCclass(Cclass(2, "Koalas"))
        addPerson(Person(3,
            "Hajin Lee",
            "Apr. 21, 2022", "Egg allergy", 2))
        addPerson(Person(4, "Hermione", "Apr.06, 2022"," No allergies",  2))

        addCclass(Cclass(3, "Pandas"))
    }
}