package rbrtjhs.config;

import com.eventstore.dbclient.Endpoint;
import com.eventstore.dbclient.EventStoreDBClient;
import com.eventstore.dbclient.EventStoreDBClientSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventstoreConfig {

    @Value("${eventstore.host")
    private String host;

    @Value("${eventstore.port}")
    private int port;

    @Value("${eventstore.username}")
    private String username;

    @Value("${eventstore.password}")
    private String password;

    @Bean
    public EventStoreDBClient eventstore() {
        EventStoreDBClientSettings eventstoreSettings = EventStoreDBClientSettings.builder()
                .addHost(new Endpoint(host, port))
                .defaultCredentials(username, password).buildConnectionSettings();
        EventStoreDBClient eventStoreDBClient = EventStoreDBClient.create(eventstoreSettings);
        return eventStoreDBClient;
    }
}
