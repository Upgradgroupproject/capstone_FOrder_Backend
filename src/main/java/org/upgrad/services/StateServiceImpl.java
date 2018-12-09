package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.States;
import org.upgrad.repositories.StateRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StateServiceImpl implements StateService {

    private final StateRepository stateRepository;
    public StateServiceImpl(StateRepository stateRepository)
    {
        this.stateRepository=stateRepository;
    }


    @Override
    public List<States> getAllStates() {
        return stateRepository.getAllStates();
    }

}
