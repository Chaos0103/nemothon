package com.nemo.server.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@RequiredArgsConstructor
public class EmitterRepository {
    private final Map<Long, SseEmitter> emitters = new ConcurrentHashMap<>();

    // 주어진 아이디와 emitter 저장
    public void save(Long id, SseEmitter emitter) {
        emitters.put(id, emitter);
    }

    // 주어진 아이디와 emitter 삭제
    public void deleteById(Long id) {
        emitters.remove(id);
    }

    // 주어진 아이디와 Emitter를 가져옴
    public SseEmitter get(Long id) {
        return emitters.get(id);
    }
}
