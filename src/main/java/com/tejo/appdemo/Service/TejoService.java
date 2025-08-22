package com.tejo.appdemo.Service;

import com.tejo.appdemo.entity.TejoEntity;
import java.util.List;
import java.util.Optional;

public interface TejoService {
    TejoEntity saveTejo(TejoEntity tejo);
    List<TejoEntity> getAllTejos();
    Optional<TejoEntity> getTejoById(Integer id);
    TejoEntity updateTejo(Integer id, TejoEntity tejo);
    void deleteTejo(Integer id);
}
