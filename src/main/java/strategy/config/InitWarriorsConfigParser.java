package strategy.config;

import strategy.AppError;
import strategy.json.JSON;
import strategy.military.infantry.InitWarriorsConfig;

public class InitWarriorsConfigParser implements ConfigParser<InitWarriorsConfig>{
    @Override
    public InitWarriorsConfig createConfig(JSON json) {
        try {
            int numberOfWarriors = json.getInt("number_of_warriors");
            int maxDamage = json.getInt("max_damage");
            int maxDefense = json.getInt("max_defense");
            return new InitWarriorsConfig(numberOfWarriors, maxDamage, maxDefense);
        }
        catch (Exception err) {
            throw new AppError("Something went wrong on creating init warriors config. " + err.getMessage() );
        }
    }
}
