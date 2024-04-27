package com.example.contactsmanagerapp;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.room.Insert;

public class AddContactActivityClickHandlers {
    ContactsModel contact;
    Context context;

    ContactsViewModel contactsViewModel;

    public AddContactActivityClickHandlers(ContactsModel contact, Context context, ContactsViewModel contactsViewModel) {
        this.contact = contact;
        this.context = context;
        this.contactsViewModel = contactsViewModel;
    }

     public void onAddContactToRoomDBBtnClicked(View view){
        if (contact.getName() == null || contact.getEmail() == null){
            Toast.makeText(context, "Text field cannot be empty.", Toast.LENGTH_SHORT).show();
        } else{
            Intent intent = new Intent(context, MainActivity.class);
//            intent.putExtra("name", contact.getName());
//            intent.putExtra("email", contact.getEmail());
            ContactsModel c = new ContactsModel(contact.getName(), contact.getEmail());
            contactsViewModel.addContact(c);
            context.startActivity(intent);
        }
     }
}
