package me.larikraun.room_devfestsw.data

import android.arch.persistence.room.*

/**
 * Author: Omolara Adejuwon
 * Date: 11/8/17.
 */
@Dao
interface AttendeeDao {
    @Insert
    fun insert(attendee: Attendee)

    @Delete
    fun delete(attendee: Attendee)

    @Update
    fun update(attendee: Attendee)

    @Query("Select * from attendeess where email=:email")
    fun getAttendedByEmail(email: String): Attendee

    @Query("Select * from attendee")
    fun getAllAttendees(): List<Attendee>

}