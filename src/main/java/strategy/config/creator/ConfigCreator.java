package strategy.config.creator;

import strategy.Config;
import strategy.config.ConfigParser;
import strategy.json.JsonLoader;

public interface ConfigCreator<T extends Config> {
	T createConfigFromJson(JsonLoader jsonLoader, ConfigParser<T> configParser);
}
