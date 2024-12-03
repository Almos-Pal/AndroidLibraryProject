package com.example.library;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class BookAdapter  extends BaseAdapter {

    private List<Book> books;
    private Context context;

    public BookAdapter(List<Book> books, Context context) {
        this.books = books;
        this.context = context;
    }


    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = LayoutInflater.from(context).inflate(R.layout.book_item, parent, false);
        TextView title = view.findViewById(R.id.bookTitle);
        TextView author = view.findViewById(R.id.bookAuthor);
        TextView pageCount = view.findViewById(R.id.bookPages);
        Button delete = view.findViewById(R.id.deleteBookButton);
        Book book = books.get(position);

        title.setText(book.getTitle());
        author.setText(book.getAuthor());
        pageCount.setText(String.valueOf(book.getPageCount()));
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           AlertDialog.Builder builder = new AlertDialog.Builder(context);
              builder.setTitle("Delete Book");
                builder.setMessage("Are you sure you want to delete this book?");
                builder.setPositiveButton("Yes", (dialog, which) -> {
                    books.remove(book);
                    notifyDataSetChanged();
                });
                builder.setNegativeButton("No", (dialog, which) -> {
                    dialog.dismiss();
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        view.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("book", book);
            context.startActivity(intent);
        });
        return view;
    }
}
