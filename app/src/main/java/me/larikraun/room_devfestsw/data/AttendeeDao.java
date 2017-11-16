package me.larikraun.room_devfestsw.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Author: Omolara Adejuwon
 * Date: 11/8/17.
 */
@Dao
public interface AttendeeDao {
	@Insert
	void insert (Attendee attendee);
	
	@Delete
	void delete (Attendee attendee);
	
	@Update
	void update (Attendee attendee);
	
	@Query("Select * from attendee where email=:email")
	Attendee getAttendeesByEmail (String email);
	
	@Query("Select * from attendee")
	 List<Attendee> getAllAttendees ();
	
}