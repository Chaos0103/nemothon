package com.nemo.server.domain.notification.service;

import com.nemo.server.domain.member.Member;
import com.nemo.server.domain.member.repository.MemberRepository;
import com.nemo.server.notification.dao.EmitterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class NotificationService {
    // 기본 타임아웃 설정
    private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;

    private final EmitterRepository emitterRepository;
    private final MemberRepository memberRepository;

    /**
     * 클라이언트가 구독을 위해 호출하는 메서드.
     */
    public SseEmitter subscribe(String memberEmail) {
        Member member = memberRepository.findByEmail(memberEmail).orElseThrow(NoSuchElementException::new);
        SseEmitter emitter = createEmitter(member.getId());
        return emitter;
    }

    /**
     * 서버의 이벤트를 클라이언트에게 보내는 메서드
     */
    public void notify(Long userId, Object event) {
        sendToClient(userId, event);
    }

    /**
     * 클라이언트에게 데이터를 전송
     */
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

    /**
     * 사용자 아이디를 기반으로 이벤트 Emitter를 생성
     */
    private SseEmitter createEmitter(Long id) {
        SseEmitter emitter = new SseEmitter(DEFAULT_TIMEOUT);
        emitterRepository.save(id, emitter);

        // Emitter가 완료될 때(모든 데이터가 성공적으로 전송된 상태) Emitter를 삭제한다.
        emitter.onCompletion(() -> emitterRepository.deleteById(id));
        // Emitter가 타임아웃 되었을 때(지정된 시간동안 어떠한 이벤트도 전송되지 않았을 때) Emitter를 삭제한다.
        emitter.onTimeout(() -> emitterRepository.deleteById(id));

        return emitter;
    }
}
