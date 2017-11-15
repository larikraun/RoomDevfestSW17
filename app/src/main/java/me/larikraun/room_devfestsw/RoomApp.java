package me.larikraun.room_devfestsw;

import android.app.Application;
import android.arch.persistence.room.Room;

import me.larikraun.room_devfestsw.data.AppDatabase;

/**
 * Author: Omolara Adejuwon
 * Date: 11/8/17.
 */
public class RoomApp extends Application {
	public static AppDatabase database;
	
	@Override
	public void onCreate () {
		super.onCreate ();
		database = Room.databaseBuilder (this, AppDatabase.class, "devfestsw-java.db")
				.addMigrations (AppDatabase.MIGRATION_1_2)
				.build ();
	}
}