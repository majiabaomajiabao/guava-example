package com.guava.example.event.bus;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * @author majiabao on 2018/1/31.
 */
public class EventbusExample {
    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        eventBus.register(new Object() {
            @Subscribe
            public void handleUserInfoChangeEvent(UserInfoChangeEvent userInfoChangeEvent) {
                System.out.println("处理用户信息变化事件：" + userInfoChangeEvent.getUserName());
            }

            @Subscribe
            public void handleUserInfoChangeEvent(BaseEventBusEvent userInfoChangeEvent) {
                System.out.println("所有事件的父类");
            }

            @Subscribe
            public void handlerA(A a) {
                System.out.println("无关类");
            }

        });
        eventBus.post(new UserInfoChangeEvent("apple"));
    }

    static class BaseEventBusEvent {

    }

    static class A {

    }

    static class UserInfoChangeEvent extends BaseEventBusEvent {
        private String userName;

        public UserInfoChangeEvent(String userName) {
            this.userName = userName;
        }

        public String getUserName() {
            return userName;
        }
    }
}
