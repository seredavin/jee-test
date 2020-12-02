package service;

import dto.CalcRequest;
import dto.City;
import dto.ToDistance;
import repo.DistanceRepo;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class CalcService {
    private CalcRequest calcRequest;
    @Inject
    DistanceRepo distanceRepo;

    private CalcService() {}

    public CalcRequest getCalcRequest() {
        return calcRequest;
    }

    public void setCalcRequest(CalcRequest calcRequest) {
        this.calcRequest = calcRequest;
    }

    public DistanceRepo getDistanceRepo() {
        return distanceRepo;
    }

    public void setDistanceRepo(DistanceRepo distanceRepo) {
        this.distanceRepo = distanceRepo;
    }

    public CalcService(CalcRequest calcRequest) {
        this.calcRequest = calcRequest;
    }

    public double getCrowflightDistance(){
        double lat1 = calcRequest.getFrom().getLatitude();
        double lon1 = calcRequest.getFrom().getLongitude();
        double lat2 = calcRequest.getTo().getLatitude();
        double lon2 = calcRequest.getTo().getLongitude();
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 1.609344 * 100;
        return (dist);
    }
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    public Integer getMatrixDistance() {
        List<ToDistance> distanceList = new ArrayList<>();
        Integer i = 0;
        distanceList.add(new ToDistance(this.calcRequest.getFrom(), 0));
        List<City> settledCities = new ArrayList<>();
        settledCities.add(this.calcRequest.getFrom());
        Integer distance = Integer.MAX_VALUE;
        while (true) {
            ToDistance currentToDistance = distanceList.get(i);
            List<ToDistance> neighborList = distanceRepo.findAllWhereCityAndNotInList(
                    distanceList.get(i).getCity(),
                    settledCities
            );
            if (neighborList.size() > 0) {
                for (ToDistance toDistance : neighborList) {
                    if (toDistance.getCity().equals(this.calcRequest.getTo())) {
                        if (toDistance.getDistance() + currentToDistance.getDistance() < distance) {
                            distance = toDistance.getDistance() + currentToDistance.getDistance();
                        }
                    } else {
                        if (toDistance.getDistance() + currentToDistance.getDistance() <= distance) {
                            distanceList.add(new ToDistance(toDistance.getCity(),
                                    toDistance.getDistance() + currentToDistance.getDistance()));
                        }
                    }
                }
            }
            if (!currentToDistance.getCity().equals(this.calcRequest.getTo())) {
                settledCities.add(currentToDistance.getCity());
            }
            i++;
            if (i + 1 > distanceList.size()) break;
        }
        return distance;
    }
}
