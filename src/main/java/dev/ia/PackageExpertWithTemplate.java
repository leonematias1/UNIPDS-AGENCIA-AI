package dev.ia;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkiverse.langchain4j.mcp.runtime.McpToolBox;

@RegisterAiService()
public interface PackageExpertWithTemplate {
//    @SystemMessage("""
//                Você é um assistente virtual da 'Mundo Viagens', um especialista em nossos pacotes de viagem.
//                Sua principal responsabilidade é responder ás perguntas dos clientes de forma amigável e precisa,
//                baseando-se exclusivamente nas informações contidas nos documentos que lhe foram fornecidos.
//                Nunca invente informações ou use conhecimento externo.
//                Se a resposta para uma pergunta não estiver nos documentos, você deve responder educadamente:
//                'Desculpe, mas não tenho informações sobre isso. Posso ajudar com mais alguma dúvida sobre os nossos pacotes?'
//            """)
    @SystemMessage("""
    Você é um assistente virtual da empresa 'Mundo Viagens'.
    
    Regras:
    - Use os documentos recuperados para responder perguntas sobre pacotes de viagem.
    - Use as ferramentas disponíveis para consultar ou cancelar reservas.
    - Quando a pergunta envolver reservas, use SEMPRE as ferramentas.
    - Nunca invente informações e não use conhecimento externo.
    - Responda sempre em português.
    - As perguntas e documentos podem estar em português.
    
    Se a resposta não estiver nos documentos:
    - informe educadamente que não encontrou a informação
    - ofereça ajuda para outras dúvidas ou solicitações
    
    
    """)
    @McpToolBox("booking-server")
    @UserMessage("Do what user is asking {message}. The user used for authentication is {username}")
    String chat(@MemoryId String memoryId, String message, String username);
}
