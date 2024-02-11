package org.exercises.Thread_Exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MegaDTO{

    String dad_joke;
    String chuck_norris_joke;

    String kanye_quote;

    List<APIInformation> listOfAPIInformation;

    public MegaDTO(String dad_joke, String chuck_norris_joke, String kanye_quote) {
        this.dad_joke = dad_joke;
        this.chuck_norris_joke = chuck_norris_joke;
        this.kanye_quote = kanye_quote;
    }

    public MegaDTO(List<APIInformation> listOfAPIInformation) {
        this.listOfAPIInformation = listOfAPIInformation;
    }

    public MegaDTO(String dad_joke, String chuck_norris_joke, String kanye_quote, List<APIInformation> listOfAPIInformation) {
        this.dad_joke = dad_joke;
        this.chuck_norris_joke = chuck_norris_joke;
        this.kanye_quote = kanye_quote;
        this.listOfAPIInformation = listOfAPIInformation;
    }


    public MegaDTO() {
        listOfAPIInformation = new ArrayList<>();

    }

    public void addToList(APIInformation apiInformation){
        listOfAPIInformation.add(apiInformation);
    }
    public String getDad_joke() {
        return dad_joke;
    }

    public void setDad_joke(String dad_joke) {
        this.dad_joke = dad_joke;
    }

    public String getChuck_norris_joke() {
        return chuck_norris_joke;
    }

    public void setChuck_norris_joke(String chuck_norris_joke) {
        this.chuck_norris_joke = chuck_norris_joke;
    }

    public String getKanye_quote() {
        return kanye_quote;
    }

    public void setKanye_quote(String kanye_quote) {
        this.kanye_quote = kanye_quote;
    }

    public List<APIInformation> getListOfAPIInformation() {
        return listOfAPIInformation;
    }

    public void setListOfAPIInformation(List<APIInformation> listOfAPIInformation) {
        this.listOfAPIInformation = listOfAPIInformation;
    }


    void setValue(String api, String value){

        switch (api){
            case "dad_joke":
                this.dad_joke = value;
                break;
            case "chuck_norris":
                this.chuck_norris_joke = value;
                break;
            case "kanye":
                this.kanye_quote = value;

        }

    }
    @Override
    public String toString() {

        String dataFromListOfAPIInformation = listOfAPIInformation.stream().map(dto -> dto.getValue() + "\n")
                .reduce("", String::concat);

        return "--------\nDTO data from MegaDTO:\n\n- From Strings:\n" + "Dad joke: " + dad_joke + "\nChuck Norris joke: " + chuck_norris_joke
                + "\nKanye quote: " + kanye_quote + "\n\n- From List: \n" + dataFromListOfAPIInformation;
    }


}
