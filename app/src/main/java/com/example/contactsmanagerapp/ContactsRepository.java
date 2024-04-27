package com.example.contactsmanagerapp;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContactsRepository {
    private final ContactsDao contactsDao;

    ExecutorService executor;
    Handler handler;

    public ContactsRepository(Application application) {
        ContactsDatabase contactsDatabase = ContactsDatabase.getInstance(application);
        this.contactsDao = contactsDatabase.getContactsDao();
        executor = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());
    }

    public void addContact(ContactsModel contact){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactsDao.insert(contact);
            }
        });
    }

    public void deleteContact(ContactsModel contact){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactsDao.delete(contact);
            }
        });
    }

    public LiveData<List<ContactsModel>> getAllContacts(){
        return contactsDao.getAllContacts();
    }
}
