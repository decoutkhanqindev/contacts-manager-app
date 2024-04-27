package com.example.contactsmanagerapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactsmanagerapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // data source
    private ContactsDatabase contactsDatabase;
    private ArrayList<ContactsModel> contactsModelArrayList = new ArrayList<>();

    // adapter
    private ContactsAdapter contactsAdapter;

    // Binding
    private ActivityMainBinding mainBinding;
    private MainActivityClickHandlers clickHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // data binding
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        clickHandler = new MainActivityClickHandlers(this);
        mainBinding.setClickHandler(clickHandler);

        // recyclerView
        RecyclerView  recyclerView = mainBinding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // database
        contactsDatabase = ContactsDatabase.getInstance(this);

        // viewModel
        ContactsViewModel contactsViewModel = new ViewModelProvider(this).get(ContactsViewModel.class);
//        ContactsModel c1 = new ContactsModel("khang", "khang@gmail.com");
//        contactsViewModel.addContact(c1);

        // load data from Room db
        contactsViewModel.getAllContacts().observe(this, new Observer<List<ContactsModel>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(List<ContactsModel> contactsModelList) {
                contactsModelArrayList.clear();
                contactsModelArrayList.addAll(contactsModelList);
                contactsAdapter.notifyDataSetChanged();
            }
        });

        // adapter
        contactsAdapter = new ContactsAdapter(contactsModelArrayList);

        recyclerView.setAdapter(contactsAdapter);

        // vuot sang trai de xoa
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                ContactsModel contact = contactsModelArrayList.get(viewHolder.getAdapterPosition());

                contactsViewModel.deleteContact(contact);
            }
        }).attachToRecyclerView(recyclerView);
    }
}