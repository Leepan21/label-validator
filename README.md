# Sound Event Label Validator (MLPC)

Small Java tool I wrote to check label files from our sound event detection project
at JKU (MLPC course). It reads a CSV with labeled sound events and finds mistakes
before the data is used for training.

## Checks
- start time is negative or end time is before start time
- label is not one of our sound classes
- same label overlaps itself in the same recording

It also counts how many events each label has.

## Input
```
file,start,end,label
rec1.wav,1.0,3.0,door_open
rec1.wav,2.0,4.0,door_open
rec1.wav,5.0,4.0,footsteps
```

## Output
```
Total events: 6
Invalid times: 1
Unknown labels: 1
Overlapping pairs: 1
Events per label:
  dog_bark: 1
  door_open: 2
  footsteps: 1
  microwave: 1
  running_water: 1
```

## Run
Needs Java 17 and Maven.

```
mvn test
mvn compile
java -cp target/classes Main labels.csv
```

## Notes
I used this project to practice test-driven development. Each feature started with
a JUnit test first, then the code to make it pass.