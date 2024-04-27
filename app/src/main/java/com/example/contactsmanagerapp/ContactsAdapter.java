package com.example.contactsmanagerapp;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactsmanagerapp.databinding.ContactsListItemBinding;

import java.util.ArrayList;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactViewHolder> {
    private ArrayList<ContactsModel> contactsModelArrayList;

    public ContactsAdapter(ArrayList<ContactsModel> contactsModelArrayList) {
        this.contactsModelArrayList = contactsModelArrayList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContactsListItemBinding contactsListItemBinding =
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.contacts_list_item,
                        parent,
                        false);
        return new ContactViewHolder(contactsListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        ContactsModel contact = contactsModelArrayList.get(position);
        holder.contactsListItemBinding.setContact(contact);
    }

    @Override
    public int getItemCount() {
        if (contactsModelArrayList == null){
            return 0;
        }
        return contactsModelArrayList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setContactsModelArrayList(ArrayList<ContactsModel> contactsModelArrayList){
        this.contactsModelArrayList = contactsModelArrayList;
        notifyDataSetChanged();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder{
        private ContactsListItemBinding contactsListItemBinding;

        public ContactViewHolder(@NonNull ContactsListItemBinding contactsListItemBinding) {
            super(contactsListItemBinding.getRoot());
            this.contactsListItemBinding = contactsListItemBinding;
        }
    }
}
