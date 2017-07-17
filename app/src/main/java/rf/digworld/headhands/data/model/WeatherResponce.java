package rf.digworld.headhands.data.model;


import com.google.gson.annotations.Expose;

import java.util.Locale;

public class WeatherResponce {
    @Expose
    Main main;
    @Expose
    String error;

    public WeatherResponce() {

    }

    public WeatherResponce(Double temp, int pressure, int humidity) {
        main=new Main(temp,pressure,humidity);
    }

    public Main getMain() {
        return main;
    }

    public String getError() {
        return error;
    }

    public class Main{
        @Expose
        Double temp;
        @Expose
        int pressure;
        @Expose
        int humidity;

        public Main() {
        }

        public Main(Double temp, int pressure, int humidity) {
            this.temp = temp;
            this.pressure = pressure;
            this.humidity = humidity;
        }

        public String getTemp() {
            return String.format(Locale.getDefault(),"%.1f"+ " \u2103",temp );
        }

        public String getPressure() {
            Double presureInMM=pressure*0.750061561303;
            return String.format("%3.0f", presureInMM);
        }

        public String getHumidity() {
            return String.format("%.0f",humidity);
        }
    }

}
