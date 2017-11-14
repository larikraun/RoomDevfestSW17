package me.larikraun.room_devfestsw.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Objects;

/**
 * Author: Omolara Adejuwon
 * Date: 11/8/17.
 */
@Entity
public class Attendee {
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "id")
	private int id;
	@ColumnInfo(name = "name")
	private String name;
	@ColumnInfo(name = "email")
	private String email;
	private String category;
	
	public Attendee (int id, String name, String email, String category) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.category = category;
	}
	
	@Override
	public String toString () {
		return name + " " + email;
	}
	
	public int getId () {
		return id;
	}
	
	public void setId (int id) {
		this.id = id;
	}
	
	public String getName () {
		return name;
	}
	
	public void setName (String name) {
		this.name = name;
	}
	
	public String getEmail () {
		return email;
	}
	
	public void setEmail (String email) {
		this.email = email;
	}
	
	public String getCategory () {
		return category;
	}
	
	public void setCategory (String category) {
		this.category = category;
	}
	
}