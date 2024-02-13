import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val roomNum: String,
    val description: String,
    val hasWindows: Boolean
)