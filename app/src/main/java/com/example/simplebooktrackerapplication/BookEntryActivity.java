package com.example.simplebooktrackerapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BookEntryActivity extends AppCompatActivity {

    private EditText titleEditText;
    private EditText authorEditText;
    private EditText isbnEditText;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_entry);

        titleEditText = findViewById(R.id.titleEditText);
        authorEditText = findViewById(R.id.authorEditText);
        isbnEditText = findViewById(R.id.isbnEditText);
        Button submitButton = findViewById(R.id.submitButton);

        databaseHelper = new DatabaseHelper(this);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String author = authorEditText.getText().toString();
                String isbn = isbnEditText.getText().toString();

                Book book = new Book();
                book.setTitle(title);
                book.setAuthor(author);
                book.setIsbn(isbn);

                databaseHelper.addBook(book);


                titleEditText.setText("");
                authorEditText.setText("");
                isbnEditText.setText("");

                Toast.makeText(BookEntryActivity.this, "Book added successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseHelper.close();
    }
}
