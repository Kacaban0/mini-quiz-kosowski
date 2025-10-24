package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    TextView Title, Question, Score;
    Button Start, btnA, btnB, btnC, Reset;

    ArrayList<Question> questions;
    ArrayList<Question> selectedQuestions;
    int currentIndex = 0;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Title = findViewById(R.id.Title);
        Question = findViewById(R.id.Question);
        Score = findViewById(R.id.Score);
        Start = findViewById(R.id.Start);
        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);
        Reset = findViewById(R.id.Reset);

        questions = new ArrayList<>();
        questions.add(new Question("Stolica Włoch to:", "Rzym", "Paryż", "Madryt", "Rzym"));
        questions.add(new Question("Największa planeta Układu Słonecznego:", "Ziemia", "Jowisz", "Mars", "Jowisz"));
        questions.add(new Question("Ile boków ma kwadrat?", "3", "4", "5", "4"));
        questions.add(new Question("W jakim kraju leży Paryż?", "Francja", "Hiszpania", "Włochy", "Francja"));
        questions.add(new Question("Kolor nieba to zwykle:", "Czerwony", "Niebieski", "Zielony", "Niebieski"));
        questions.add(new Question("Ile dni ma tydzień?", "5", "6", "7", "7"));
        questions.add(new Question("Co jest większe: kot czy słoń?", "Kot", "Słoń", "Takie same", "Słoń"));
        questions.add(new Question("Ile godzin ma doba?", "12", "24", "48", "24"));
        questions.add(new Question("Jakie zwierzę jest symbolem Polski?", "Orzeł", "Lew", "Wilk", "Orzeł"));
        questions.add(new Question("Który miesiąc ma 28 dni?", "Tylko luty", "Wszystkie", "Żaden", "Wszystkie"));

        Start.setOnClickListener(v -> startQuiz());
        Reset.setOnClickListener(v -> resetQuiz());

        View.OnClickListener answerClick = v -> {
            Button clicked = (Button) v;
            checkAnswer(clicked.getText().toString());
        };

        btnA.setOnClickListener(answerClick);
        btnB.setOnClickListener(answerClick);
        btnC.setOnClickListener(answerClick);
    }

    private void startQuiz() {
        score = 0;
        currentIndex = 0;
        Score.setText("Wynik: " + score);

        Collections.shuffle(questions);
        selectedQuestions = new ArrayList<>(questions.subList(0, 5));

        Start.setVisibility(View.GONE);
        Question.setVisibility(View.VISIBLE);
        btnA.setVisibility(View.VISIBLE);
        btnB.setVisibility(View.VISIBLE);
        btnC.setVisibility(View.VISIBLE);

        showNextQuestion();
    }

    private void showNextQuestion() {
        if (currentIndex >= selectedQuestions.size()) {
            Toast.makeText(this, "Koniec quizu! Twój wynik: " + score + " / 5", Toast.LENGTH_LONG).show();

            Question.setVisibility(View.GONE);
            btnA.setVisibility(View.GONE);
            btnB.setVisibility(View.GONE);
            btnC.setVisibility(View.GONE);

            Start.setVisibility(View.VISIBLE);
            return;
        }

        Question q = selectedQuestions.get(currentIndex);
        Question.setText(q.question);
        btnA.setText(q.answerA);
        btnB.setText(q.answerB);
        btnC.setText(q.answerC);
    }

    private void checkAnswer(String chosenAnswer) {
        Question q = selectedQuestions.get(currentIndex);
        if (chosenAnswer.equals(q.correctAnswer)) {
            score++;
            Score.setText("Wynik: " + score);
        }

        currentIndex++;
        showNextQuestion();
    }

    private void resetQuiz() {
        score = 0;
        currentIndex = 0;
        Score.setText("Wynik: 0");

        Collections.shuffle(questions);
        selectedQuestions = new ArrayList<>(questions.subList(0, 5));

        Question.setVisibility(View.GONE);
        btnA.setVisibility(View.GONE);
        btnB.setVisibility(View.GONE);
        btnC.setVisibility(View.GONE);

        Start.setVisibility(View.VISIBLE);

        Toast.makeText(this, "Quiz zresetowany! Wylosowano nowe pytania.", Toast.LENGTH_SHORT).show();
    }
}
