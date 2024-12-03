package com.example.library;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private BookAdapter bookAdapter;

    private static List<Book> books = new ArrayList<>();
    private EditText bookTitle;
    private EditText bookAuthor;
    private EditText bookPages;

    private Button addBookButton;
    private ListView listView;

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
        init();


        addBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = bookTitle.getText().toString();
                String author = bookAuthor.getText().toString();
                String pages = bookPages.getText().toString();
                if(title.isEmpty() || author.isEmpty() || pages.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                    return;

                }
                int pageCount = Integer.parseInt(pages);
                if (pageCount < 50) {
                    Toast.makeText(MainActivity.this, "Please enter a 50 or more pages", Toast.LENGTH_SHORT).show();
                    return;
                }
                Book book = new Book(title, author, pageCount);
                books.add(book);
                bookTitle.setText("");
                bookAuthor.setText("");
                bookPages.setText("");
                bookAdapter.notifyDataSetChanged();
            }
        });

    }

    public void init()
    {
        bookTitle = findViewById(R.id.bookTitleInput);
        bookAuthor = findViewById(R.id.bookAuthorInput);
        bookPages = findViewById(R.id.bookPagesInput);
        addBookButton = findViewById(R.id.addBook);
        listView = findViewById(R.id.bookList);
        books = new ArrayList<>();
        bookAdapter = new BookAdapter(books, this);
        listView.setAdapter(bookAdapter);
    }


    public static Book getBookByTitle(String title)
    {
        for(Book book : books)
        {
            if(book.getTitle().equals(title))
            {
                return book;
            }
        }
        return null;
    }
}