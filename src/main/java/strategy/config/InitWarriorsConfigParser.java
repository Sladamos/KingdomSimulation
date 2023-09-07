package strategy.config;

import org.json.JSONException;
import strategy.json.JSON;
import strategy.item.military.infantry.warrior.InitWarriorsConfig;
import strategy.CriticalAppError;

public class InitWarriorsConfigParser implements ConfigParser<InitWarriorsConfig>{
    @Override
    public InitWarriorsConfig createConfig(JSON json) {
        try {
            int numberOfWarriors = json.getInt("number_of_warriors");
            int maxDamage = json.getInt("max_damage");
            int maxDefense = json.getInt("max_defense");
            return new InitWarriorsConfig(numberOfWarriors, maxDamage, maxDefense);
        }
        catch (JSONException err) {
            throw new CriticalAppError("Something went wrong on creating init warriors config. " + err.getMessage());
        }
    }
}
