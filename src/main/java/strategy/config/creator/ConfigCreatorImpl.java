package strategy.config.creator;

import strategy.Config;
import strategy.config.ConfigParser;
import strategy.json.JSON;
import strategy.json.JsonLoader;

public class ConfigCreatorImpl<T extends Config> implements ConfigCreator<T> {
	@Override
	public T createConfigFromJson(JsonLoader jsonLoader, ConfigParser<T> configParser) {
		JSON json = jsonLoader.loadJson();
		return configParser.createConfig(json);
	}
}
