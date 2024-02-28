import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val roomNum: String,
    val teachers: String,
    val description: String
)