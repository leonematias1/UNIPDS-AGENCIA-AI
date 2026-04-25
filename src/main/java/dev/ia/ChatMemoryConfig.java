package dev.ia;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.store.memory.chat.InMemoryChatMemoryStore;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class ChatMemoryConfig {

    @Produces
    public ChatMemory chatMemory() {
        return MessageWindowChatMemory.builder()
                // caso for uma API da openai por ex o maximo de msg interfere no valor pq ele manda mais tokens
                .maxMessages(20) // mantém as ultimas 20 mensagens na memória
                .chatMemoryStore(new InMemoryChatMemoryStore())
                .build();
    }

}
