package br.com.alura.comex.loja;

import lombok.NoArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageChannel;

@EnableBinding(StreamConfig.PedidoGeradoSource.class)
@Configuration
@NoArgsConstructor
public class StreamConfig {

    public interface PedidoGeradoSource {
        @Output
        MessageChannel pedidoGerado();

    }

}
