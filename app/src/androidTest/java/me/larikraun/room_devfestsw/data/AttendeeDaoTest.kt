package me.larikraun.room_devfestsw.data

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class AttendeeDaoTest {
    private val ATTENDEE = Attendee(1, "Yemi Alade", "yemi@email.com", "Participant")
    lateinit var database: AppDatabase
    @Before
    fun initDb() {
        database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), AppDatabase::class.java).build()
    }

    @Test
    fun insert_retrieveAll_containsCorrectSize() {
        database.attendeeDao().insert(ATTENDEE)

        val attendees = database.attendeeDao().getAllAttendees()

        assertEquals(1, attendees.size)
        assertEquals(ATTENDEE.name, attendees.get(0).name)
    }

    @After
    fun closeDb() {
        database.close()
    }
}
