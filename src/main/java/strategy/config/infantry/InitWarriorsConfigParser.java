package strategy.config.infantry;

import org.json.JSONException;
import strategy.config.ConfigParser;
import strategy.json.JSON;
import strategy.item.military.infantry.warrior.InitWarriorsConfig;
import strategy.CriticalAppError;

public class InitWarriorsConfigParser implements ConfigParser<InitWarriorsConfig> {
    @Override
    public InitWarriorsConfig createConfig(JSON json) {
        try {
            int numberOfWarriors = json.getInt("number_of_warriors");
            int maxDamage = json.getInt("max_damage");
            int maxDefense = json.getInt("max_defense");
            int health = json.getInt("health");
            return new InitWarriorsConfig(numberOfWarriors, maxDamage, maxDefense, health);
        }
        catch (JSONException err) {
            throw new CriticalAppError("Something went wrong on creating initializing warriors config. " + err.getMessage());
        }
    }
}
