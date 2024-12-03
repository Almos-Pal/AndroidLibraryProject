package com.example.library;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class DetailsActivity extends AppCompatActivity {

    private TextView title;
    private TextView author;
    private TextView pageCount;
    private TextView bookDate;
    private Button backButton;

    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();



        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        backButton.setOnClickListener(v -> finish());

    }

    private String generateRandomDate() {
        int year = random.nextInt(2023 - 1900 + 1) + 1900;
        int month = random.nextInt(12) + 1;
        int day = random.nextInt(28) + 1;
        return year + "-" + String.format("%02d", month) + "-" + String.format("%02d", day); // itt megkértem a copilotot hogy segítsen
    }

    private void init() {
        title = findViewById(R.id.bookTitleSubPage);
        author = findViewById(R.id.bookAuthorSubPage);
        pageCount = findViewById(R.id.bookPagesSubPage);
        bookDate = findViewById(R.id.bookDate);
        backButton = findViewById(R.id.backButton);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("book")) {
            Book book = (Book) intent.getSerializableExtra("book");

            if (book != null) {
                title.setText(book.getTitle());
                author.setText(book.getAuthor());
                pageCount.setText(String.valueOf(book.getPageCount()));
                bookDate.setText(generateRandomDate());
            }
        }
    }
}
