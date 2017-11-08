package me.larikraun.room_devfestsw.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Author: Omolara Adejuwon
 * Date: 11/8/17.
 */
@Database(entities = arrayOf(Attendee::class), version = 1)

abstract class AppDatabase : RoomDatabase() {
    abstract fun attendeeDao(): AttendeeDao
}