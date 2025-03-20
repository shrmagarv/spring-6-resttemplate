package guru.springframework.spring6resttemplate.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static guru.springframework.spring6resttemplate.model.BeerStyle.IPA;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerClientImplTest {

    @Autowired
    BeerClientImpl beerClient ;

    @Test
    void listBeersWithoutName() {

        beerClient.listBeers();

    }

    @Test
    void listBeersWithName() {

        beerClient.listBeers("IPA", IPA, false, 0, 15);
    }
}