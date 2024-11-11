package com.kftc.state_machine_tutorial.service;

import com.kftc.state_machine_tutorial.events.BotEvent;
import com.kftc.state_machine_tutorial.states.BotState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class BotStateMachineService {

    @Autowired
    private StateMachineFactory<BotState, BotEvent> stateMachineFactory;

    // 사용자별 상태 머신을 저장하기 위한 맵
    private ConcurrentHashMap<String, StateMachine<BotState, BotEvent>> stateMachines = new ConcurrentHashMap<>();

    public StateMachine<BotState, BotEvent> getStateMachine(String userId) {
        return stateMachines.computeIfAbsent(userId, id -> {
            StateMachine<BotState, BotEvent> sm = stateMachineFactory.getStateMachine(id);
            sm.start();
            return sm;
        });
    }

    public BotState getCurrentState(String userId) {
        StateMachine<BotState, BotEvent> sm = getStateMachine(userId);
        return sm.getState().getId();
    }

    public boolean sendEvent(String userId, BotEvent event) {
        StateMachine<BotState, BotEvent> sm = getStateMachine(userId);
        synchronized (sm) { // 동시성 문제 방지를 위해 동기화
            return sm.sendEvent(event);
        }
    }
}
