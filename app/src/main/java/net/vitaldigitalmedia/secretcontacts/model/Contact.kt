package net.vitaldigitalmedia.secretcontacts.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.regex.Matcher
import java.util.regex.Pattern

@Parcelize
@Entity
data class Contact(
        @PrimaryKey(autoGenerate = true)
        val id: Int? = null,
        var name: String = "",
        @ColumnInfo(name = "phone_number")
        var phoneNumber: String = ""
) : Parcelable {

    companion object {
        // validates mobile numbers (not restricted to US numbers).
        fun isValidNumber(number: String): Boolean {
            val pattern = "^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$"
            val m: Matcher
            val r: Pattern = Pattern.compile(pattern)
            m = r.matcher(number.trim())
            return m.find()
        }
    }

}