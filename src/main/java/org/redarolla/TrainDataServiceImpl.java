package org.redarolla;

public class TrainDataServiceImpl implements TrainDataService {

    TrainDataService trainDataService;

    @Override
    public TrainDataResponse get(String trainId) {
        return trainDataService.get(trainId);
    }
}
