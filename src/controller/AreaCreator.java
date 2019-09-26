package controller;

import model.hotel.area.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AreaCreator {

    private ArrayList<Area> areaList;
    private String layoutFile;
    private int maxX;
    private int maxY;
    private int minX;
    private int minY;

    public AreaCreator(String layoutFile) {
        this.layoutFile = layoutFile;
        this.areaList = new ArrayList<>();
        init();
    }

    private void init() {

        Object object = LayoutFileReader.readFile(layoutFile);

        for (Object obj : (JSONArray) object) {

            Area area = null;
            String position = null;
            String dimension = null;
            String areaType = null;
            int x = 0;
            int y = 0;
            int width = 0;
            int height = 0;
            int capacity = 0;
            String classification = null;

            for(Object o : ((JSONObject) obj).keySet()){

                String key = o.toString();

                if(key.equalsIgnoreCase(("Position"))){
                    position = (String) ((JSONObject) obj).get("Position");
                    x = Integer.parseInt(String.valueOf(position.charAt(0)));
                    y = Integer.parseInt(String.valueOf(position.charAt(3)));
                }
                else if (key.equalsIgnoreCase(("Dimension"))){
                    dimension = (String) ((JSONObject) obj).get(key);
                    width = Integer.parseInt(String.valueOf(dimension.charAt(0)));
                    height = Integer.parseInt(String.valueOf(dimension.charAt(3)));
                }
                else if (key.equalsIgnoreCase(("AreaType"))){
                    areaType =  (String) ((JSONObject) obj).get(key);
                }
                else if (key.equalsIgnoreCase(("Classification"))){
                    classification = (String) ((JSONObject) obj).get(key);
                }
                else if (key.equalsIgnoreCase(("Capacity"))){
                    if ((((JSONObject) obj).get(key)) instanceof String)
                        capacity = Integer.parseInt(String.valueOf(((JSONObject) obj).get(key)));
                    else
                        capacity = (int) (long) ((JSONObject) obj).get(key);
                }
            }

            AreaFactory factory = new AreaFactory();
            area = factory.createArea(areaType,x,y,width,height,capacity,classification);
            areaList.add(area);
        }

        // get the max and min of XY in the current areaList
        maxX = getMaxX();
        maxY = getMaxY();
        minX = getMinX();
        minY = getMinX();

        // add standard areas into the areaList
        addLiftToAreaList();
        addStairsToAreaList();
        addLobbyToAreaList();
    }

    public int getMinX() {
        return (int) (Collections.min(areaList, Comparator.comparingDouble(c -> c.getX()))).getX();
    }
    public int getMaxX() {
        return (int) (Collections.max(areaList, Comparator.comparingDouble(c -> c.getX()))).getX();
    }
    public int getMinY() {
        return (int) (Collections.min(areaList, Comparator.comparingDouble(c -> c.getY()))).getY();
    }
    public int getMaxY() {
        int y = (int) (Collections.max(areaList, Comparator.comparingDouble(c -> c.getY()))).getY();
        int h = 1;

        // include dimension of area to determine the maxY
        for(Area a : areaList){
            if(a.getY() == y){
                if(a.getHeight() > h){
                    h = (int) a.getHeight();
                }
            }
        }
        return y+h-1;
    }

    private void addLiftToAreaList() {
        for (int y = minY - 1; y < maxY + 1; y++) {
            areaList.add(new Lift(minX - 1, y, 1, 1));
        }
    }

    private void addStairsToAreaList() {
        for (int y = minY - 1; y < maxY + 1; y++) {
            areaList.add(new Stairs(maxX + 1, y, 1, 1));
        }
    }

    private void addLobbyToAreaList() {
        areaList.add(new Lobby(maxX / 3, minY - 1, 2, 1));
    }

    public ArrayList<Area> getAreaList() {
        return areaList;
    }

}
