package com.example.contactsmanagerapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactsDao {
    @Insert
    void insert(ContactsModel contact);

    @Delete
    void delete(ContactsModel contact);

    @Query("SELECT * FROM contacts_table")
    LiveData<List<ContactsModel>> getAllContacts();
}
