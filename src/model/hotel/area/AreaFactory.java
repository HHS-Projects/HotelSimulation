package model.hotel.area;

import model.hotel.area.*;

public class AreaFactory {

    public Area createArea (String type, int x, int y, int width, int height, int capacity, String classification) {
        switch(type) {
            case "Cinema": return new Cinema(x,y,width,height);
            case "Fitness": return new Fitness(x,y,width,height);
            case "Lift": return new Lift(x,y,width,height);
            case "Lobby": return new Lobby(x,y,width,height);
            case "Stairs": return new Stairs(x,y,width,height);
            case "Restaurant": return new Restaurant(x,y,width,height,capacity);
            case "Room": return new Room(x,y,width,height,classification);
            default: return null;
        }
    }
}
