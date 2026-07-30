package com.offlinetranslator.data
import androidx.room.*
import kotlinx.coroutines.flow.Flow
@Entity(tableName="history") data class TranslationRecord(@PrimaryKey(autoGenerate=true) val id:Long=0,val source:String,val result:String,val timestamp:Long=System.currentTimeMillis())
@Dao interface TranslationDao { @Query("SELECT * FROM history ORDER BY timestamp DESC") fun observe():Flow<List<TranslationRecord>>; @Insert suspend fun insert(record:TranslationRecord) }
@Database(entities=[TranslationRecord::class],version=1,exportSchema=false) abstract class TranslationDatabase:RoomDatabase(){abstract fun translationDao():TranslationDao}
