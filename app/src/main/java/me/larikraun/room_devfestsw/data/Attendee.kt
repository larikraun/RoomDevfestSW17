package me.larikraun.room_devfestsw.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Author: Omolara Adejuwon
 * Date: 11/8/17.
 */
@Entity
data class Attendee(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: String?,
                    var name: String?,
                    var email: String?,
                    var category: String?)
