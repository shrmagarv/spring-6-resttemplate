package guru.springframework.spring6resttemplate.client;

import guru.springframework.spring6resttemplate.model.BeerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static guru.springframework.spring6resttemplate.model.BeerStyle.IPA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerClientImplTest {

    @Autowired
    BeerClientImpl beerClient ;

    @Test
    void testCreateBeer() {
        BeerDTO beerDTO = BeerDTO.builder()
                .beerName("New Beer template")
                .beerStyle(IPA)
                .price(BigDecimal.valueOf(10.33))
                .upc("123456789012")
                .quantityOnHand(12)
                .build();
        BeerDTO savedBeer = beerClient.createBeer(beerDTO);
        assertThat(savedBeer).isNotNull();
    }

    @Test
    void testUpdateBeer() {
        BeerDTO beerDTO = BeerDTO.builder()
                .beerName("New Beer template")
                .beerStyle(IPA)
                .price(BigDecimal.valueOf(10.33))
                .upc("123456789012")
                .quantityOnHand(12)
                .build();

        BeerDTO savedBeer = beerClient.createBeer(beerDTO);
        final String beerName = "New Beer template2";
        savedBeer.setBeerName(beerName);
        BeerDTO updatedBeer = beerClient.updateBeer(savedBeer);
        assertThat(updatedBeer.getBeerName()).isEqualTo(beerName);
    }

    @Test
    void testDeleteBeer() {
        BeerDTO beerDTO = BeerDTO.builder()
                .beerName("Delete Beer template")
                .beerStyle(IPA)
                .price(BigDecimal.valueOf(10.33))
                .upc("123456789012")
                .quantityOnHand(12)
                .build();

        BeerDTO savedBeer = beerClient.createBeer(beerDTO);
        beerClient.deleteBeer(savedBeer.getId());
        assertThrows(RuntimeException.class, () -> beerClient.getBeerById(savedBeer.getId()));
    }

    @Test
    void testGetBeerByID() {
        BeerDTO beer = beerClient.listBeers().getContent().get(0);
        BeerDTO beerDTO = beerClient.getBeerById(beer.getId());
        assertNotNull(beerDTO);
    }

    @Test
    void listBeersWithoutName() {

        beerClient.listBeers();

    }

    @Test
    void listBeersWithName() {
        beerClient.listBeers("IPA", IPA, false, 0, 15);
    }


}