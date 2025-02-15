package dev.rennen.springbootproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RestController
public class SseController {

    @GetMapping("/sse")
    public SseEmitter streamEvents() {
        // 创建一个 SseEmitter 对象，设置超时时间为 30 秒
        SseEmitter emitter = new SseEmitter(30_000L);

        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                // 模拟发送事件
                for (int i = 1; i <= 10; i++) {
                    // 发送消息
                    emitter.send("Message " + i);
                    // 每隔 1 秒发送一条消息
                    TimeUnit.SECONDS.sleep(1);
                }
                // 发送完成后调用 complete
                emitter.complete();
            } catch (IOException | InterruptedException e) {
                // 发送失败，调用 completeWithError
                emitter.completeWithError(e);
            }
        });

        return emitter;
    }
}
