package com.example.contactsmanagerapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.contactsmanagerapp.databinding.ActivityAddContactBinding;

public class AddContactActivity extends AppCompatActivity {
    private ActivityAddContactBinding activityAddContactBinding;
    private AddContactActivityClickHandlers clickHandler;
    private ContactsModel contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        contact = new ContactsModel();

        activityAddContactBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_contact);

        ContactsViewModel contactsViewModel =  new ViewModelProvider(this).get(ContactsViewModel.class);

        clickHandler = new AddContactActivityClickHandlers(contact, this, contactsViewModel);

        activityAddContactBinding.setContact(contact);
        activityAddContactBinding.setClickHandler(clickHandler);
    }
}