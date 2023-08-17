package com.example.hw5_retrofit.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity(tableName = "favBook")
data class FavBook(

    @PrimaryKey
    @ColumnInfo(name = "bookId")
    var bookId: Int?,

    @ColumnInfo(name = "name")
    var name: String?,

    @ColumnInfo(name = "publisher")
    var publisher: String?,

    @ColumnInfo(name = "price")
    var price: Double?,

    @ColumnInfo(name = "bookImage")
    var bookImage: String?
)

@Dao
interface FavBookDao {

    @Insert
    fun insert(favBook: FavBook)

    @Delete
    fun delete(favBook: FavBook)

    @Query("SELECT*FROM favBook")
    fun getAllFavBooks(): List<FavBook>?

    @Query("SELECT name FROM favBook")
    fun getNamesFavBooks(): List<String>?

}
