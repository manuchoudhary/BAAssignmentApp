package com.app.baassignmentapp.model.local.dao

import android.database.sqlite.SQLiteConstraintException
import androidx.room.*
import com.app.baassignmentapp.model.local.entity.ApiResponseEntity

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(response: ApiResponseEntity)

    @Query("SELECT * FROM api_response_table WHERE apiKey = :apiKey")
    fun getValueByKey(apiKey: String) : ApiResponseEntity

    @Query("UPDATE api_response_table SET apiValue = :apiValue, updatedAt = :updateAt WHERE apiKey = :apiKey")
    fun update(apiKey: String, apiValue: String, updateAt: Long?)

    @Transaction
    fun upsert(apiResponseEntity: ApiResponseEntity) {
        try {
            insert(apiResponseEntity)
        } catch (exception: SQLiteConstraintException) {
            apiResponseEntity.apiValue?.let {
                update(apiResponseEntity.apiKey,
                    it, apiResponseEntity.updatedAt)
            }
        }
    }
}