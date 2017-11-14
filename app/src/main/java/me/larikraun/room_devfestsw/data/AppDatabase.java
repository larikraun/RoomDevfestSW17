package me.larikraun.room_devfestsw.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

/**
 * Author: Omolara Adejuwon
 * Date: 11/8/17.
 */
@Database(entities = {Attendee.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase {
	public abstract AttendeeDao attendeeDao ();
	
	public static final Migration MIGRATION_1_2 = new Migration (1, 2) {
		
		@Override
		public void migrate (@NonNull SupportSQLiteDatabase database) {
			database.execSQL ("ALTER TABLE attendee ADD COLUMN phone_number TEXT");
		}
	};
}