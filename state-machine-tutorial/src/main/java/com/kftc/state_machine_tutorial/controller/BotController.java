package com.kftc.state_machine_tutorial.controller;

import com.kftc.state_machine_tutorial.events.BotEvent;
import com.kftc.state_machine_tutorial.request.PostRequest;
import com.kftc.state_machine_tutorial.service.BotStateMachineService;
import com.kftc.state_machine_tutorial.states.BotState;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bot")
public class BotController {

    private final BotStateMachineService stateMachineService;

    // 사용자의 메시지를 수신하는 엔드포인트
    @PostMapping("/message")
    public String receiveMessage(@RequestBody PostRequest request) {
        String userId = request.getUserId();
        String message = request.getMessage();

        // 메시지 수신 이벤트 전송
        stateMachineService.sendEvent(userId, BotEvent.RECEIVE_MESSAGE);
        
        // 메시지 처리 로직 (예: 메시지 분석, 응답 생성 등)
        // 여기서는 단순히 PROCESS_MESSAGE 이벤트를 전송한다고 가정
        boolean processed = stateMachineService.sendEvent(userId, BotEvent.PROCESS_MESSAGE);
        
        if (processed) {
            // 처리 완료 이벤트 전송
            stateMachineService.sendEvent(userId, BotEvent.FINISH);
            return "Message processed successfully.";
        } else {
            // 처리 실패 이벤트 전송
            stateMachineService.sendEvent(userId, BotEvent.FAIL);
            return "Failed to process message.";
        }
    }

    // 현재 상태 확인 엔드포인트
    @GetMapping("/state")
    public BotState getState(@RequestParam String userId) {
        return stateMachineService.getCurrentState(userId);
    }
}
