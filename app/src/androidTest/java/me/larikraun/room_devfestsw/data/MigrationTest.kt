package me.larikraun.room_devfestsw.data

import android.arch.persistence.db.framework.FrameworkSQLiteOpenHelperFactory
import android.arch.persistence.room.Room
import android.arch.persistence.room.testing.MigrationTestHelper
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Author: Omolara Adejuwon
 * Date: 11/16/17.
 */
@RunWith(AndroidJUnit4::class)
class MigrationTest {
    private val ATTENDEE = Attendee(1, "Anifowoshe Kareem", "kareem@ani.com", "Participant", null)


    private val DB_NAME = "test_attendee_db_name"

    @get:Rule
    public val mMigrationTestHelper = MigrationTestHelper(InstrumentationRegistry.getInstrumentation(),
            AppDatabase::class.java.canonicalName,
            FrameworkSQLiteOpenHelperFactory())

    @Before
    fun setUp() {
        ATTENDEE.phoneNumber = "01234567891"
    }

    @Test
    fun migrationFrom1To2_containsCorrectData() {
        val db = mMigrationTestHelper.createDatabase(DB_NAME, 1)

        db.execSQL("INSERT INTO attendee VALUES(" + ATTENDEE.id + ",'" + ATTENDEE.name
                + "','" + ATTENDEE.email + "','" + ATTENDEE.category + "')")


        db.close()

        mMigrationTestHelper.runMigrationsAndValidate(DB_NAME, 2, true,
                AppDatabase.MIGRATION_1_2)

        val attendee = getMigratedRoomDatabase().attendeeDao().getAttendeesByEmail(ATTENDEE.email)
        assertEquals(ATTENDEE.id, attendee.id)

        assertEquals(ATTENDEE.name, attendee.name)
        assertEquals(attendee.phoneNumber, null)
    }

    @Test
    fun startInVersion2_containsCorrectData() {
        val db = mMigrationTestHelper.createDatabase(DB_NAME, 2)
        val values = ContentValues()
        values.put("id", ATTENDEE.id)
        values.put("name", ATTENDEE.name)
        values.put("email", ATTENDEE.email)
        values.put("category", ATTENDEE.category)
        values.put("phone_number", ATTENDEE.phoneNumber)

        db.insert("attendee", SQLiteDatabase.CONFLICT_REPLACE, values)
        db.close()

        // verify that the data is correct
        val (id, _, _, _, phoneNumber) = getMigratedRoomDatabase().attendeeDao().getAttendeesByEmail(ATTENDEE.email)
        assertEquals(ATTENDEE.id, id)
        assertEquals(ATTENDEE.phoneNumber, phoneNumber)
    }

    private fun getMigratedRoomDatabase(): AppDatabase {
        val database = Room.databaseBuilder(InstrumentationRegistry.getTargetContext(),
                AppDatabase::class.java, DB_NAME)
                .addMigrations(AppDatabase.MIGRATION_1_2)
                .build()

        mMigrationTestHelper.closeWhenFinished(database)
        return database
    }

    @After
    fun tearDown() {

    }
}