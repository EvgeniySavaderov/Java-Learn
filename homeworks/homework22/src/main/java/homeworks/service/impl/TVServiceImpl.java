package homeworks.service.impl;

import homeworks.dto.TVRequest;
import homeworks.dto.TVResponse;
import homeworks.mapper.TVRequestMapper;
import homeworks.mapper.TVResponseMapper;
import homeworks.repository.TVRepository;
import homeworks.service.TVService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TVServiceImpl implements TVService {

    private final TVRepository tvRepository;
    private final TVRequestMapper tvRequestMapper;
    private final TVResponseMapper tvResponseMapper;

    @Override
    public List<TVResponse> getTVs() {
        return tvRepository.findAll()
                .stream()
                .map(tvResponseMapper::toTVResponse)
                .toList();
    }

    @Override
    public void deleteAllTVs() {
        tvRepository.deleteAll();
    }

    @Override
    public TVResponse addTV(TVRequest tvRequest) {
        return tvResponseMapper.toTVResponse(
                tvRepository.save(
                        tvRequestMapper.toEntity(tvRequest)));
    }

    @Override
    public void deleteById(Long id) {
        tvRepository.deleteById(id);
    }

    @Override
    public TVResponse findById(Long id) {
        return tvResponseMapper.toTVResponse(
                tvRepository.findById(id).orElseThrow());
    }

    @Override
    @Transactional
    public TVResponse changeState(Long id) {
        tvRepository.tvChangeState(id);
        return tvResponseMapper.toTVResponse(
                tvRepository.findById(id).orElseThrow());
    }

    @Override
    public List<TVResponse> getSilentTV(Integer volume) {
        return tvRepository.getSilentTV(volume)
                .stream()
                .map(tvResponseMapper::toTVResponse)
                .toList();
    }

    @Override
    @Transactional
    public TVResponse nextChannel(Long id) {
        tvRepository.tvNextChannel(id);
        return tvResponseMapper.toTVResponse(
                tvRepository.findById(id).orElseThrow());
    }

    @Override
    @Transactional
    public TVResponse prevChannel(Long id) {
        tvRepository.tvPrevChannel(id);
        return tvResponseMapper.toTVResponse(
                tvRepository.findById(id).orElseThrow());
    }

    @Override
    @Transactional
    public TVResponse goToChannel(Long id, Integer channel) {
        tvRepository.tvGoToChannel(id, channel);
        return tvResponseMapper.toTVResponse(
                tvRepository.findById(id).orElseThrow());
    }
}
