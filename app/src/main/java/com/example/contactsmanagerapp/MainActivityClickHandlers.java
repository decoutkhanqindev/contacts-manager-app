package com.example.contactsmanagerapp;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class MainActivityClickHandlers {
    Context context;

    public MainActivityClickHandlers(Context context) {
        this.context = context;
    }

    public void onAddContactBtn(View view){
        Intent intent = new Intent(view.getContext(), AddContactActivity.class);
        context.startActivity(intent);
    }
}
