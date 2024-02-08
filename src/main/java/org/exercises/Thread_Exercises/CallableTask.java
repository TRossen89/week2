package org.exercises.Thread_Exercises;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

public class CallableTask implements Callable {

    Double number;

    public CallableTask(Double number) {
        this.number = number;
    }

    @Override
    public Double call() throws Exception {

        List<String> listOfMovies = Arrays.asList("tt0111161", "tt0068646",
                "tt0468569", "tt0071562", "tt0167260",
                "tt0110912", "tt0050083", "tt0060196",
                "tt0109830", "tt0137523", "tt1375666",
                "tt0268978", "tt0119217", "tt0780504",
                "tt0449467");



        Double numberToReturn = 1.23;

        for (String movie : listOfMovies) {

            for (int counting = movie.length(); counting > 0; counting = counting-1) {

                    for (int count = 0; count < 1; count++) {

                        numberToReturn += number + count + (12 + 2132 + (12323 / 2323) + (3 * 11));

                        for(String s : listOfMovies){

                            numberToReturn *= s.length()-8;
                        }
                    }

                }
            }
        return numberToReturn;
    }
}
