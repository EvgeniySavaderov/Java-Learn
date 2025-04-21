package homeworks.service;

import homeworks.dto.TVRequest;
import homeworks.dto.TVResponse;

import java.util.List;

public interface TVService {
    List<TVResponse> getTVs();

    void deleteAllTVs();

    TVResponse addTV(TVRequest tvRequest);

    void deleteById(Long id);

    TVResponse findById(Long id);

    TVResponse changeState(Long id);

    List<TVResponse> getSilentTV(Integer volume);

    TVResponse nextChannel(Long id);

    TVResponse prevChannel(Long id);

    TVResponse goToChannel(Long id, Integer channel);
}
