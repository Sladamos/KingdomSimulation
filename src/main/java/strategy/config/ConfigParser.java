package strategy.config;

import strategy.Config;
import strategy.json.JSON;

public interface ConfigParser<T extends Config> {
    T createConfig(JSON json);
}
