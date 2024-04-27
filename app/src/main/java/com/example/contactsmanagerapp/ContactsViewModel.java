package com.example.contactsmanagerapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ContactsViewModel extends AndroidViewModel {
    private ContactsRepository contactsRepository;

    private LiveData<List<ContactsModel>> allContacts;

    public ContactsViewModel(@NonNull Application application) {
        super(application);
        this.contactsRepository = new ContactsRepository(application);
    }

    public LiveData<List<ContactsModel>> getAllContacts(){
        allContacts = contactsRepository.getAllContacts();
        return allContacts;
    }

    public void addContact(ContactsModel contact){
        contactsRepository.addContact(contact);
    }

    public void deleteContact(ContactsModel contact){
        contactsRepository.deleteContact(contact);
    }
}
