package com.kftc.state_machine_tutorial.config;

import com.kftc.state_machine_tutorial.events.BotEvent;
import com.kftc.state_machine_tutorial.states.BotState;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@Configuration
@EnableStateMachineFactory
public class BotStateMachineConfig extends EnumStateMachineConfigurerAdapter<BotState, BotEvent> {

    @Override
    public void configure(StateMachineStateConfigurer<BotState, BotEvent> states) throws Exception {
        states
            .withStates()
                .initial(BotState.START)
                .states(EnumSet.allOf(BotState.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<BotState, BotEvent> transitions) throws Exception {
        transitions
            .withExternal()
                .source(BotState.START).target(BotState.WAITING_FOR_INPUT).event(BotEvent.RECEIVE_MESSAGE)
            .and()
            .withExternal()
                .source(BotState.WAITING_FOR_INPUT).target(BotState.PROCESSING).event(BotEvent.PROCESS_MESSAGE)
            .and()
            .withExternal()
                .source(BotState.PROCESSING).target(BotState.COMPLETED).event(BotEvent.FINISH)
            .and()
            .withExternal()
                .source(BotState.PROCESSING).target(BotState.ERROR).event(BotEvent.FAIL);
    }

//    @Override
//    public void configure(StateMachineConfigurationConfigurer<BotState, BotEvent> config) throws Exception {
//    }
}
