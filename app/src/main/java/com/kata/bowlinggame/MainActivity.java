package com.kata.bowlinggame;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.kata.bowlinggame.domain.PossiblePins;
import com.kata.bowlinggame.domain.ScoreBoard;
import com.kata.bowlinggame.model.BowlingGame;
import com.kata.bowlinggame.viewmodel.GameViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private GridView buttonHolder;

    private GameViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this, new GameViewModelFactory()).get(GameViewModel.class);

        gridView = findViewById(R.id.grid_view);
        buttonHolder = findViewById(R.id.button_holder);
        buttonHolder.setOnItemClickListener(new ButtonClickListener());


        viewModel.getPossiblePins().observe(this, new Observer<PossiblePins>() {
            @Override
            public void onChanged(@Nullable PossiblePins possiblePins) {
                if (possiblePins == null) {
                    return;
                }

                List<String> possiblePinsList = possiblePins.getPossiblePins();
                buttonHolder.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, possiblePinsList) {
                });
                buttonHolder.invalidateViews();
            }
        });

        viewModel.getGameScore().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer score) {
                Toast.makeText(MainActivity.this, "Score : " + score, Toast.LENGTH_LONG).show();
            }
        });

        viewModel.getScoreBoard().observe(this, new Observer<ScoreBoard>() {
            @Override
            public void onChanged(@Nullable ScoreBoard scoreBoard) {
                gridView.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, scoreBoard.board()) {
                });
                gridView.invalidateViews();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_game:
                viewModel.newGame();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class GameViewModelFactory implements ViewModelProvider.Factory {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(GameViewModel.class)) {
                return (T) new GameViewModel(new BowlingGame());
            }
            return null;
        }
    }

    private class ButtonClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            viewModel.roll(position);
        }
    }
}
