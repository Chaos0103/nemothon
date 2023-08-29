package com.nemo.server.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class NotificationService {
    // 기본 타임아웃
    private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;

    private final EmitterRepository emitterRepository;

    // 클라이언트가 구독을 위해 호출하는 메서드
    public SseEmitter subscribe(Long userId) {
        SseEmitter emitter = createEmitter(userId);

        sendToClient(userId, "EventStream Created. [userId=" + userId + "]");
        return emitter;
    }

    // 서버의 이벤트를 클라이언트에게 보내는 메서드
    public void notify(Long userId, Object event) {
        sendToClient(userId, event);
    }

    // 클라이언트에게 데이터 전송
    private void sendToClient(Long id, Object data) {
        SseEmitter emitter = emitterRepository.get(id);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event().id(String.valueOf(id)).name("sse").data(data));
            } catch (IOException exception) {
                emitterRepository.deleteById(id);
                emitter.completeWithError(exception);
            }
        }
    }

    // 사용자 아이디를 기반으로 이벤트 Emitter 생성
    private SseEmitter createEmitter(Long id) {
        SseEmitter emitter = new SseEmitter(DEFAULT_TIMEOUT);
        emitterRepository.save(id, emitter);

        // Emitter가 완료될 때 Emitter 삭제
        emitter.onCompletion(() -> emitterRepository.deleteById(id));

        // Emitterr가 타임아웃  되었을 때 Emitter 삭제
        emitter.onTimeout(() -> emitterRepository.deleteById(id));

        return emitter;
    }
}
