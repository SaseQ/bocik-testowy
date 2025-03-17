package dev.saseq.assistant.service;

import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.mcp.client.DefaultMcpClient;
import dev.langchain4j.mcp.client.McpClient;
import dev.langchain4j.mcp.client.transport.McpTransport;
import dev.langchain4j.mcp.client.transport.stdio.StdioMcpTransport;
import dev.langchain4j.service.tool.ToolProvider;
import dev.saseq.assistant.config.AssistantConfig;
import lombok.Getter;

import java.util.List;
import java.util.Map;

public class McpService {
    @Getter
    private final ToolProvider toolProvider;

    public McpService() {
        McpTransport mcpTransport = buildMcpTransport(
                List.of("npm", "exec", "@modelcontextprotocol/server-brave-search"),
                Map.of("BRAVE_API_KEY", AssistantConfig.getBraveSearchApiKey()),
                true
        );
        McpClient mcpClient = buildMcpClient(mcpTransport);
        this.toolProvider = McpToolProvider.builder()
                .mcpClients(List.of(mcpClient))
                .build();
    }

    private McpTransport buildMcpTransport(List<String> commands, Map<String, String> env, boolean debug) {
        return new StdioMcpTransport.Builder()
                .command(commands)
                .environment(env)
                .logEvents(debug)
                .build();
    }

    private McpClient buildMcpClient(McpTransport transport) {
        return new DefaultMcpClient.Builder()
                .transport(transport)
                .build();
    }
}
