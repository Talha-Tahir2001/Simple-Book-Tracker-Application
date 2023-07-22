package com.example.simplebooktrackerapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {
    private final Context context;
    private final List<Book> bookList;

    public BookAdapter(Context context, List<Book> bookList) {
        super(context, 0, bookList);
        this.context = context;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_book, parent, false);
        }

        Book book = bookList.get(position);

        TextView titleTextView = convertView.findViewById(R.id.titleTextView);
        TextView authorTextView = convertView.findViewById(R.id.authorTextView);
        TextView isbnTextView = convertView.findViewById(R.id.isbnTextView);

        titleTextView.setText(book.getTitle());
        authorTextView.setText(book.getAuthor());
        isbnTextView.setText(book.getIsbn());

        return convertView;
    }
}