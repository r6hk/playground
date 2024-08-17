package dev.rennen.springbootproject;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author rennen.dev
 * @date 2024/8/15 22:03
 */
@Component
public class Test {

    @Transactional
    public void test() {
    }
}
