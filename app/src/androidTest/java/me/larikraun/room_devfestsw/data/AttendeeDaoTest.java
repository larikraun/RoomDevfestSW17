package me.larikraun.room_devfestsw.data;

import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Author: Omolara Adejuwon
 * Date: 11/14/17.
 */
@RunWith(AndroidJUnit4.class)
public class AttendeeDaoTest {
	
	private static Attendee ATTENDEE = new Attendee (1, "Anifowoshe Kareem", "kareem@ani.com", "Participant");
	private AppDatabase database;
	
	@Before
	public void initDb () {
		
		database = Room.inMemoryDatabaseBuilder (InstrumentationRegistry.getContext (),
				AppDatabase.class).build ();
	}
	
	@Test
	public void insert_retrieveAll_hasCorrectSize () {
		database.attendeeDao ().insert (ATTENDEE);
		List<Attendee> attendees = database.attendeeDao ().getAllAttendees ();
		assertEquals (1, attendees.size ());
		assertEquals (ATTENDEE.getName (), attendees.get (0).getName ());
	}
	
	@Test
	public void insert_retrieveByEmail_hasCorrectData () {
		database.attendeeDao ().insert (ATTENDEE);
		
		Attendee attendee = database.attendeeDao ().getAttendeesByEmail (ATTENDEE.getEmail ());
		
		assertEquals (ATTENDEE.getId (), attendee.getId ());
	}
	
	@After
	public void closeDb () {
		database.close ();
	}
}
