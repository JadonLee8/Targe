import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RoomDao {
    @Query("SELECT * FROM roomentity")
    fun getAll(): List<RoomEntity>

    @Insert
    fun insertAll(vararg rooms: RoomEntity)
}