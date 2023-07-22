package com.example.simplebooktrackerapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Book> bookList;
    private BookAdapter bookAdapter;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView bookListView = findViewById(R.id.bookListView);
        databaseHelper = new DatabaseHelper(this);

        bookList = new ArrayList<>();
        bookAdapter = new BookAdapter(this, bookList);
        bookListView.setAdapter(bookAdapter);

        loadBooks();


        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = bookList.get(position);
                int bookId = book.getId();
                databaseHelper.deleteBook(bookId);
                loadBooks();
                Toast.makeText(MainActivity.this, "Book deleted", Toast.LENGTH_SHORT).show();
            }
        });


        FloatingActionButton addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BookEntryActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadBooks() {
        bookList.clear();
        bookList.addAll(databaseHelper.getAllBooks());
        bookAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadBooks();
    }
}