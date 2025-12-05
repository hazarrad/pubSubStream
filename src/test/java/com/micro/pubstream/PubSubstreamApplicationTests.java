package com.micro.pubstream;

import com.google.api.core.SettableApiFuture;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.PubsubMessage;
import com.micro.pubstream.api.PubSubstreamApi;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class PubSubstreamApplicationTests {

    @Mock
    private Publisher publisher;
    @Mock
    private Subscriber subscriber;

    @InjectMocks
    private PubSubstreamApi pubSubstreamApi;

    @Test
    void contextLoads() {
    }

    @Test
    void TestPublishMessage() throws ExecutionException, InterruptedException {
        String expectedMessageId = "12345";
        SettableApiFuture<String> apiFuture = SettableApiFuture.create();
        apiFuture.set(expectedMessageId);

        when(publisher.publish(any(PubsubMessage.class))).thenReturn(apiFuture);

        String response = pubSubstreamApi.publishMessage("Hello World");

        assertEquals("Published Successfully", response);
        verify(publisher, times(1)).publish(any(PubsubMessage.class));

    }

    @Test
    void TestStartSubscriber() {

        when(subscriber.getSubscriptionNameString()).thenReturn("test-sub");
        Subscriber.Listener listener = mock(Subscriber.Listener.class);
        when(subscriber.startAsync()).thenReturn(subscriber);

        pubSubstreamApi.startSubscriber();

        verify(subscriber, times(1)).startAsync();
        verify(subscriber, times(1)).awaitRunning();

    }

}
