package com.example.workout

import androidx.room.*
import kotlinx.coroutines.flow.Flow


    @Dao
    interface HistoryDao {
        @Insert
        suspend fun insert(historyEntity: HistoryEntity)

}