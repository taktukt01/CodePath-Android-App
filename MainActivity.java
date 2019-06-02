package com.example.taktuk.lab1codepath;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.R.color;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards; // variable to hold all our flashcard objects.
    allFlashcards = flashcardDatabase.getAllCards();  //we want to add this line so that our local variable holding the list of flashcards is updated:
    int currentCardDisplayedIndex = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();

        if (allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(0).getAnswer());
        }



//        String question = getIntent().getStringExtra("question");
//        String answer = getIntent().getStringExtra("answer");
//        final TextView v = (TextView) findViewById(R.id.flashcard_question);
//        v.setText(question);
//        v.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final TextView w = (TextView) findViewById(R.id.flashcard_answer);
//                w.setText(answer);
//            }
//        });
        findViewById(R.id.next_button).setOnClickListener(new View.OnClickListener() {  // NEXT button
            @Override
            public void onClick(View v) {
                // advance our pointer index so we can show the next card
                currentCardDisplayedIndex++;

                // make sure we don't get an IndexOutOfBoundsError if we are viewing the last indexed card in our list
                if (currentCardDisplayedIndex > allFlashcards.size() - 1) {
                    currentCardDisplayedIndex = 0;
                }

                // set the question and answer TextViews with data from the database
                ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
            }
        });






        findViewById(R.id.flashcard_answer).setVisibility(View.GONE);
        findViewById(R.id.flashcard_answer2).setVisibility(View.GONE);
        findViewById(R.id.flashcard_answer3).setVisibility(View.GONE);
        findViewById(R.id.closeButton).setVisibility(View.GONE);
        findViewById(R.id.flashcard_answer).setVisibility(View.GONE);

        findViewById(R.id.flashcard_question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer).setVisibility(View.VISIBLE);
                //POPUP "That is correct!"
                findViewById(R.id.flashcard_answer).setVisibility(View.VISIBLE);
                findViewById(R.id.flashcard_answer2).setVisibility(View.VISIBLE);
                findViewById(R.id.flashcard_answer3).setVisibility(View.VISIBLE);
            }
        });


        findViewById(R.id.flashcard_answer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //POPUP "That is correct!"
                findViewById(R.id.flashcard_answer2).setBackgroundColor(getResources().getColor(R.color.my_red_color, null));

            }
        });
        //sorry try again
        findViewById(R.id.flashcard_answer2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer).setBackgroundColor(getResources().getColor(R.color.my_green_color, null));


            }
        });
        //sorry try again
        findViewById(R.id.flashcard_answer3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer3).setBackgroundColor(getResources().getColor(R.color.my_red_color, null));
            }
        });


    }
    /* ** Make sure onActivityResult() is placed outside of onCreate()! Since onActivityResult() is it's own method, it needs to be placed outside of any other methods. In Java, you cannot put methods inside other methods, so in general
     it's a good habit to remember that whenever we're adding new methods, we want to place them outside of onCreate().
      */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == RESULT_OK && requestCode == 20) {
            String question = intent.getExtras().getString("question"); // 'string1' needs to match the key we used when we put the string in the Intent
            String  answer = intent.getExtras().getString("answer");
            String  answer2 = intent.getExtras().getString("answer2");
            String  answer3 = intent.getExtras().getString("answer3");

            flashcardDatabase.insertCard(new Flashcard(question, answer));  // Using Room Library


            TextView w = (TextView) findViewById(R.id.flashcard_question);
            w.setText(question);
            TextView y = (TextView) findViewById(R.id.flashcard_answer);
            y.setText(answer);
            TextView a = (TextView) findViewById(R.id.flashcard_answer2);
            a.setText(answer2);
            TextView b = (TextView) findViewById(R.id.flashcard_answer3);
            b.setText(answer3);
        }

    }

    public void onClick(View view) {
        Intent intent = new Intent(this, AddCardActivity.class);
        this.startActivityForResult(intent, 20);
    }
}