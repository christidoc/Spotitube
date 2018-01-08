package service;

import java.util.Random;

/**
 * Created by Christiaan on 13-10-2017.
 */
public class TokenService {
    public String getRandomToken(){
        Random random = new Random();
        random.nextInt(2000000);
        return random.nextInt(2000000)+"";
    }
}
