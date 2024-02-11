package org.exercises.Thread_Exercises;

public class KanyeDTO implements APIInformation {

    String quote;

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    @Override
    public void printAPIInfo() {
        System.out.println("Kanye quote: " + quote);
    }

    @Override
    public String getValue() {
        return quote;
    }

    @Override
    public String getAPI() {
        return "kanye";
    }
}
