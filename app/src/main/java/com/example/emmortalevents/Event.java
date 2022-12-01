package com.example.emmortalevents;

public class Event {
    private String EventName;
    private Long Date;
    private String Client;
    private String Venue;
    private String Cake;
    private String Music;
    private String Deco;
    private String Lights;
    private String Flowers;


    public Event() {
        //public no-arg constructor needed
    }

    public Event(String EventName, Long Date, String Client, String Venue, String Cake,String Music, String Deco, String Lights, String Flowers){
        this.EventName = EventName;
        this.Date = Date;
        this.Client = Client;
        this.Cake = Cake;
        this.Music = Music;
        this.Deco = Deco;
        this.Lights = Lights;
        this.Flowers = Flowers;
        this.Venue = Venue;
    }

    public String getEventName(){
        return EventName;
    }

    public String getClient(){
        return Client;
    }
    public String getVenue(){
        return Venue;
    }
    public String getCake(){
        return Cake;
    }
    public String getMusic(){
        return Music;
    }
    public String getDeco(){
        return Deco;
    }
    public String getLights(){
        return Lights;
    }
    public String getFlowers(){
        return Flowers;
    }

    public Long getDate(){
        return Date;
    }


}
