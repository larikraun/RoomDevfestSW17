package me.larikraun.room_devfestsw.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.framework.FrameworkSQLiteOpenHelperFactory;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.testing.MigrationTestHelper;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static me.larikraun.room_devfestsw.data.AppDatabase.MIGRATION_1_2;
import static org.junit.Assert.assertEquals;

/**
 * Author: Omolara Adejuwon
 * Date: 11/14/17.
 */
@RunWith(AndroidJUnit4.class)
public class MigrationTest {
	private static Attendee ATTENDEE = new Attendee (1, "Anifowoshe Kareem", "kareem@ani.com", "Participant");
	
	
	private static final String DB_NAME = "test_attendee_db_name";
	@Rule
	public MigrationTestHelper mMigrationTestHelper =
			new MigrationTestHelper (InstrumentationRegistry.getInstrumentation (),
					AppDatabase.class.getCanonicalName (),
					new FrameworkSQLiteOpenHelperFactory ());
	
	@Before
	public void setUp () throws Exception {
	ATTENDEE.setPhoneNumber ("01234567891");
	}
	
	@Test
	public void migrationFrom1To2_containsCorrectData () throws IOException {
		SupportSQLiteDatabase db = mMigrationTestHelper.createDatabase (DB_NAME, 1);
		
		/*ContentValues values = new ContentValues ();
		values.put ("id", ATTENDEE.getId ());
		values.put ("name", ATTENDEE.getName ());
		values.put ("email", ATTENDEE.getEmail ());
		values.put ("category", ATTENDEE.getCategory ());
		
		db.insert ("attendee", SQLiteDatabase.CONFLICT_REPLACE, values);*/
		db.execSQL ("INSERT INTO attendee VALUES(" + ATTENDEE.getId () + ",'" + ATTENDEE.getName ()
				+ "','" + ATTENDEE.getEmail () + "','" + ATTENDEE.getCategory () + "')");
		
		
		db.close ();
		
		mMigrationTestHelper.runMigrationsAndValidate (DB_NAME, 2, true,
				MIGRATION_1_2);
		
		Attendee attendee = getMigratedRoomDatabase ().attendeeDao ().getAttendedByEmail (ATTENDEE.getEmail ());
		assertEquals (ATTENDEE.getId (), attendee.getId ());
		
		assertEquals (ATTENDEE.getName (), attendee.getName ());
		assertEquals (attendee.getPhoneNumber (), null);
	}
	
	
	@Test
	public void startInVersion2_containsCorrectData () throws IOException {
		SupportSQLiteDatabase db = mMigrationTestHelper.createDatabase (DB_NAME, 2);
		ContentValues values = new ContentValues ();
		values.put ("id", ATTENDEE.getId ());
		values.put ("name", ATTENDEE.getName ());
		values.put ("email", ATTENDEE.getEmail ());
		values.put ("category", ATTENDEE.getCategory ());
		values.put ("phone_number",ATTENDEE.getPhoneNumber ());
		
		db.insert ("attendee", SQLiteDatabase.CONFLICT_REPLACE, values);
		db.close ();
		
		// verify that the data is correct
		Attendee attendee = getMigratedRoomDatabase ().attendeeDao ().getAttendedByEmail (ATTENDEE.getEmail ());
		assertEquals (ATTENDEE.getId (), attendee.getId ());
		assertEquals (ATTENDEE.getPhoneNumber (), attendee.getPhoneNumber ());
	}
	
	private AppDatabase getMigratedRoomDatabase () {
		AppDatabase database = Room.databaseBuilder (InstrumentationRegistry.getTargetContext (),
				AppDatabase.class, DB_NAME)
				.addMigrations (MIGRATION_1_2)
				.build ();
		
		mMigrationTestHelper.closeWhenFinished (database);
		return database;
	}
	
	@After
	public void tearDown () throws Exception {
		
	}
}