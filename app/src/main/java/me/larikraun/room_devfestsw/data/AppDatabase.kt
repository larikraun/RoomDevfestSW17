package me.larikraun.room_devfestsw.data

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.migration.Migration


/**
 * Author: Omolara Adejuwon
 * Date: 11/8/17.
 */
@Database(entities = arrayOf(Attendee::class), version = 2)

abstract class AppDatabase : RoomDatabase() {
    abstract fun attendeeDao(): AttendeeDao

    companion object {

        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE attendee ADD COLUMN phone_number TEXT NOT NULL DEFAULT nothing")
            }
        }
    }
}