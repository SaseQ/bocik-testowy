package dev.saseq.assistant;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.TokenStream;

public interface Assistant {
    @SystemMessage("""
            Jesteś pomocnym asystentem,
            odpowiadaj w formacie tekstowym discord,
            maksymalnie 2000 znaków,
            jeśli twoja odpowiedź jest dłuższa, zadaj pytanie na końcu aby kontynuować.
            """)
    TokenStream chat(String message);
}
