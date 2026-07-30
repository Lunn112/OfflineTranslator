package com.offlinetranslator.data
import androidx.room.*
@Fts4(contentEntity=TermEntity::class) abstract class TermSearch { @ColumnInfo(name="term") abstract val term:String; @ColumnInfo(name="translation") abstract val translation:String }
@Entity(tableName="terms") data class TermEntity(@PrimaryKey(autoGenerate=true) val id:Long=0,val domain:String,val term:String,val translation:String)
@Dao interface TermDao { @Insert fun insertAll(items:List<TermEntity>); @Query("SELECT terms.* FROM terms JOIN termsearch ON terms.rowid=termsearch.rowid WHERE termsearch MATCH :query AND domain=:domain LIMIT 20") fun search(query:String,domain:String):List<TermEntity> }
@Database(entities=[TermEntity::class,TermSearch::class],version=1,exportSchema=false) abstract class DomainFtsDatabase:RoomDatabase(){abstract fun terms():TermDao}
