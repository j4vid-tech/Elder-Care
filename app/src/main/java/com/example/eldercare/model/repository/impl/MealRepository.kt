package com.example.eldercare.model.repository.impl

import com.example.eldercare.model.Meal
import com.example.eldercare.model.service.AuthService
import com.example.eldercare.model.service.CaretakerAccountService
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.dataObjects
import kotlinx.coroutines.flow.Flow
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

const val MEAL_COLLECTION_NAME = "meal"


class MealRepository @Inject constructor(
    firestore: FirebaseFirestore, auth: AuthService
) : FirestoreRepository<Meal>(collectionName = MEAL_COLLECTION_NAME, firestore, auth) {

    override val clazz: Class<Meal>
        get() = Meal::class.java


    override fun getAll(): Flow<List<Meal>> {
        return collection.dataObjects()
    }

    fun getAllInThePast(): Flow<List<Meal>> {
        val currentTime = Timestamp.now()

        return collection
            .whereLessThan(Meal::date.name, currentTime)
            .orderBy(Meal::date.name, Query.Direction.DESCENDING)
            .dataObjects()
    }

    fun getAllByPatientId(patientId: String): Flow<List<Meal>> {
        return collection.whereEqualTo(Meal::patientUuid.name, patientId).dataObjects()
    }

    fun getAllMealPastByPatientId(patientId: String): Flow<List<Meal>> {
        val currentDate = Date()
        return collection
            .whereEqualTo(Meal::patientUuid.name, patientId)
            .whereLessThanOrEqualTo(Meal::date.name, currentDate)
            .orderBy(
                Meal::date.name,
                Query.Direction.DESCENDING
            )
            .dataObjects()
    }

    fun getAllByPatientIdToday(patientId: String): Flow<List<Meal>> {
        // Get the start of the day in milliseconds
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0) // set hour to midnight
            set(Calendar.MINUTE, 0) // set minute in hour
            set(Calendar.SECOND, 0) // set second in minute
            set(Calendar.MILLISECOND, 0) // set millisecond in second
        }
        val startOfDay = calendar.time

        // Get the end of the day in milliseconds
        calendar.apply {
            add(Calendar.DAY_OF_YEAR, 1) // move to the next day
            add(Calendar.MILLISECOND, -1) // subtract one millisecond to get the end of today
        }
        val endOfDay = calendar.time

        // Query Firestore for meals for the patient that fall within today's date range
        return collection
            .whereEqualTo(Meal::patientUuid.name, patientId)
            .whereGreaterThanOrEqualTo(Meal::date.name, startOfDay)
            .whereLessThanOrEqualTo(Meal::date.name, endOfDay)
            .orderBy(
                Meal::date.name,
                Query.Direction.ASCENDING
            ) // ensure the query is ordered if using range filters
            .dataObjects()
    }

    fun getPreviousMeals(meal: Meal, numOfPreviousMeals: Int): Flow<List<Meal>> {
        return collection
            .whereLessThan(Meal::date.name, meal.date!!)
            .orderBy(Meal::date.name, Query.Direction.DESCENDING)
            .limit(numOfPreviousMeals.toLong())
            .dataObjects()
    }
}
