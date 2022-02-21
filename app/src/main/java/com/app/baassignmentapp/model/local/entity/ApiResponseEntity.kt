package com.app.baassignmentapp.model.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "api_response_table")
data class ApiResponseEntity(
    @PrimaryKey
    @NotNull
    var apiKey: String,
    var apiValue: String?,
    var updatedAt: Long?
)