package me.larikraun.room_devfestsw.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Author: Omolara Adejuwon
 * Date: 11/8/17.
 */
@Database(entities = {Attendee.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase {
	public abstract AttendeeDao attendeeDao ();
}