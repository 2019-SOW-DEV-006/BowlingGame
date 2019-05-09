# Bowling
This project is about calculating the total score of the Bowling game.


## Rules for Calculating Total Score
* Each game, or “line” of bowling, includes ten turns, or “frames” for the bowler.
* In each frame, the bowler gets up to two tries to knock down all the pins.
* If in two tries, he fails to knock them all down, his score for that frame is the total number of pins knocked down in his two tries.
* If in two tries he knocks them all down, this is called a “spare” and his score for the frame is ten plus the number of pins knocked down on his next throw (in his next turn).
* If on his first try in the frame he knocks down all the pins, this is called a “strike”. His turn is over, and his score for the frame is ten plus the simple total of the pins knocked down in his next two rolls.
* If he gets a spare or strike in the last (tenth) frame, the bowler gets to throw one or two more bonus balls, respectively.
* These bonus throws are taken as part of the same turn. If the bonus throws knock down all the pins, the process does not repeat: the bonus throws are only used to calculate the score of the final frame.
* The game score is the total of all frame scores.


## Exceptions for Calculating Score
* We will not check for valid rolls.
* We will not check for correct number of rolls and frames.
* We will not provide scores for intermediate frames.

## Key points to highlight about this kata:
* Followed Test Driven Development
* Used MVVM architectural pattern to separate UI and business logic
* Used Android's LiveData to notify changes to Activity
* Added Test cases for all business logic in Model and ViewModel
* Used Constraint Layout for designing UI

## Run
To run this project (make sure mobile is connected and usb debugging is enabled):

```
$ ./gradlew installDebug
```

## Output
![Screenshot](https://raw.githubusercontent.com/2019-SOW-DEV-006/BowlingGame/master/Screenshot_20190509-033124.png)